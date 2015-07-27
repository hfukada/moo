(ns moo.core
  (:use
    [ring.middleware.params]
    [ring.middleware.multipart-params]
    [ring.middleware.ratelimit]
    [ring.adapter.jetty])
  (:require
    [clojure.string :refer [split]]
    [clojure.java.io :as io]
    [moo.config :as c]
    [pandect.algo.sha1 :as sha1]
    [aws.sdk.s3 :as s3]
    [compojure.core :refer :all]
    [compojure.route :as route]))

(def aws-creds
  (select-keys c/config [:access-key :secret-key]))

(defn upload-file
  [file]
  (let [file-hash (->> file
                       :tempfile
                       sha1/sha1-file)
        extension (-> file
                      :filename
                      (split #"\.")
                      last)
        content-type (format "image/%s" extension)
        bucket-path (format "%s/%s.%s" (:image-prefix c/config) file-hash extension)]
    (s3/put-object aws-creds (:bucket c/config) bucket-path (:tempfile file) {:content-type content-type})
    (s3/update-object-acl aws-creds (:bucket c/config) bucket-path (s3/grant :all-users :read))))

(defroutes handler
  (POST "/up" {params :params}
        (upload-file (get params "file"))))

(def app
  (-> handler
      (wrap-ratelimit {:limits [(ip-limit 20)]})
      wrap-params
      wrap-multipart-params))

(defn -main []
  (when (not (s3/bucket-exists? aws-creds (:bucket c/config)))
    (s3/create-bucket aws-creds (:bucket c/config)))
  (run-jetty app {:port 8080}))

(ns moo.config
  (:require [clojure.java.io :as io]
            [cheshire.core :as json]))

(def config
  "generate and define a global config"
  {:access-key (System/getenv "ACCESS_KEY")
   :secret-key (System/getenv "SECRET_KEY")
   :image-prefix (System/getenv "IMAGE_PREFIX")
   :bucket (System/getenv "BUCKET")})

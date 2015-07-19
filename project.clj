(defproject moo "0.1.0-SNAPSHOT"
  :description "Super Simple Image Hoster using s3 as a storage solution"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [cheshire "5.5.0"]
                 [ring "1.4.0"]
                 [ring-ratelimit "0.2.2"]
                 [pandect "0.5.2"]
                 [clj-aws-s3 "0.3.10"]]
  :resource-paths ["resources"]
  :main moo.core)

(ns moo.config
  (:require [clojure.java.io :as io]
            [cheshire.core :as json]))

(def config
  "generate and define a global config"
  (-> "config.json"
      io/resource
      .getFile
      io/reader
      (json/parse-stream true)))

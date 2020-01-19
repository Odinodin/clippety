(ns ^:figwheel-hooks clippety.dev
(:require [clippety.core :as app]))

(enable-console-print!)


(defn ^:after-load render-on-reload []
  (prn "RELAODED")
  (app/render))


(prn "Debbing")

(defonce started (do (app/render) true))
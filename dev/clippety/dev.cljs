(ns ^:figwheel-hooks clippety.dev
(:require [clippety.core :as app]))

(enable-console-print!)

(defn ^:after-load render-on-reload []
  (prn "Reloaded")
  (app/render))

(defonce started (do (app/render) true))
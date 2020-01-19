(ns clippety.core
(:require [dumdom.core :as dumdom :refer [defcomponent]]))


(defcomponent css []

)

(defcomponent Main []
  [:div "Yep"
    (css)
  ])

(defn render []
  (dumdom/render (Main) (js/document.getElementById "app")))
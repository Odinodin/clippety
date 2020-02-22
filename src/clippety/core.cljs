(ns clippety.core
  (:require [dumdom.core :as dumdom :refer [defcomponent]]))

(defn to-css [rules]
  (->> rules (map
               (fn [[rule-name config]]
                 (str rule-name " { \n"
                      (->> config (map (fn [[attr val]] (str "  " (name attr) ": " val ";"))) (clojure.string/join "\n"))
                      "\n}\n")))
       (clojure.string/join "\n")))

(defcomponent styling [rules]
  [:style {}
   (to-css (merge {"#app" {:height "100vh" :display "flex" :align-items "center" :justify-content "center"}
                   ".container" {:width "400px" :height "400px"  :position "relative"}
                   ".polygon" {"transition" "all 2s ease" :position "absolute"}}
                  rules))])

(defn step-style [step-id element]
  (->> (:polygons element)
       (map-indexed (fn [idx [a b c color]]
                      [(str step-id " > #p" (inc idx)) {:width (str (:width element) "px")
                                                        :height (str (:height element) "px")
                                                        :background-color (or color "pink")
                                                        "-webkit-clip-path" (str "polygon("
                                                                                 (str (first a) "% " (second a) "%, ")
                                                                                 (str (first b) "% " (second b) "%, ")
                                                                                 (str (first c) "% " (second c) "%")
                                                                                 ")")
                                                        :clip-path (str "polygon("
                                                                        (str (first a) "% " (second a) "%, ")
                                                                        (str (first b) "% " (second b) "%, ")
                                                                        (str (first c) "% " (second c) "%")
                                                                        ")")}]))

       (into {})))

(def k {:width 300
        :height 400
        :polygons [[[0, 0], [30, 0] [0, 100] "red"]
                   [[0, 100], [30, 0] [30, 100] "green"]
                   [[30, 40], [30, 60], [60, 50] "blue"]
                   [[30, 40], [60, 50], [80, 0] "yellow"]
                   [[100, 0], [80, 0], [60, 50] "red"]
                   [[30, 60], [60, 50], [80, 100]]
                   [[60, 50], [80, 100], [100, 100]]]})

(def o {:width 300
        :height 300
        :polygons [[[0, 20], [10, 30], [20, 0] "red"]
                   [[10, 30], [20, 20], [20, 0] "darkred"]
                   [[20, 0], [20, 20], [80, 20] "red"]
                   [[20, 0], [80, 0], [80, 20] "darkred"]
                   [[80, 0], [80, 20], [90, 30] "red"]
                   [[80, 0], [100, 20], [90, 30] "darkred"]
                   [[100, 20], [90, 70], [90, 30] "red"]
                   [[100, 20], [100, 80], [90, 70] "darkred"]
                   [[90, 70], [100, 80], [80, 80] "red"]
                   [[100, 80] [80, 80] [80, 100] "darkred"]
                   [[80, 80] [80 100] [20, 80] "red"]
                   [[20, 80], [20, 100], [80, 100] "darkred"]
                   [[0 80] [20 100] [20 80] "red"]
                   [[20 80] [0 60] [0, 80] "darkred"]
                   [[0 20] [0 80] [10 30] "red"]
                   [[10 30] [10 70] [0 80] "darkred"]]})

(def d {:width 300
        :height 300
        :polygons [[[] [] []]]})

(def e {:width 300
        :height 300
        :polygons [[[] [] []]]})

(def green-0 "#8BC685")
(def green-1 "#64B35C")
(def green-2 "#2B9720")
(def green-3 "#206E18")
(def green-4 "#185112")
(def green-5 "#10370C")

(def brown-0 "#251a11")
(def brown-1 "#702e04")
(def brown-11 "#3e2c1d")
(def brown-2 "#a55200")
(def brown-22 "#d37227")
(def brown-3 "#fca266")
(def brown-4 "#ffc38b")
(def brown-5 "#ffd2ac")

(def gray-0 "#3e3e3e")
(def gray-1 "#4d4d4d")
(def gray-2 "#757575")
(def gray-3 "#a0a0a0")
(def gray-4 "#bcbcbc")


(def my-tree {:width 300
              :height 300
              :polygons [[[16 24] [15 32] [21 30] green-3]
                         [[16 24] [21 30] [31 25] green-2]
                         [[5 36] [31 25] [23 52] green-0]
                         [[31 25] [24 46] [35 29] green-0]
                         [[26 20] [35 29] [61 35] green-3]
                         [[5 36] [24 53] [27 43] green-3]
                         [[26 20] [69 0] [61 35] green-0]
                         [[35 29] [24 46] [61 35] green-2]
                         [[61 35] [68 43] [69 0] green-2]
                         [[68 43] [68.9 0] [75 32] green-2]
                         [[68.9 0] [75 32] [84 38] green-1]
                         [[80 28] [81 31] [91 27] green-0]
                         [[81 31] [84 38] [91 27] green-3]
                         [[84 38] [90 40] [91 27] green-3]
                         [[66 53] [67.9 43] [75.1 32] green-0]
                         [[75 32] [94 41] [66 53] green-0]
                         [[68 43] [62 44] [66 53] green-0]
                         [[66 53] [84 50] [94 41] green-2]
                         [[66 53] [84 50] [79 66] green-1]
                         [[66 53] [79 66] [59 60] green-3]
                         [[53 52.9] [66.1 52.9] [59 60] green-5]
                         [[52 53] [62 44] [66 53] green-5]
                         [[61 35] [68 43] [62 44] green-3]
                         [[61 35] [58 49] [62 44] green-3]
                         [[35 50] [25 46] [23 57] green-2]
                         [[61 35] [58 49] [24 46] green-3]
                         [[58 49] [49 56] [24 46] green-4]
                         [[35 50] [39 52] [35 62] green-3]
                         [[23 57] [35 50] [35 62] green-3]
                         [[39 52] [35 62] [49 56] green-5]

                         ;; Trunk
                         [[53 53] [55 67] [54 53] brown-22]
                         [[53 53] [55 67] [52 55] brown-22]
                         [[52.5 54] [55 67] [49 90] brown-22]
                         [[38 55] [49 59 ] [49 64] brown-22]
                         [[38 55] [49 61] [49 64] brown-1]
                         [[53 53] [49 56] [49 90] brown-1]
                         [[55 67] [57 70] [49 90] brown-1]
                         [[55 67] [57 70] [73 60] brown-22]
                         [[57 70] [60 70] [73 60] brown-1]
                         [[57 70] [60 70] [62 85] brown-22]
                         [[57 70] [59 88] [63 88] brown-1]
                         [[57 70] [49 89] [59 88] brown-22]
                         [[49 80] [44 87] [50 89] brown-1]

                         ;; Shadow
                         [[32 90] [44 87] [53 90] green-4]
                         [[50 89] [53 90] [59 88] green-3]
                         [[23 90] [80 90] [62 87] green-3]

                         [[24 90] [36 94] [42 90] green-0]
                         [[36 94] [42 90] [47 95] green-0]
                         [[42 90] [53 90] [47 95] green-0]
                         [[47 95] [53 96] [53 90] green-0]
                         [[53 90] [53 96] [59 95] green-0]
                         [[53 90] [59 95] [65 90] green-0]
                         [[59 95] [65 90] [69 94] green-0]
                         [[65 90] [69 94] [80 90] green-0]

                         [[69 94] [59 95] [62 98] brown-2]
                         [[59 95] [62 98] [53 100] brown-1]
                         [[53 96] [53 100] [59 95] brown-0]
                         [[53 96] [53 100] [47 95] brown-1]
                         [[47 95] [53 100] [44 98] brown-2]
                         [[47 95] [44 98] [36 94] brown-1]

                         ;; Cloud
                         [[5 75] [12 69] [15 74] gray-4]
                         [[5 75] [13 77] [15 74] gray-3]
                         [[5 75] [11 79] [13 77] gray-2]
                         [[11 79] [13 77] [16 78] gray-0]
                         [[13 77] [16 78] [15 74] gray-1]
                         [[12 69] [15 74] [17 71] gray-3]
                         [[15 74] [20 76] [17 71] gray-2]
                         [[15 74] [16 78] [20 76] gray-0]
                         [[16 78] [20 76] [25 81] gray-4]
                         [[20 76] [25 81] [26 77] gray-2]
                         [[26 77] [20 76] [24 72] gray-2]
                         [[17 71] [24 67] [24 72] gray-4]
                         [[17 71] [20 76] [24 72] gray-3]
                         [[24 67] [30 71] [24 72] gray-3]
                         [[24 72] [30 71] [29 75] gray-2]
                         [[30 71] [29 75] [35 70] gray-3]
                         [[35 70] [33 76] [29 75] gray-4]
                         [[24 72] [26 77] [29 75] gray-1]
                         [[26 77] [25 81] [29 75] gray-0]
                         [[25 81] [30 78] [29 75] gray-2]
                         [[35 70] [39 74] [33 76] gray-3]
                         [[39 74] [33 76] [37 77] gray-1]
                         [[37 77] [34 79] [33 76] gray-0]
                         [[30 78] [34 79] [33 76] gray-2]
                         [[29 75] [33 76] [30 78] gray-3]

                         [[89 79] [86 81] [90 81] gray-4]
                         [[89 79] [92 80] [90 81] gray-2]
                         [[86 81 ] [90 81] [89 82] gray-3]
                         [[89 82 ] [90 81] [92 80] gray-1]

                         [[68 78] [72 78] [71 74] gray-4]
                         [[68 78 ] [72 78] [72 79] gray-3]
                         [[68 78 ] [72 79] [73 80] gray-2]
                         [[71 74 ] [72 78] [75 77] gray-2]
                         [[75 77] [72 79] [72 78] gray-1]
                         [[72 78] [72 79] [75 77] gray-2]
                         [[73 80] [75 77] [77 79] gray-3]
                         [[72 79] [73 80] [75 77] gray-0]
                         [[74 75] [77 73] [75 77] gray-4]
                         [[71 74] [74 75] [75 77] gray-3]
                         [[75 77] [77 73] [80 75] gray-4]
                         [[75 77] [80 75] [82 78] gray-3]
                         [[75 77] [77 79] [82 78] gray-2]
                         [[77 79] [82 78] [84 80] gray-1]
                         [[82 78] [85 77] [84 80] gray-0]
                         [[82 78] [85 77] [84 74] gray-3]
                         [[80 75] [82 78] [84 74] gray-4]
                         [[84 74] [88 77] [85 77] gray-3]
                         [[84 80] [88 77] [85 77] gray-1]]})


(def deer {:width 300
           :height 300
           :polygons [
                      ;; Chest
                      [[41.5 68] [50 96] [57.5 68] brown-0]
                      [[41.5 68] [57.5 68] [50 36] brown-0]
                      [[41.5 68] [38 81] [50 96] brown-2]
                      [[61 81] [50 96] [57.5 68] brown-2]
                      [[40 59] [35 70] [38 81] brown-1]
                      [[40 59] [38 81] [43 63] brown-1]
                      [[59 59] [61 81] [56 63] brown-1]
                      [[59 59] [61 81] [64 70] brown-1]

                      ;; Antlers
                      [[66 25] [70 22] [73 11] "darkgrey"]
                      [[70 22] [73 11] [75 11] "darkgrey"]
                      [[75 11] [73 11] [71 5] "lightgrey"]
                      [[73 11] [70 6] [71 5] "lightgrey"]
                      [[71 5] [70 6] [65 1] "lightgrey"]

                      [[32 24] [29 22] [28 11] "darkgrey"]
                      [[29 22] [28 11] [26 11] "darkgrey"]
                      [[28 11] [26 11] [29 6] "lightgrey"]

                      [[38.5 28] [38 31] [30 25] "lightgrey"]
                      [[38.5 28] [30 25] [29 22] "lightgrey"]
                      [[41 36.5] [43 37] [44 35] "grey"]
                      [[41 36.5] [44 35] [41 28] "grey"]
                      [[39 19] [41 34] [41 20] "darkgrey"]
                      [[39 19] [41 34] [38 31] "darkgrey"]
                      [[39 19] [41 20] [44 15] "darkgrey"]
                      [[21 20] [23 15] [23 21] "lightgrey"]
                      [[30 25] [29 22] [23 22] "grey"]
                      [[23 22] [29 22] [21 20] "grey"]

                      [[61.5 28] [62 31] [69 25] "lightgrey"]
                      [[61.5 28] [70 22] [69 25] "lightgrey"]

                      [[59 34] [62 31] [61 19] "darkgrey"]
                      [[59 34] [59 20] [61 19] "darkgrey"]
                      [[59 20] [61 19] [57 15] "darkgrey"]
                      [[56 35] [57 37] [59 37] "grey"]
                      [[56 35] [59 37] [59 28] "grey"]

                      [[77 19.5] [75 21] [76 15] "lightgrey"]
                      [[70 22] [69 25] [76 22] "grey"]
                      [[70 22] [76 22] [77 19.5] "grey"]

                      ;; Eye socket
                      [[45 52] [47 46] [46.5 40] brown-4]
                      [[45 52] [46.5 40] [38 45] brown-4]

                      [[53.5 40] [53 46] [55 52] brown-4]
                      [[55 52] [53.5 40] [61 46] brown-4]


                      ;; Antler sockets
                      [[40 39] [43 37] [41 36] brown-22]
                      [[40 39] [43 37] [44 47] brown-22]
                      [[43 37] [44 47] [46.5 40] brown-22]
                      [[43 37] [46.5 40] [46 36] brown-22]
                      [[43 37] [44 35] [46 36] brown-22]

                      [[56 35] [54 36] [57 37] brown-22]
                      [[57 37] [59 37] [59 39] brown-22]
                      [[53.5 40] [54 36] [57 37] brown-22]
                      [[53.5 40] [55 47] [57 37] brown-22]
                      [[55 47] [57 37] [59 39] brown-22]

                      ;; Left ear
                      [[27 35] [29 36] [38 37] brown-1]
                      [[29 36] [38 37] [40 43] brown-1]
                      [[38 37] [40 39] [40 43] brown-1]
                      [[27 35] [29 36] [29 40] brown-3]
                      [[29 36] [29 40] [34 45] brown-3]
                      [[29 36] [34 45] [40 43] brown-5]
                      [[34 45] [40 43] [37 46] brown-5]
                      [[40 39] [40 43] [42 43] brown-2]
                      [[40 43] [42 43] [37 46] brown-2]
                      [[37 46] [40 46] [42 43] brown-2]
                      [[37 46] [40 50] [42 46] brown-2]
                      [[40 50] [45 52] [42 46] brown-2]
                      [[40 50] [45 52] [40 54] brown-2]
                      [[40 54] [45 52] [44 57] brown-2]


                      ;; Right ear
                      [[59 43] [59 39] [62 37] brown-1]
                      [[59 43] [62 37] [70 36] brown-1]
                      [[62 37] [72 35] [70 36] brown-1]
                      [[62 37] [72 35] [70 36] brown-1]
                      [[70 36] [70 40] [72 35] brown-3]
                      [[70 36] [70 40] [65 45] brown-3]
                      [[65 45] [59 43] [62 46] brown-5]
                      [[59 43] [65 45] [70 36] brown-5]
                      [[59 39] [57 43] [59 43] brown-2]
                      [[57 43] [59 43] [62 46] brown-2]
                      [[57 43] [62 46] [60 46.5] brown-2]
                      [[59 50] [62 46] [57 47] brown-2]    ;; 30
                      [[59 50] [55 52] [57 47] brown-2]
                      [[59 50] [55 52] [59 54] brown-2]
                      [[55 57] [55 52] [59 54] brown-2]


                      ;; Cheeks
                      [[40 54] [44 57] [40 59] brown-11]
                      [[40 59] [44 57] [45 61] brown-11]
                      [[40 59] [43 63] [47 61] brown-11]

                      [[55 57] [59 59] [59 54] brown-11]
                      [[55 57] [59 59] [55 61] brown-11]
                      [[59 59] [56 63] [51 60] brown-11]

                      ;; Mouth
                      [[47 62] [48 64] [53 62] brown-22]
                      [[48 64] [52 64] [53 62] brown-22]

                      ;; Center face
                      [[50 35] [46 36] [47 46] brown-1]
                      [[50 35] [54 36] [53 46] brown-1]
                      [[50 35] [47 46] [53 46] brown-2]
                      [[47 46] [53 46] [50 55] brown-2]
                      [[47 46] [45 52] [50 55] brown-3]
                      [[53 46] [55 52] [50 55] brown-3]
                      [[45 52] [44 57] [50 55] brown-3]
                      [[44 57] [45 61] [50 55] brown-3]
                      [[47 62] [45 61] [50 55] brown-3]
                      [[47 62] [53 62] [50 55] brown-3]
                      [[53 62] [55 61] [50 55] brown-3]
                      [[55 61] [56 57] [50 55] brown-3]
                      [[56 57] [55 52] [50 55] brown-3]

                      ;; Nose
                      [[47 55] [48 59] [46 57] "black"]
                      [[47 55] [48 59] [53 55] "black"]
                      [[53 55] [48 59] [52 59] "black"]
                      [[53 55] [54 57] [52 59] "black"]

                      ;; Eyes
                      [[41.5 49] [43.5 49] [42.5 46] "black"]
                      [[41.5 49] [40.5 46] [42.5 46] "black"]
                      [[55.5 49] [57.5 49] [56.5 46] "black"]
                      [[56.5 46] [57.5 49] [58.5 46] "black"]]})

(defn pyramid-pattern []
  (let [col-width 20
        row-height 20]
    {:width 500
     :height 500
     :polygons (for [col (range 4)
                     row (range 4)]
                 [[(* col col-width) (* row row-height)]
                  [(* (inc col) col-width) (* row row-height)]
                  [(+ (* col col-width) (/ col-width 2)) (* (inc row) row-height)]])}))


(defn pyramid-up-down-pattern []
  (let [col-width 20
        row-height 20]
    {:width 500
     :height 500
     :polygons (concat
                 (for [col (range 5)
                       row (range 5)]
                   [[(* col col-width) (* row row-height)]
                    [(* (inc col) col-width) (* row row-height)]
                    [(+ (* col col-width) (/ col-width 2)) (* (inc row) row-height)] "yellow"])
                 (for [col (range 1 5)
                       row (range 5)]
                   [[(- (* col col-width) (/ col-width 2)) (* (inc row) row-height)]
                    [(* col col-width) (* row row-height)]
                    [(+ (* col col-width) (/ col-width 2)) (* (inc row) row-height)] "orange"]))}))


(defcomponent Main [{:keys [step]}]
  [:div
   (styling (merge
              (step-style ".step1" k)
              (step-style ".step2" o)
              (step-style ".step3" d)
              (step-style ".step4" e)
              (step-style ".step5" (pyramid-pattern))
              (step-style ".step6" (pyramid-up-down-pattern))
              (step-style ".step7" my-tree)
              (step-style ".step8" deer)))
   (into
     [:div {:className (str "container " "step" step)}
      (for [x (range 1 110)]
        [:div {:id (str "p" x) :className "polygon"}])])])

(defn render []
  (dumdom/render (Main {:step 8}) (js/document.getElementById "app")))
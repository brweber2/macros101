(ns capclug.demo.macros.macro1-test
  (:use clojure.test)
  (:use capclug.demo.macros.macro1))

; go from
;
; (println \a 1 (+ 2 3) ["cheese" "doodles"])
;
; to 
;
; (println 1 ["cheese" "doodles"])
;
; by keeping every other argument


(deftest marco-evens-test
  (is (= '(println 1 ["cheese" "doodles"]) (macroexpand '(marco-evens println \a 1 (+ 2 3) ["cheese" "doodles"])))))

; go from
;
; (println \a 1 (+ 2 3) ["cheese" "doodles"])
;
; to
;
; (println \a (+ 2 3))
;
; by keeping the first item and then every other argument

(deftest marco-odds-test
  (is (= '(println \a (+ 2 3)) (macroexpand '(marco-odds println \a 1 (+ 2 3) ["cheese" "doodles"])))))

(deftest marco-odds2-test
  (is (= '(println \a (+ 2 3)) (macroexpand '(marco-odds2 println \a 1 (+ 2 3) ["cheese" "doodles"]))))
  (is (not= '(println \a (+ 2 3)) (macroexpand-1 '(marco-odds2 println \a 1 (+ 2 3) ["cheese" "doodles"]))))
  (is (= '(capclug.demo.macros.macro1/marco-evens println nil \a 1 (+ 2 3) ["cheese" "doodles"]) (macroexpand-1 '(marco-odds2 println \a 1 (+ 2 3) ["cheese" "doodles"])))))

(deftest marco-evens2-test
  (is (= '(println 1 ["cheese" "doodles"]) (macroexpand '(marco-evens2 println \a 1 (+ 2 3) ["cheese" "doodles"]))))
  (is (not= '(println 1 ["cheese" "doodles"]) (macroexpand-1 '(marco-evens2 println \a 1 (+ 2 3) ["cheese" "doodles"]))))
  (is (= '(capclug.demo.macros.macro1/marco-odds println 1 (+ 2 3) ["cheese" "doodles"]) (macroexpand-1 '(marco-evens2 println \a 1 (+ 2 3) ["cheese" "doodles"])))))

(println "macroexpand-1 odds: " (macroexpand-1 '(marco-odds2 println \a 1 (+ 2 3) ["cheese" "doodles"])))
(println "macroexpand-1 evens: " (macroexpand-1 '(marco-evens2 println \a 1 (+ 2 3) ["cheese" "doodles"])))

(run-tests)

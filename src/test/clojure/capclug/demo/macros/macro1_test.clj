(ns capclug.demo.macros.macro1-test
  (:use clojure.test)
  (:use capclug.demo.macros.macro1))

; 1) what we have
;
;           1 2    3           4
; (println \a 1 (+ 2 3) ["cheese" "doodles"])
;
; 2) what we want 
;
; (println 1 ["cheese" "doodles"])
;
; keep the even numbered arguments


(deftest marco-evens-test
  (is (= '(println 1 ["cheese" "doodles"]) (macroexpand '(marco-evens println \a 1 (+ 2 3) ["cheese" "doodles"])))))

; same test, but uses marco-evens2 which calls marco-odds
(deftest marco-evens2-test
  (is (= '(println 1 ["cheese" "doodles"]) (macroexpand '(marco-evens2 println \a 1 (+ 2 3) ["cheese" "doodles"]))))
  (is (not= '(println 1 ["cheese" "doodles"]) (macroexpand-1 '(marco-evens2 println \a 1 (+ 2 3) ["cheese" "doodles"]))))
  (is (= '(capclug.demo.macros.macro1/marco-odds println 1 (+ 2 3) ["cheese" "doodles"]) (macroexpand-1 '(marco-evens2 println \a 1 (+ 2 3) ["cheese" "doodles"])))))

; 1) what we have
;
;           1 2    3             4
; (println \a 1 (+ 2 3) ["cheese" "doodles"])
;
; 2) what we want
;
; (println \a (+ 2 3))
;
; keeping the odd numbered arguments

(deftest marco-odds-test
  (is (= '(println \a (+ 2 3)) (macroexpand '(marco-odds println \a 1 (+ 2 3) ["cheese" "doodles"])))))

; same test, but uses marco-odds2 which calls marco-evens
(deftest marco-odds2-test
  (is (= '(println \a (+ 2 3)) (macroexpand '(marco-odds2 println \a 1 (+ 2 3) ["cheese" "doodles"]))))
  (is (not= '(println \a (+ 2 3)) (macroexpand-1 '(marco-odds2 println \a 1 (+ 2 3) ["cheese" "doodles"]))))
  (is (= '(capclug.demo.macros.macro1/marco-evens println nil \a 1 (+ 2 3) ["cheese" "doodles"]) (macroexpand-1 '(marco-odds2 println \a 1 (+ 2 3) ["cheese" "doodles"])))))

(run-tests)

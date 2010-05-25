(ns capclug.demo.macros.macro2-test
  (:use clojure.test)
  (:use capclug.demo.macros.macro2))

; similar to macro1 tests, but these test for dropped metadata....  

; utility code that we use to to verify equality with metadata

(deftest meta-data-equality
  (is (= [1 2 3] #^{::donkey "kong"} [1 2 3])))

(defn compare-with-metadata [#^clojure.core/IObj a #^clojure.core/IObj b]
    (and (= (meta a) (meta b)) (= a b)))

(defn compare-list-with-metadata [list-a list-b]
  (let [size-a (count list-a) index-a (range size-a) size-b (count list-b) index-b (range size-b)] 
    (if (= size-a size-b)
      (every? true? (for [index (range size-a)]
        (compare-with-metadata (nth list-a index) (nth list-b index))))
      false)))

(deftest compare-with-metadata-test 
  (is (compare-with-metadata #^{::donkey "kong"} [1 2 3] #^{::donkey "kong"} [1 2 3]))
  (is (false? (compare-with-metadata [1 2 3] #^{::donkey "kong"} [1 2 3])))
  (is (not= {::a "b"} {::a "c"}))
  (is (= {::d "e"} {::d "e"}))
  (is (false? (compare-with-metadata #^{::donkey "shrek"} [1 2 3] #^{::donkey "kong"} [1 2 3])))
  (is (compare-list-with-metadata '(println 1 #^{::mm ::kay} [1 2]) '(println 1 #^{::mm ::kay} [1 2])))
  (is (false? (compare-list-with-metadata '(println 1 [1 2]) '(println 1 #^{::mm ::kay} [1 2])))))



; 1) what we have
;
;           1 2    3                                4
; (println \a 1 (+ 2 3) #^{:donkey "kong"} ["cheese" "doodles"])
;
; 2) what we want 
;
; (println 1 #^{:donkey "kong"} ["cheese" "doodles"])
;
; keep the even numbered arguments

(deftest marco-evens-test
  (is (compare-list-with-metadata '(println 1 #^{:donkey "kong"} ["cheese" "doodles"]) (macroexpand '(marco-evens println \a 1 (+ 2 3) #^{:donkey "kong"} ["cheese" "doodles"]))))
  (is (not (compare-list-with-metadata '(println 1 #^{:donkey "shrek"} ["cheese" "doodles"]) (macroexpand '(marco-evens println \a 1 (+ 2 3) #^{:donkey "kong"} ["cheese" "doodles"])))))
  (is (not (compare-list-with-metadata '(println 1 ["cheese" "doodles"]) (macroexpand '(marco-evens println \a 1 (+ 2 3) #^{:donkey "kong"} ["cheese" "doodles"]))))))

; same test, but uses marco-evens2 which calls marco-odds
(deftest marco-evens2-test
  (is (compare-list-with-metadata '(println 1 #^{:donkey "kong"} ["cheese" "doodles"]) (macroexpand '(marco-evens2 println \a 1 (+ 2 3) #^{:donkey "kong"} ["cheese" "doodles"]))))
  (is (not (compare-list-with-metadata '(println 1 #^{:donkey "shrek"} ["cheese" "doodles"]) (macroexpand '(marco-evens2 println \a 1 (+ 2 3) #^{:donkey "kong"} ["cheese" "doodles"])))))
  (is (not (compare-list-with-metadata '(println 1 ["cheese" "doodles"]) (macroexpand '(marco-evens2 println \a 1 (+ 2 3) #^{:donkey "kong"} ["cheese" "doodles"]))))))

; 1) what we have
;
;           1 2    3                                4
; (println \a 1 #^{"a" "b"} (+ 2 3) ["cheese" "doodles"])
;
; 2) what we want
;
; (println \a #^{"a" "b"} (+ 2 3))
;
; keep the odd numbered arguments

(deftest marco-odds-test
  (is (compare-list-with-metadata '(println \a (+ 2 3)) (macroexpand '(marco-odds println \a 1 (+ 2 3) ["cheese" "doodles"]))))
  (is (compare-list-with-metadata '(println \a #^{"a" "b"} (+ 2 3)) (macroexpand '(marco-odds println \a 1 #^{"a" "b"} (+ 2 3) ["cheese" "doodles"]))))
  (is (not (compare-list-with-metadata '(println \a (+ 2 3)) (macroexpand '(marco-odds println \a 1 #^{"a" "b"} (+ 2 3) ["cheese" "doodles"]))))))

; same test, but uses marco-odds2 which calls marco-evens
(deftest marco-odds2-test
  (is (compare-list-with-metadata '(println \a (+ 2 3)) (macroexpand '(marco-odds2 println \a 1 (+ 2 3) ["cheese" "doodles"]))))
  (is (compare-list-with-metadata '(println \a #^{"a" "b"} (+ 2 3)) (macroexpand '(marco-odds2 println \a 1 #^{"a" "b"} (+ 2 3) ["cheese" "doodles"]))))
  (is (not (compare-list-with-metadata '(println \a (+ 2 3)) (macroexpand '(marco-odds2 println \a 1 #^{"a" "b"} (+ 2 3) ["cheese" "doodles"]))))))

(run-tests)


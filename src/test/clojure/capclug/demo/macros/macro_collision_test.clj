(ns capclug.demo.macros.macro-collision-test
  (:use clojure.test)
  (:use capclug.demo.macros.macro-collision))

(deftest collision-test
    (def foo 14)
    (is (= foo 14))
    (collision 16)
    (is (= foo 16))
  )

(deftest no-collision-test
    (def foo 14)
    (is (= foo 14))
    (no-collision 16)
    (is (= foo 14))
  )

(run-tests)



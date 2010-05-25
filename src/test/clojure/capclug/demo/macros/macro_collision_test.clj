(ns capclug.demo.macros.macro-collision-test
  (:use clojure.test)
  (:use capclug.demo.macros.macro-collision))

; sometimes the simplest looking things are the most complex...
; or at least, they appear to be the most complex. :)

; calling collision should change the value of an immutable value 'foo' (BAD!)
(deftest collision-test
    (def foo 14)
    (is (= foo 14))
    (collision 16)
    (is (= foo 16)))

; calling no-collision should NOT change the value of the immutable value 'foo'
(deftest no-collision-test
    (def foo 14)
    (is (= foo 14))
    (no-collision 16)
    (is (= foo 14)))

(run-tests)



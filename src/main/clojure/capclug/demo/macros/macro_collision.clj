(ns capclug.demo.macros.macro-collision)

; sets foo to value of x
(defmacro collision [x] 
  `(def ~'foo ~x))

; sets generated variable to value of x
(defmacro no-collision [x]
  `(def foo# ~x))


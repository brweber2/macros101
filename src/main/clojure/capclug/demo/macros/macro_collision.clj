(ns capclug.demo.macros.macro-collision)

(defmacro collision [x] 
  `(def ~'foo ~x))

(defmacro no-collision [x]
  `(def foo# ~x))


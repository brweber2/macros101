(odds println \a 1 (+ 2 3) "foo" [1 3 2])

should expand to

(println a 5 [1 3 2])

(lazy-odds println \a 1 (+ 2 3) "foo" [1 3 2])

should expand to

(println a (+ 2 3) [1 3 2])

IS THERE A DIFFERENCE???????

(println "Hi")
(println a nil [1 3 2])

(println a (println "Hi") [1 3 2])

ARE THEY THE SAME?????
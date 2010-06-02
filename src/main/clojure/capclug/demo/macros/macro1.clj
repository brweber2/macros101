(ns capclug.demo.macros.macro1)

; let's just call all my macros 'marco' b/c I type that anyway....

; note that these macros drop metadata
; see macros2.clj for code that keeps the metadata intact

(defmacro marco-evens [& clauses]
  "calls the function only passing in the even arguments."
  `(~@(for [index (range (count clauses)) :when (even? index)]
    (nth clauses index))))

(defmacro marco-odds [function-name & clauses]
  "calls the function only passing in the odd arguments."
  `(~function-name
    ~@(for [index (range (count clauses)) :when (even? index)]
        (nth clauses index))))

(defmacro marco-odds2 [function-name & clauses]
  "calls the function only passing in the odd arguments.
  uses the even macro for maximum code reuse."
  `(marco-evens ~function-name ~@(cons nil clauses))) ; need to insert an extra nil arg

(defmacro marco-evens2 [& clauses]
  "calls the function only passing in the even arguments.
  uses the odd macro for maximum code reuse."
  `(marco-odds ~(first clauses) ~@(rest (rest clauses)))) ; need to get rid of function name and first arg

; here is an even better solution given by Mike Hogye at #capclug in May, 2010
(defmacro pass-even-args [f & args]
	`(~f ~@(take-nth 2 args)))


(defmacro pass-odd-args [f & args]
	`(~f ~@(take-nth 2 (drop 1 args))))
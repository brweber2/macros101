(ns capclug.demo.macros.macro1)

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
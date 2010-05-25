Unit tests (and answers) for three challenges that are part of a presentation.

The presentation "Macros 101" can be found at:
https://docs.google.com/present/edit?id=0AWA9IUA1REQAZGc2d2d6Ml8xM25oZ2Q2cGRk&hl=en

The challenges are:

1)Write a macro that calls uses the first parameter as the function name and 
then passes only the even numbered remaining parameters.

Example:
                            1   2    3    4    5
(macro-name function-name arg0 arg1 arg2 arg3 arg4)

would expand to

(function-name arg1 arg3)

2) Same thing as number one, but only the odd parameters.

Example:
                            1   2    3    4    5
(macro-name function-name arg0 arg1 arg2 arg3 arg4)

would expand to

(function-name arg0 arg2 arg4)

3) Write a macro that would cause a variable name collision (variable capture).  
Show why gensym is necessary/helpful in Clojure (and still not perfect...).
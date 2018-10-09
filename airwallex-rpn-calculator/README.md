# RPN Calculator
* Command line RPN Calculator, uses space as delimiter.
* Numbers are pushed on to the stack. Operators operate on numbers that are on the stack.
  
  Available operators are +, -, *, /, sqrt, pow, undo, clear
  
  Operators pop their parameters off the stack, and push their results back onto the stack.
  * The 'clear' operator removes all items from the stack.
   
  * The 'undo' operator undoes the previous operation. "undo undo" will undo the previous two operations.
   
  * 'sqrt' performs a square root on the top item from the stack
   
  * 'pow' returns the top item from the stack power 2
   
   * The +, -, *, / operators perform addition, subtraction, multiplication and division respectively on the top two items from the stack.
* If an operator cannot find a sufficient number of parameters on the stack, a warning is displayed:
  ``` operator <operator> (position: <pos>): insufficient parameters ```
    
# Compile and Run
* Compile: mvn compile
* Run: mvn exec:java
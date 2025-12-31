# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

 def divide_by_zero
   # ruleid: ruby_error_rule-DivideByZero
   3/0
   # ruleid: ruby_error_rule-DivideByZero
   oops = 4/0
   variable = 3
   # ruleid: ruby_error_rule-DivideByZero
   oops = variable / 0

   zero = 0
   # ruleid: ruby_error_rule-DivideByZero
   bad = variable/zero

   # ok: ruby_error_rule-DivideByZero
   ok = 1.0 / 0
   # ok: ruby_error_rule-DivideByZero
   ok2 = 2.0 / zero
   
 end
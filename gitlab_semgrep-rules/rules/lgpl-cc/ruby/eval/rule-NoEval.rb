# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

# ruleid:ruby_eval_rule-NoEval
Array.class_eval(cookies['tainted_cookie'])

def zen
  41
end

# ok:ruby_eval_rule-NoEval
eval("def zen; 42; end")

puts zen

class Thing
end
a = %q{def hello() "Hello there!" end}
# not user-controllable, this is ok
# ok:ruby_eval_rule-NoEval
Thing.module_eval(a)
puts Thing.new.hello()
b = params['something']
# ruleid:ruby_eval_rule-NoEval
Thing.module_eval(b)

# ruleid:ruby_eval_rule-NoEval
eval(b)
# ruleid:ruby_eval_rule-NoEval
eval(b,some_binding)

def get_binding(param)
  binding
end
b = get_binding("hello")
# ok:ruby_eval_rule-NoEval
b.eval("some_func")

# ok:ruby_eval_rule-NoEval
eval("some_func",b)

# ruleid:ruby_eval_rule-NoEval
eval(params['cmd'],b)

# ruleid:ruby_eval_rule-NoEval
eval(params.dig('cmd'))

# ruleid:ruby_eval_rule-NoEval
eval(cookies.delete('foo'))

# ruleid:ruby_eval_rule-NoEval
RubyVM::InstructionSequence.compile(foo).eval

# ok:ruby_eval_rule-NoEval
RubyVM::InstructionSequence.compile("1 + 2").eval

iseq = RubyVM::InstructionSequence.compile(foo)
# ruleid:ruby_eval_rule-NoEval
iseq.eval


iseq = RubyVM::InstructionSequence.compile('num = 1 + 2')
# ok:ruby_eval_rule-NoEval
iseq.eval

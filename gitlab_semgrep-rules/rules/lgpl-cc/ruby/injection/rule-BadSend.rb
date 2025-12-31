# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

def bad_send
    # ruleid: ruby_injection_rule-BadSend
    method = params[:method]
    @result = User.send(method.to_sym)
end

def ok_send
    # ok: ruby_injection_rule-BadSend
    method = params[:method] == 1 ? :method_a : :method_b
    @result = User.send(method, *args)
end
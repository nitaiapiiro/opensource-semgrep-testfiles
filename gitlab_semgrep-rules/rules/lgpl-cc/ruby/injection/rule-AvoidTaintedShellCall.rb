# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

def foo

  # ruleid: ruby_injection_rule-AvoidTaintedShellCall
  Shell.cat(params[:filename])

  sh = Shell.cd("/tmp")
  # ruleid: ruby_injection_rule-AvoidTaintedShellCall
  sh.open(params[:filename])

  sh = Shell.new
  fn = params[:filename]
  # ruleid: ruby_injection_rule-AvoidTaintedShellCall
  sh.open(fn)

  # ok: ruby_injection_rule-AvoidTaintedShellCall
  Shell.cat("/var/log/www/access.log")

end

def foo2(param1)
  # ok: ruby_injection_rule-AvoidTaintedShellCall
  new(params).call
end

def foo3(param1, param2, param3)
  # ok: ruby_injection_rule-AvoidTaintedShellCall
  new(param1, params2, param3).execute
end

def foo4(param1, param2)
  # ok: ruby_injection_rule-AvoidTaintedShellCall
  new(param1, param2).execute
end

def foo5(param1, param2, param3)
  # ok: ruby_injection_rule-AvoidTaintedShellCall
  new(param1, param2, param3).execute
end
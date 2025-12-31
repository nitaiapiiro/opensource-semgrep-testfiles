# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

def some_rails_controller
  foo = params[:some_regex]
  #ruleid: ruby_regex_rule-CheckRegexDOS
  Regexp.new(foo).match("some_string")
end

def some_rails_controller
  foo = Record[something]
  #ruleid: ruby_regex_rule-CheckRegexDOS
  Regexp.new(foo).match("some_string")
end

def some_rails_controller
  foo = Record.read_attribute("some_attribute")
  #ruleid: ruby_regex_rule-CheckRegexDOS
  Regexp.new(foo).match("some_string")

  bar = ENV['someEnvVar']
  #ok: ruby_regex_rule-CheckRegexDOS
  Regexp.new(bar).match("some_string")
end

def use_params_in_regex
#ruleid: ruby_regex_rule-CheckRegexDOS
@x = something.match /#{params[:x]}/
end

def regex_on_params
#ok: ruby_regex_rule-CheckRegexDOS
@x = params[:x].match /foo/
end
# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

class HomeController < ApplicationController

  def unsafe_reflection # not that safe
    table = params["table"]
    # ruleid: ruby_reflection_rule-CheckUnsafeReflection
    model = table.classify.constantize
    @result = model.send(:method)
  end

  # safe
  def ok_reflection
    foo = "SomeClass"
    #ok: ruby_reflection_rule-CheckUnsafeReflection
    foo.classify.constantize
  end

  def test_more_send_methods
    User.try(params[:meth])
    self.__send__(params[:meth])
    Account.public_send(params[:meth])

    table = params["table"]
    # ruleid: ruby_reflection_rule-CheckUnsafeReflection
    table.classify.constantize.try(:meth)
  end

end
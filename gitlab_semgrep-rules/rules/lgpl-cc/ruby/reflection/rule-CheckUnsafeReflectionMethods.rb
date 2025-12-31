# License: Commons Clause License Condition v1.0[LGPL-2.1-only]
class GroupsController < ApplicationController

  def dynamic_method_invocations
    # ruleid: ruby_reflection_rule-CheckUnsafeReflectionMethods
    params[:method].to_sym.to_proc.call(Kernel)
    # ruleid: ruby_reflection_rule-CheckUnsafeReflectionMethods
    (params[:klass].to_s).method(params[:method]).(params[:argument])
    # ruleid: ruby_reflection_rule-CheckUnsafeReflectionMethods
    Kernel.tap(&params[:method].to_sym)
    User.method("#{User.first.some_method_thing}_stuff")
    user_input_value = params[:my_user_input]
    # ruleid: ruby_reflection_rule-CheckUnsafeReflectionMethods
    anything.tap(&user_input_value.to_sym)
    # ruleid: ruby_reflection_rule-CheckUnsafeReflectionMethods
    anything_else.tap { |thing| thing + user_input_value() }
  end

  def dynamic_method_invocations_ok
    # ok: ruby_reflection_rule-CheckUnsafeReflectionMethods
    "SomeClass".to_sym.to_proc.call(Kernel)
    # ok: ruby_reflection_rule-CheckUnsafeReflectionMethods
    SomeClass.method("some_method").("some_argument")
    # ok: ruby_reflection_rule-CheckUnsafeReflectionMethods
    Kernel.tap("SomeClass".to_sym)
    user_input_value = params[:my_user_input]
    # ok: ruby_reflection_rule-CheckUnsafeReflectionMethods
    user_input_value.tap("some_method")
  end

end
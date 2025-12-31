# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

 def bad_deserialization
   data = request.env[:name]
   # ruleid: ruby_deserialization_rule-BadDeserializationEnv
   obj = Marshal.load(data)
   # ruleid: ruby_deserialization_rule-BadDeserializationEnv
   obj = Marshal.restore(data)

   o = Klass.new("hello\n")
   data = request.env[:name]
   # ruleid: ruby_deserialization_rule-BadDeserializationEnv
   obj = Oj.object_load(data)
   # ruleid: ruby_deserialization_rule-BadDeserializationEnv
   obj = Oj.load(data)
   # ok: ruby_deserialization_rule-BadDeserializationEnv
   obj = Oj.load(data,options=some_safe_options)
 end

 def ok_deserialization
    
    data = get_safe_data()
    # ok: ruby_deserialization_rule-BadDeserializationEnv
    obj = Marshal.load(data)
 end

 def safe_marshal_restore_test
    safe_data = Base64.encode64(Marshal.dump("Safe test string for Marshal.restore"))
    # ok: ruby_deserialization_rule-BadDeserializationEnv
    obj = Marshal.restore(Base64.decode64(safe_data))
    render plain: "Safe Marshal.restore executed with #{obj}"
  end

  def safe_oj_object_load_test
    safe_data = Oj.dump({ message: "Safe test string for Oj.object_load" }, mode: :strict)
    # ok: ruby_deserialization_rule-BadDeserializationEnv
    obj = Oj.object_load(safe_data)
    render plain: "Safe Oj.object_load executed with message: #{obj['message']}"
  end

  def safe_marshal_load_test
    safe_data = Base64.encode64(Marshal.dump("Safe test string for Marshal"))
    # ok: ruby_deserialization_rule-BadDeserializationEnv
    obj = Marshal.load(Base64.decode64(safe_data))
    render plain: "Safe Marshal.load executed with #{obj}"
  end

  def safe_oj_load_test
    safe_data = Oj.dump({ message: "Safe test string for Oj" }, mode: :strict)
    # ok: ruby_deserialization_rule-BadDeserializationEnv
    obj = Oj.load(safe_data)
    render plain: "Safe Oj.load executed with message: #{obj['message']}"
  end

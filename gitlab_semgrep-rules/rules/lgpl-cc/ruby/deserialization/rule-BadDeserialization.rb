# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

 def bad_deserialization
    o = Klass.new("hello\n")
    data = params['data']
    # ruleid: ruby_deserialization_rule-BadDeserialization
    obj = Marshal.load(data)
    # ruleid: ruby_deserialization_rule-BadDeserialization 
    obj = Marshal.restore(data)

    o = Klass.new("hello\n")
    data = YAML.dump(o)
    # ok: ruby_deserialization_rule-BadDeserialization
    obj = YAML.load(data)

    o = Klass.new("hello\n")
    data = cookies['some_field']
    # ruleid: ruby_deserialization_rule-BadDeserialization
    obj = Oj.object_load(data)
    # ruleid: ruby_deserialization_rule-BadDeserialization
    obj = Oj.load(data)
    # ok: ruby_deserialization_rule-BadDeserialization
    obj = Oj.load(data,options=some_safe_options)
 end

 def safe_marshal_restore_test
    safe_data = Base64.encode64(Marshal.dump("Safe test string for Marshal.restore"))
    # ok: ruby_deserialization_rule-BadDeserialization
    obj = Marshal.restore(Base64.decode64(safe_data))
    render plain: "Safe Marshal.restore executed with #{obj}"
  end

  def safe_oj_object_load_test
    safe_data = Oj.dump({ message: "Safe test string for Oj.object_load" }, mode: :strict)
    # ok: ruby_deserialization_rule-BadDeserialization
    obj = Oj.object_load(safe_data)
    render plain: "Safe Oj.object_load executed with message: #{obj['message']}"
  end

  def safe_marshal_load_test
    safe_data = Base64.encode64(Marshal.dump("Safe test string for Marshal"))
    # ok: ruby_deserialization_rule-BadDeserialization
    obj = Marshal.load(Base64.decode64(safe_data))
    render plain: "Safe Marshal.load executed with #{obj}"
  end

  def safe_oj_load_test
    safe_data = Oj.dump({ message: "Safe test string for Oj" }, mode: :strict)
    # ok: ruby_deserialization_rule-BadDeserialization
    obj = Oj.load(safe_data)
    render plain: "Safe Oj.load executed with message: #{obj['message']}"
  end

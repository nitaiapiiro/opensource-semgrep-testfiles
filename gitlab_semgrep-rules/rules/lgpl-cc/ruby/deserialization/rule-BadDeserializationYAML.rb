# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

 def bad_deserialization

    o = Klass.new("hello\n")
    data = YAML.dump(o)
    # ruleid: ruby_deserialization_rule-BadDeserializationYAML
    obj = YAML.load(data)
    # ruleid: ruby_deserialization_rule-BadDeserializationYAML
    obj = YAML.load_stream(data)
    # ruleid: ruby_deserialization_rule-BadDeserializationYAML
    obj = YAML.parse_stream(data)

 end

 def ok_deserialization
    o = Klass.new("hello\n")
    data = YAML.dump(o)
    # ok: ruby_deserialization_rule-BadDeserializationYAML
    obj = YAML.load(data, safe: true)
    # ok: ruby_deserialization_rule-BadDeserializationYAML
    obj = YAML.load_stream(data, safe: true)
    # ok: ruby_deserialization_rule-BadDeserializationYAML
    obj = YAML.parse_stream(data, safe: true)

    filename = File.read("test.txt")
    data = YAML.dump(filename)
    # ok: ruby_deserialization_rule-BadDeserializationYAML
    YAML.load(filename)
    # ok: ruby_deserialization_rule-BadDeserializationYAML
    YAML.load_stream(filename)
    # ok: ruby_deserialization_rule-BadDeserializationYAML
    YAML.parse_stream(filename)

    # ok: ruby_deserialization_rule-BadDeserializationYAML
    YAML.load(File.read("test.txt"))
    # ok: ruby_deserialization_rule-BadDeserializationYAML
    YAML.load_stream(File.read("test.txt"))
    # ok: ruby_deserialization_rule-BadDeserializationYAML
    YAML.parse_stream(File.read("test.txt"))
 end
#!/usr/bin/env ruby

require 'json'
require 'yaml'
require 'json-schema'

SCHEMA_PATH="ci/mappings-schema.json"

def validate_yaml_against_schema(yaml_file, schema_file)
  yaml_data = YAML.load_file(yaml_file)
  schema_data = JSON.parse(File.read(schema_file))
  JSON::Validator.fully_validate(schema_data, yaml_data)
end

errors = []
Dir.glob('./mappings/*.yml').each do |mapping_path|
  errors.append(validate_yaml_against_schema(mapping_path, SCHEMA_PATH))
end

errors.flatten!

unless errors.empty?
  puts "Validation errors:"
  puts errors.join("\n")
  exit 1
end

puts "YAML file is valid against the schema."

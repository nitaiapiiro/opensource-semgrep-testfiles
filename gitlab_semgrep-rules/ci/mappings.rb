#!/usr/bin/env ruby

require 'yaml'

module MappingCheck
  def self.run
    ok = true
    Dir.glob('./mappings/*.yml').each do |file|
      mappings = YAML.load(File.read(file))
      base = File.basename(file, ".yml")
      mappings[base]['mappings'].each do |mp|
        id = mp['id']
        mp['rules'].each do |rule|
          fname = "#{rule['path']}.yml"

          unless File.exist?(fname)
            puts("#{fname} that corresponds to #{id} does not exist")
            ok = false
          end
        end
      end
    end
    ok
  end
end

if MappingCheck.run
  exit(0)
else
  exit(-1)
end

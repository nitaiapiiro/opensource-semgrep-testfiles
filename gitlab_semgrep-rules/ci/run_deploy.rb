#!/usr/bin/env ruby
# This script consolidates all yaml files to a single rule files

require_relative 'deploy'

Deploy.new('mappings', '.', 'dist', ARGV[0]).run

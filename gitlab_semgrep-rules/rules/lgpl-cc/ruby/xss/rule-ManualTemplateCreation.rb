# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

require 'erb'

class FaxHelper

  def to_fax
    html = File.open(path_to_template).read
    # ruleid: ruby_xss_rule-ManualTemplateCreation
    template = ERB.new(html)
    template.result
  end

end


x = 42
# ruleid: ruby_xss_rule-ManualTemplateCreation
template = ERB.new <<-EOF
  The value of x is: <%= x %>
EOF
puts template.result(binding)
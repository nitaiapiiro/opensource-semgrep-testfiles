# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

 def bad_escape
     # ruleid: ruby_escaping_rule-JSONEntityEscape
     ActiveSupport.escape_html_entities_in_json = false
 end

 def ok_escape
     # ok: ruby_escaping_rule-JSONEntityEscape
     ActiveSupport.escape_html_entities_in_json = true
 end
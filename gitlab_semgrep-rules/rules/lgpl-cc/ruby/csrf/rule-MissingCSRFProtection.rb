# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

# ruleid: ruby_csrf_rule-MissingCSRFProtection
class DangerousController < ActionController::Base

  puts "do more stuff"

end

# ok: ruby_csrf_rule-MissingCSRFProtection
class OkController < ActionController::Base

  protect_from_forgery :with => :exception

  puts "do more stuff"

end

# ok: ruby_csrf_rule-MissingCSRFProtection
class OkController < ActionController::Base

  protect_from_forgery prepend: true, with: :exception

  puts "do more stuff"

end
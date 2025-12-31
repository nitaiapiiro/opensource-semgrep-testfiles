# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

 def bad_ssl
    # ruleid: ruby_ssl_rule-ForceSSLFalse
    config.force_ssl = false
 end

 def ok_ssl
    # ok: ruby_ssl_rule-ForceSSLFalse
    config.force_ssl = true
 end
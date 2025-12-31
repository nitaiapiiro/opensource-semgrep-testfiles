# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

#rails2
ActionController::Base.session = {
  :key         => '_rails2_session',
  :secret      => 'secret!',
  # ruleid: ruby_cookie_rule-CheckCookieStoreSessionSecurityAttributes
  :session_http_only   => false
}

#rails3
# ruleid: ruby_cookie_rule-CheckCookieStoreSessionSecurityAttributes
Rails3::Application.config.session_store :cookie_store, :key => '_rails3_session', :httponly => false, :secure => false

#rails3
# ruleid: ruby_cookie_rule-CheckCookieStoreSessionSecurityAttributes
Rails3::Application.config.session_store :cookie_store, :key => '_rails3_session', :secure => false

#rails3
# ruleid: ruby_cookie_rule-CheckCookieStoreSessionSecurityAttributes
Rails3::Application.config.session_store :cookie_store, :httponly => false, :key => '_rails3_session'

#rails3
# ruleid: ruby_cookie_rule-CheckCookieStoreSessionSecurityAttributes
Rails.application.config.session_store :cookie_store, key: '_rails3_session', httponly: false, domain: :all

# ruleid: ruby_cookie_rule-CheckCookieStoreSessionSecurityAttributes
Rails.application.config.session_store :cookie_store, httponly: false

# ok: ruby_cookie_rule-CheckCookieStoreSessionSecurityAttributes
Rails.application.config.session_store :cookie_store, some_harmless_key: false

# ruleid: ruby_cookie_rule-CheckCookieStoreSessionSecurityAttributes
MyRailsApp::Application.config.session_store :cookie_store, httponly: false

# ruleid: ruby_cookie_rule-CheckCookieStoreSessionSecurityAttributes
MyRailsApp.application.config.session_store :cookie_store, httponly: false
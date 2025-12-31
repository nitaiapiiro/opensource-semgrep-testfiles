# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

class Bad_cookie_serialization
  # ruleid: ruby_cookie_rule-CookieSerialization
  Rails.application.config.action_dispatch.cookies_serializer = :hybrid
  # ruleid: ruby_cookie_rule-CookieSerialization
  Rails.application.config.action_dispatch.cookies_serializer = :marshal
end

class Cookie_serialization
  # ok: ruby_cookie_rule-CookieSerialization
  Rails.application.config.action_dispatch.cookies_serializer = :json
end
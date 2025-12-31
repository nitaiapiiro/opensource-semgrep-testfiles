# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

# ruleid: ruby_xss_rule-AvoidLinkTo
link_to "#{params[:url]}/profile", profile_path(@profile)

# ruleid: ruby_xss_rule-AvoidLinkTo
link_to "#{h(cookies[:url])}/profile", profile_path(@profile)

url = request.env[:url]
# ruleid: ruby_xss_rule-AvoidLinkTo
link_to url, profile_path(@profile)

# ruleid: ruby_xss_rule-AvoidLinkTo
link_to "#{h(User.url(x))}/profile", profile_path(@profile)

# ok: ruby_xss_rule-AvoidLinkTo
link_to "Profile#{params[:url]}", profile_path(@profile)

# ok: ruby_xss_rule-AvoidLinkTo
link_to "Profile", profile_path(@profile)
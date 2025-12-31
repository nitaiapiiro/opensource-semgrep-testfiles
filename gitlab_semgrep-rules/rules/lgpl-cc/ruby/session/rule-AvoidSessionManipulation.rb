# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

# ruleid: ruby_session_rule-AvoidSessionManipulation
id = session[params[:uid]]

uid = params[:uid]
# ruleid: ruby_session_rule-AvoidSessionManipulation
id = session[uid]

# ok: ruby_session_rule-AvoidSessionManipulation
id = session[user_id]
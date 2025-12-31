# License: Commons Clause License Condition v1.0[LGPL-2.1-only]
class BadController < ApplicationController
  #Examples of skipping important filters with a blacklist instead of whitelist
  # ruleid: ruby_filter_rule-CheckBeforeFilter
  skip_before_filter :login_required, :except => :do_admin_stuff
  # ruleid: ruby_filter_rule-CheckBeforeFilter
  skip_filter :authenticate_user!, :except => :do_admin_stuff
  # ruleid: ruby_filter_rule-CheckBeforeFilter
  skip_before_filter :require_user, :except => [:do_admin_stuff, :do_other_stuff]

    def do_admin_stuff
        #do some stuff
    end
    
    def do_anonymous_stuff
      # do some stuff
    end
end

class GoodController < ApplicationController
  #Examples of skipping important filters with a blacklist instead of whitelist
  # ok: ruby_filter_rule-CheckBeforeFilter
  skip_before_filter :login_required, :only => :do_anonymous_stuff
  # ok: ruby_filter_rule-CheckBeforeFilter
  skip_filter :authenticate_user!, :only => :do_anonymous_stuff
  # ok: ruby_filter_rule-CheckBeforeFilter
  skip_before_filter :require_user, :only => [:do_anonymous_stuff, :do_nocontext_stuff]

    def do_admin_stuff
        #do some stuff
    end
    
    def do_anonymous_stuff
      # do some stuff
    end

    def do_nocontext_stuff
      # do some stuff
    end
end
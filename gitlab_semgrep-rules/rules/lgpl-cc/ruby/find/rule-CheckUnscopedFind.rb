# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

class GroupsController < ApplicationController

  def show
    #ruleid: ruby_find_rule-CheckUnscopedFind
    @user = User.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render :json => @user }
    end
  end

  def show_ok
    #ok: ruby_find_rule-CheckUnscopedFind
    @user = User.find(session[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render :json => @user }
    end
  end

  def show_ok2
    #ok: ruby_find_rule-CheckUnscopedFind
    current_user = User.find(session[:id])
    #ok: ruby_find_rule-CheckUnscopedFind
    current_user.accounts.find(param[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render :json => @user }
    end
  end

  def get
    #ruleid: ruby_find_rule-CheckUnscopedFind
    @some_record = SomeRecord.find_by_id!(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render :json => @user }
    end
  end

end
# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

def mass_assign_unsafe
    #ruleid: ruby_mass_assignment_rule-UnprotectedMassAssign
    User.new(params[:user])
    #ruleid: ruby_mass_assignment_rule-UnprotectedMassAssign
    user = User.new(params[:user])
    #ruleid: ruby_mass_assignment_rule-UnprotectedMassAssign
    User.new(params[:user], :without_protection => true)
end

def safe_send
    #ok: ruby_mass_assignment_rule-UnprotectedMassAssign
    attr_accessible :name
    User.new(params[:user])

    #ok: ruby_mass_assignment_rule-UnprotectedMassAssign
    attr_accessible :name
    user = User.new(params[:user])
end
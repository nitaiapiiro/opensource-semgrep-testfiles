# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

class Bad_attr_accessible
   include  ActiveModel::MassAssignmentSecurity

   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   attr_accessible :name, :admin,
                   :telephone, as: :create_params
   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   attr_accessible :name, :banned,
                   as: :create_params
   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   attr_accessible :role,
                   :telephone, as: :create_params
   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   attr_accessible :name,
                   :account_id, as: :create_params

   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   User.new(params.permit(:name, :admin))
   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   params_with_conditional_require(ctrl.params).permit(:name, :age, :admin)

   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   User.new(params.permit(:role))
   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   params_with_conditional_require(ctrl.params).permit(:name, :age, :role)

   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   User.new(params.permit(:banned, :name))
   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   params_with_conditional_require(ctrl.params).permit(:banned, :name, :age)

   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   User.new(params.permit(:address, :account_id, :age))
   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   params_with_conditional_require(ctrl.params).permit(:name, :account_id, :age)

   # ruleid: ruby_mass_assignment_rule-ModelAttrAccessible
   params.permit!
end

class Ok_attr_accessible
   # ok: ruby_mass_assignment_rule-ModelAttrAccessible
   attr_accessible :name, :address, :age,
                   :telephone, as: :create_params
   # ok: ruby_mass_assignment_rule-ModelAttrAccessible
   User.new(params.permit(:address, :acc, :age))
   # ok: ruby_mass_assignment_rule-ModelAttrAccessible
   params_with_conditional_require(ctrl.params).permit(:name, :address, :age)
end
let context = LAContext()
//ruleid: rules_lgpl_swift_other_rule-ios-biometric-acl
if context.biometryType == .touchID || context.biometryType == .faceID || context.biometryType == .biometryAny {
    print("Biometric authentication is available")
}

var accessControl: SecAccessControl?
//ruleid: rules_lgpl_swift_other_rule-ios-biometric-acl
let flags: SecAccessControlCreateFlags = [.biometryAny, .userPresence]
//ruleid: rules_lgpl_swift_other_rule-ios-biometric-acl
let status = SecAccessControlCreateWithFlags(kCFAllocatorDefault,
                                             kSecAttrAccessibleWhenUnlockedThisDeviceOnly,
                                             flags,
                                             &accessControl)
package godynamicmethodinvocation

import (
	"fmt"
	"reflect"
)

type Service struct {
	Name string
}

func (s Service) PublicMethod(arg string) string {
	return fmt.Sprintf("Public: %s - %s", s.Name, arg)
}

func (s Service) AnotherMethod(args ...string) string {
	return fmt.Sprintf("Variadic: %v", args)
}

func (s *Service) PointerMethod(arg int) int {
	return arg * 2
}

type Handler struct {
	Service Service
}

func (h Handler) Execute(command string) string {
	return "Executed: " + command
}

// Direct method invocation patterns
func directMethodInvocation() {
	svc := Service{Name: "TestService"}

	// Pattern: reflect.ValueOf($OBJ).MethodByName($NAME).Call($ARGS) - Line 37
	result := reflect.ValueOf(svc).MethodByName("PublicMethod").Call([]reflect.Value{reflect.ValueOf("test")})

	// Pattern: reflect.ValueOf($OBJ).MethodByName($NAME).CallSlice($ARGS) - Line 40
	args := []reflect.Value{reflect.ValueOf([]string{"arg1", "arg2"})}
	reflect.ValueOf(svc).MethodByName("AnotherMethod").CallSlice(args)

	_ = result
}

// Deferred invocation with stored method reference
func deferredInvocation() {
	svc := Service{Name: "TestService"}

	// Pattern: $VAL := reflect.ValueOf($OBJ); ...; $M := $VAL.MethodByName($NAME); ...; $M.Call($ARGS) - Line 52
	val := reflect.ValueOf(svc)
	// Some logic here
	method := val.MethodByName("PublicMethod")
	// More logic
	result := method.Call([]reflect.Value{reflect.ValueOf("deferred")})

	// Pattern: CallSlice variant - Line 59
	val2 := reflect.ValueOf(svc)
	variadicMethod := val2.MethodByName("AnotherMethod")
	variadicMethod.CallSlice([]reflect.Value{reflect.ValueOf([]string{"a", "b"})})

	_ = result
}

// Method invocation with IsValid guard (stored)
func methodWithIsValidGuard() {
	svc := Service{Name: "TestService"}
	methodName := "PublicMethod"

	// Pattern: $M := reflect.ValueOf($OBJ).MethodByName($NAME); if $M.IsValid() { ... $M.Call($ARGS) } - Line 72
	method := reflect.ValueOf(svc).MethodByName(methodName)
	if method.IsValid() {
		method.Call([]reflect.Value{reflect.ValueOf("guarded")})
	}

	// Pattern: CallSlice variant with guard - Line 78
	variadicMethod := reflect.ValueOf(svc).MethodByName("AnotherMethod")
	if variadicMethod.IsValid() {
		variadicMethod.CallSlice([]reflect.Value{reflect.ValueOf([]string{"x", "y"})})
	}
}

// Inline if with IsValid guard
func inlineIsValidGuard() {
	svc := Service{Name: "TestService"}

	// Pattern: if $M := reflect.ValueOf($OBJ).MethodByName($NAME); $M.IsValid() { ... $M.Call($ARGS) } - Line 89
	if method := reflect.ValueOf(svc).MethodByName("PublicMethod"); method.IsValid() {
		method.Call([]reflect.Value{reflect.ValueOf("inline")})
	}

	// Pattern: CallSlice inline variant - Line 94
	if method := reflect.ValueOf(svc).MethodByName("AnotherMethod"); method.IsValid() {
		method.CallSlice([]reflect.Value{reflect.ValueOf([]string{"1", "2", "3"})})
	}
}

// Method invocation on field
func methodOnField() {
	handler := Handler{Service: Service{Name: "FieldService"}}

	// Pattern: reflect.ValueOf($OBJ).FieldByName($FIELD).MethodByName($NAME).Call($ARGS) - Line 105
	result := reflect.ValueOf(handler).FieldByName("Service").MethodByName("PublicMethod").Call([]reflect.Value{reflect.ValueOf("field")})

	// Pattern: CallSlice on field - Line 108
	reflect.ValueOf(handler).FieldByName("Service").MethodByName("AnotherMethod").CallSlice([]reflect.Value{reflect.ValueOf([]string{"a"})})

	_ = result
}

// Method by index
func methodByIndex() {
	svc := Service{Name: "IndexService"}

	// Pattern: reflect.ValueOf($OBJ).Method($IDX).Call($ARGS) - Line 119
	method := reflect.ValueOf(svc).Method(0)
	method.Call([]reflect.Value{reflect.ValueOf("by-index")})

	// Pattern: CallSlice by index - Line 123
	method2 := reflect.ValueOf(svc).Method(1)
	method2.CallSlice([]reflect.Value{reflect.ValueOf([]string{"idx"})})
}

// Invocation via Elem (for pointers)
func invocationViaElem() {
	svc := &Service{Name: "PointerService"}

	// Pattern: reflect.ValueOf($OBJ).Elem().MethodByName($NAME).Call($ARGS) - Line 133
	reflect.ValueOf(svc).Elem().MethodByName("PublicMethod").Call([]reflect.Value{reflect.ValueOf("elem")})

	// Pattern: CallSlice via Elem - Line 136
	reflect.ValueOf(svc).Elem().MethodByName("AnotherMethod").CallSlice([]reflect.Value{reflect.ValueOf([]string{"elem-variadic"})})
}

// Invocation via Indirect
func invocationViaIndirect() {
	svc := &Service{Name: "IndirectService"}
	val := reflect.ValueOf(svc)

	// Pattern: reflect.Indirect($VALUE).MethodByName($NAME).Call($ARGS) - Line 146
	reflect.Indirect(val).MethodByName("PublicMethod").Call([]reflect.Value{reflect.ValueOf("indirect")})

	// Pattern: CallSlice via Indirect - Line 149
	reflect.Indirect(val).MethodByName("AnotherMethod").CallSlice([]reflect.Value{reflect.ValueOf([]string{"indirect-variadic"})})
}

// Complex real-world patterns
func complexPatterns() {
	svc := Service{Name: "ComplexService"}

	// Dynamic dispatch based on user input (security risk!)
	userInput := "PublicMethod"

	// Pattern: User-controlled method name - Line 160
	if method := reflect.ValueOf(svc).MethodByName(userInput); method.IsValid() {
		// This could call any public method!
		method.Call([]reflect.Value{reflect.ValueOf("user-controlled")})
	}

	// Plugin/extension pattern
	methodRegistry := map[string]string{
		"action1": "PublicMethod",
		"action2": "AnotherMethod",
	}

	action := "action1"
	if methodName, exists := methodRegistry[action]; exists {
		// Pattern: Registry-based dispatch - Line 174
		reflect.ValueOf(svc).MethodByName(methodName).Call([]reflect.Value{reflect.ValueOf("registry")})
	}
}

// RPC/API handler pattern (common security issue)
func rpcHandler(objectName string, methodName string, args []interface{}) ([]reflect.Value, error) {
	// Simulating object lookup
	var obj interface{}
	switch objectName {
	case "service":
		obj = Service{Name: "RPCService"}
	default:
		return nil, fmt.Errorf("unknown object: %s", objectName)
	}

	// Pattern: RPC-style method invocation - Line 192
	method := reflect.ValueOf(obj).MethodByName(methodName)
	if !method.IsValid() {
		return nil, fmt.Errorf("invalid method: %s", methodName)
	}

	// Convert args to reflect.Value
	reflectArgs := make([]reflect.Value, len(args))
	for i, arg := range args {
		reflectArgs[i] = reflect.ValueOf(arg)
	}

	// Pattern: Dynamic RPC call - Line 203
	return method.Call(reflectArgs), nil
}

// Event handler pattern
func eventHandler() {
	svc := Service{Name: "EventService"}
	events := []string{"PublicMethod", "AnotherMethod"}

	for _, eventName := range events {
		// Pattern: Event-driven method calls - Line 214
		if method := reflect.ValueOf(svc).MethodByName(eventName); method.IsValid() {
			method.Call([]reflect.Value{reflect.ValueOf("event-data")})
		}
	}
}

// Realistic broken-up patterns (field + method)
func fieldMethodBrokenUp() {
	handler := Handler{Service: Service{Name: "FieldService"}}

	// Pattern: Partially broken up (field stored, then method) - Line 218
	field := reflect.ValueOf(handler).FieldByName("Service")
	method := field.MethodByName("Execute")
	method.Call([]reflect.Value{reflect.ValueOf("partial-breakup")})

	// Pattern: Fully broken up (all stored) - Line 223
	val := reflect.ValueOf(handler)
	svcField := val.FieldByName("Service")
	execMethod := svcField.MethodByName("Execute")
	execMethod.Call([]reflect.Value{reflect.ValueOf("full-breakup")})
}

// Realistic broken-up patterns (Elem + method)
func elemMethodBrokenUp() {
	svc := &Service{Name: "ElemService"}

	// Pattern: Partially broken up (elem stored) - Line 233
	elem := reflect.ValueOf(svc).Elem()
	method := elem.MethodByName("PublicMethod")
	method.Call([]reflect.Value{reflect.ValueOf("elem-partial")})

	// Pattern: Fully broken up (all stored) - Line 238
	val := reflect.ValueOf(svc)
	elemVal := val.Elem()
	pubMethod := elemVal.MethodByName("PublicMethod")
	pubMethod.Call([]reflect.Value{reflect.ValueOf("elem-full")})
}

// Realistic broken-up patterns (Indirect + method)
func indirectMethodBrokenUp() {
	svc := &Service{Name: "IndirectService"}
	val := reflect.ValueOf(svc)

	// Pattern: Stored indirect reference - Line 249
	indirect := reflect.Indirect(val)
	method := indirect.MethodByName("PublicMethod")
	method.Call([]reflect.Value{reflect.ValueOf("indirect-stored")})
}

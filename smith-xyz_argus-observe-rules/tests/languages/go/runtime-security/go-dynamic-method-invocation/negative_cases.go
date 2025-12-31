package godynamicmethodinvocation

import (
	"fmt"
	"reflect"
)

type SafeService struct {
	Name string
}

func (s SafeService) SafeMethod(arg string) string {
	return fmt.Sprintf("Safe: %s - %s", s.Name, arg)
}

func (s SafeService) AnotherSafeMethod(args ...string) string {
	return fmt.Sprintf("Safe Variadic: %v", args)
}

type SafeHandler struct {
	Service SafeService
}

// Good: Normal method calls (not via reflection)
func normalMethodCalls() {
	svc := SafeService{Name: "Normal"}

	// Good: Direct method call without reflection
	result := svc.SafeMethod("direct")
	_ = result

	// Good: Variadic method call
	result2 := svc.AnotherSafeMethod("arg1", "arg2", "arg3")
	_ = result2

	// Good: Method call on struct field
	handler := SafeHandler{Service: svc}
	result3 := handler.Service.SafeMethod("field-access")
	_ = result3
}

// Good: Method inspection without invocation
func methodInspection() {
	svc := SafeService{Name: "Inspect"}

	// Good: Just getting method metadata, not calling
	method := reflect.ValueOf(svc).MethodByName("SafeMethod")
	_ = method.IsValid()
	_ = method.Type()

	// Good: Type inspection
	t := reflect.TypeOf(svc)
	numMethods := t.NumMethod()
	_ = numMethods

	// Good: Iterating methods for inspection only
	for i := 0; i < t.NumMethod(); i++ {
		m := t.Method(i)
		_ = m.Name
		_ = m.Type
		// Not calling the method!
	}
}

// Good: Getting method by name without calling
func methodLookupWithoutCall() {
	svc := SafeService{Name: "Lookup"}

	// Good: MethodByName without Call
	method := reflect.ValueOf(svc).MethodByName("SafeMethod")
	if method.IsValid() {
		// Inspecting method properties, not calling
		_ = method.Type()
		_ = method.Kind()
	}

	// Good: Method by index without calling
	method2 := reflect.ValueOf(svc).Method(0)
	_ = method2.Type()
}

// Good: Type.MethodByName (returns Method struct, not Value)
func typeMethodByName() {
	svc := SafeService{Name: "Type"}

	// Good: Type.MethodByName returns reflect.Method (not callable)
	t := reflect.TypeOf(svc)
	method := t.MethodByName("SafeMethod")
	// Cannot call .Call() on this - it's a Method struct, not a Value
	_ = method.Name
	_ = method.Type

	// Good: Getting methods from Type for metadata
	for i := 0; i < t.NumMethod(); i++ {
		m := t.Method(i)
		fmt.Printf("Method: %s, Type: %v\n", m.Name, m.Type)
	}
}

// Good: Checking if method exists without calling
func methodExistenceCheck() {
	svc := SafeService{Name: "Check"}

	// Good: Just checking if method exists
	method := reflect.ValueOf(svc).MethodByName("SafeMethod")
	hasMethod := method.IsValid()

	if hasMethod {
		fmt.Println("Method exists")
		// Not calling it!
	}

	// Good: Validation logic
	requiredMethods := []string{"SafeMethod", "AnotherSafeMethod"}
	for _, methodName := range requiredMethods {
		m := reflect.ValueOf(svc).MethodByName(methodName)
		if !m.IsValid() {
			fmt.Printf("Missing method: %s\n", methodName)
		}
	}
}

// Good: Getting method count
func methodCounting() {
	svc := SafeService{Name: "Count"}

	// Good: Counting methods
	t := reflect.TypeOf(svc)
	count := t.NumMethod()
	fmt.Printf("Number of methods: %d\n", count)

	// Good: ValueOf with method count
	v := reflect.ValueOf(svc)
	methodCount := v.NumMethod()
	_ = methodCount
}

// Good: Method type inspection
func methodTypeInspection() {
	svc := SafeService{Name: "TypeInspect"}

	// Good: Getting method signature
	method := reflect.ValueOf(svc).MethodByName("SafeMethod")
	if method.IsValid() {
		methodType := method.Type()
		numIn := methodType.NumIn()
		numOut := methodType.NumOut()

		fmt.Printf("Method has %d inputs and %d outputs\n", numIn, numOut)

		// Inspecting parameter types
		for i := 0; i < numIn; i++ {
			paramType := methodType.In(i)
			fmt.Printf("Param %d: %v\n", i, paramType)
		}
	}
}

// Good: Reflection for serialization/deserialization
func serializationPatterns() {
	svc := SafeService{Name: "Serialize"}

	// Good: Using reflection to inspect structure
	v := reflect.ValueOf(svc)
	t := v.Type()

	// Iterating fields (not methods)
	for i := 0; i < t.NumField(); i++ {
		field := t.Field(i)
		fieldValue := v.Field(i)
		fmt.Printf("Field: %s, Value: %v\n", field.Name, fieldValue.Interface())
	}

	// Good: Method metadata for documentation generation
	for i := 0; i < t.NumMethod(); i++ {
		method := t.Method(i)
		fmt.Printf("Documented method: %s\n", method.Name)
	}
}

// Good: Interface checking
func interfaceChecking() {
	svc := SafeService{Name: "Interface"}

	// Good: Checking if type implements interface
	v := reflect.ValueOf(svc)
	t := v.Type()

	// Check if methods exist (interface compliance)
	methods := []string{"SafeMethod", "AnotherSafeMethod"}
	implementsAll := true
	for _, methodName := range methods {
		_, found := t.MethodByName(methodName)
		if !found {
			implementsAll = false
			break
		}
	}

	fmt.Printf("Implements interface: %v\n", implementsAll)
}

// Good: Method comparison
func methodComparison() {
	svc1 := SafeService{Name: "First"}
	svc2 := SafeService{Name: "Second"}

	// Good: Comparing method signatures
	method1 := reflect.ValueOf(svc1).MethodByName("SafeMethod")
	method2 := reflect.ValueOf(svc2).MethodByName("SafeMethod")

	if method1.Type() == method2.Type() {
		fmt.Println("Methods have same signature")
	}
}

// Good: Conditional method selection without calling
func conditionalMethodSelection() {
	svc := SafeService{Name: "Conditional"}
	condition := true

	// Good: Selecting method based on condition but not calling
	var methodName string
	if condition {
		methodName = "SafeMethod"
	} else {
		methodName = "AnotherSafeMethod"
	}

	method := reflect.ValueOf(svc).MethodByName(methodName)
	if method.IsValid() {
		// Just getting type info, not calling
		fmt.Printf("Selected method type: %v\n", method.Type())
	}
}

// Good: Method caching/registration
func methodRegistry() {
	svc := SafeService{Name: "Registry"}

	// Good: Building a registry of methods without calling them
	registry := make(map[string]reflect.Value)

	t := reflect.TypeOf(svc)
	for i := 0; i < t.NumMethod(); i++ {
		method := t.Method(i)
		methodValue := reflect.ValueOf(svc).Method(i)
		registry[method.Name] = methodValue
		// Stored but not called
	}

	// Good: Checking if method is in registry
	if _, exists := registry["SafeMethod"]; exists {
		fmt.Println("Method registered")
		// Not calling it
	}
}

// Good: Validation patterns
func validationPatterns() {
	svc := SafeService{Name: "Validate"}

	// Good: Validating method exists and has correct signature
	method := reflect.ValueOf(svc).MethodByName("SafeMethod")

	if !method.IsValid() {
		fmt.Println("Method doesn't exist")
		return
	}

	methodType := method.Type()
	if methodType.NumIn() != 1 {
		fmt.Println("Wrong number of parameters")
		return
	}

	if methodType.NumOut() != 1 {
		fmt.Println("Wrong number of return values")
		return
	}

	// All validation passed but not calling the method
	fmt.Println("Method signature valid")
}

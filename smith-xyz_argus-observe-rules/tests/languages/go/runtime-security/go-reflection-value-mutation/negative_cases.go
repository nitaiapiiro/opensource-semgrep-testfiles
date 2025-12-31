package goreflectionvaluemutation

import (
	"reflect"
)

type SafeConfig struct {
	Host    string
	Port    int
	Enabled bool
}

// Good: Reading values without mutation
func readOnlyReflection() {
	config := SafeConfig{Host: "localhost", Port: 8080, Enabled: true}

	// Good: TypeOf operations (read-only)
	t := reflect.TypeOf(config)
	_ = t.Name()
	_ = t.Kind()
	_ = t.NumField()

	// Good: ValueOf for reading (no Set calls)
	v := reflect.ValueOf(config)
	_ = v.Kind()
	_ = v.NumField()
	_ = v.IsValid()
	_ = v.IsZero()
	_ = v.CanSet()

	// Good: Reading field values
	hostField := reflect.ValueOf(config).FieldByName("Host")
	_ = hostField.String()
	_ = hostField.IsValid()

	// Good: Type checking
	portField := reflect.ValueOf(config).FieldByName("Port")
	_ = portField.Kind()
	_ = portField.Type()

	// Good: Reading via Elem
	ptr := &config
	elem := reflect.ValueOf(ptr).Elem()
	_ = elem.NumField()
	_ = elem.Kind()

	// Good: Reading via Indirect
	indirect := reflect.Indirect(reflect.ValueOf(ptr))
	_ = indirect.NumField()
	_ = indirect.Kind()
}

// Good: Using reflect for inspection and comparison
func reflectComparison() {
	config1 := SafeConfig{Host: "localhost"}
	config2 := SafeConfig{Host: "localhost"}

	// Good: DeepEqual is read-only
	equal := reflect.DeepEqual(config1, config2)
	_ = equal

	// Good: Type comparison
	t1 := reflect.TypeOf(config1)
	t2 := reflect.TypeOf(config2)
	sameType := t1 == t2
	_ = sameType
}

// Good: Creating values without mutation
func reflectCreation() {
	// Good: MakeSlice creates new values, doesn't mutate existing
	sliceType := reflect.TypeOf([]int{})
	newSlice := reflect.MakeSlice(sliceType, 0, 10)
	_ = newSlice

	// Good: MakeMap creates new values
	mapType := reflect.TypeOf(map[string]int{})
	newMap := reflect.MakeMap(mapType)
	_ = newMap

	// Good: Zero creates new zero values
	zeroVal := reflect.Zero(reflect.TypeOf(0))
	_ = zeroVal
}

// Good: Method calls via reflection (not field mutation)
func reflectMethodCalls() {
	config := SafeConfig{Host: "localhost"}

	// Good: MethodByName for calling methods
	method := reflect.ValueOf(&config).MethodByName("String")
	if method.IsValid() {
		_ = method.Call(nil)
	}

	// Good: Type method inspection
	t := reflect.TypeOf(config)
	_ = t.NumMethod()
	for i := 0; i < t.NumMethod(); i++ {
		m := t.Method(i)
		_ = m.Name
	}
}

// Good: Index operations without Set
func reflectIndexReading() {
	slice := []int{1, 2, 3, 4, 5}
	arr := [3]string{"a", "b", "c"}

	// Good: Reading slice elements
	val := reflect.ValueOf(slice)
	for i := 0; i < val.Len(); i++ {
		elem := val.Index(i)
		_ = elem.Int()
	}

	// Good: Reading array elements
	arrVal := reflect.ValueOf(arr)
	for i := 0; i < arrVal.Len(); i++ {
		elem := arrVal.Index(i)
		_ = elem.String()
	}

	// Good: Getting element type
	elemType := val.Type().Elem()
	_ = elemType
}

// Good: Struct field iteration without mutation
func reflectFieldIteration() {
	config := SafeConfig{Host: "localhost", Port: 8080}
	v := reflect.ValueOf(config)
	t := v.Type()

	// Good: Iterating and reading fields
	for i := 0; i < v.NumField(); i++ {
		field := v.Field(i)
		fieldType := t.Field(i)
		_ = field.Interface()
		_ = fieldType.Name
		_ = fieldType.Type
	}
}

// Good: Interface conversions without mutation
func reflectInterfaceConversion() {
	config := SafeConfig{Host: "localhost"}
	v := reflect.ValueOf(config)

	// Good: Converting to interface for reading
	iface := v.Interface()
	if c, ok := iface.(SafeConfig); ok {
		_ = c.Host
	}

	// Good: Type assertions
	hostField := v.FieldByName("Host")
	if hostField.IsValid() {
		_ = hostField.Interface()
	}
}

// Good: Pointer operations without Set
func reflectPointerOperations() {
	config := SafeConfig{Host: "localhost"}

	// Good: Getting pointer
	ptr := &config
	ptrVal := reflect.ValueOf(ptr)
	_ = ptrVal.Kind() // Will be reflect.Ptr

	// Good: Checking if can address
	elem := ptrVal.Elem()
	_ = elem.CanAddr()
	_ = elem.CanSet()

	// Good: Getting address (not setting)
	if elem.CanAddr() {
		addr := elem.Addr()
		_ = addr
	}
}

// Good: Map operations without SetMapIndex
func reflectMapReading() {
	m := map[string]int{"a": 1, "b": 2}
	v := reflect.ValueOf(m)

	// Good: Reading map values
	key := reflect.ValueOf("a")
	value := v.MapIndex(key)
	_ = value.Int()

	// Good: Iterating map
	iter := v.MapRange()
	for iter.Next() {
		k := iter.Key()
		v := iter.Value()
		_ = k.String()
		_ = v.Int()
	}

	// Good: Getting map keys
	keys := v.MapKeys()
	_ = len(keys)
}

// Good: Type construction without mutation
func reflectTypeConstruction() {
	// Good: Creating new types
	structType := reflect.StructOf([]reflect.StructField{
		{
			Name: "Field1",
			Type: reflect.TypeOf(""),
		},
	})
	_ = structType

	// Good: Slice and array types
	sliceType := reflect.SliceOf(reflect.TypeOf(0))
	arrayType := reflect.ArrayOf(5, reflect.TypeOf(0))
	_ = sliceType
	_ = arrayType
}

// Good: Normal Go code without reflection
func regularGoCode() {
	config := SafeConfig{}
	// Good: Normal field assignment (not via reflection)
	config.Host = "localhost"
	config.Port = 8080
	config.Enabled = true

	_ = config
}

// Method for testing MethodByName (needs to be exported)
func (c SafeConfig) String() string {
	return c.Host
}

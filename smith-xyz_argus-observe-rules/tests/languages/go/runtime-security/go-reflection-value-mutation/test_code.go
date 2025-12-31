package goreflectionvaluemutation

import (
	"crypto/tls"
	"reflect"
	"unsafe"
)

type Config struct {
	Host     string
	Port     int
	Enabled  bool
	Settings map[string]interface{}
}

func valueCreation() {
	var x int

	// Pattern: reflect.New($TYPE) - Line 20
	ptrVal := reflect.New(reflect.TypeOf(x))

	// Pattern: reflect.NewAt($TYPE, $PTR) - Line 23
	newAtVal := reflect.NewAt(reflect.TypeOf(x), unsafe.Pointer(&x))

	_, _ = ptrVal, newAtVal
}

func directSetOperations() {
	config := &Config{}
	val := reflect.ValueOf(config).Elem()

	// Pattern: reflect.ValueOf($EXPR).Set($VALUE) - Line 34
	reflect.ValueOf(&config.Host).Elem().Set(reflect.ValueOf("localhost"))

	// Pattern: reflect.ValueOf($EXPR).SetBool($VALUE) - Line 37
	reflect.ValueOf(&config.Enabled).Elem().SetBool(true)

	// Pattern: reflect.ValueOf($EXPR).SetInt($VALUE) - Line 40
	reflect.ValueOf(&config.Port).Elem().SetInt(8080)

	// Pattern: reflect.ValueOf($EXPR).SetString($VALUE) - Line 43
	reflect.ValueOf(&config.Host).Elem().SetString("example.com")

	_ = val
}

func deferredSetOperations() {
	config := &Config{Enabled: false}

	// Pattern: $VAL := reflect.ValueOf($EXPR); ...; $VAL.SetBool($VALUE) - Line 52
	val := reflect.ValueOf(&config.Enabled).Elem()
	// Some logic here
	val.SetBool(true)

	// Pattern: $VAL := reflect.ValueOf($EXPR); ...; $VAL.Set($VALUE) - Line 57
	portVal := reflect.ValueOf(&config.Port).Elem()
	newPort := reflect.ValueOf(9090)
	portVal.Set(newPort)

	// Pattern: $VAL := reflect.ValueOf($EXPR); ...; $VAL.SetInt($VALUE) - Line 62
	anotherPortVal := reflect.ValueOf(&config.Port).Elem()
	anotherPortVal.SetInt(3000)

	// Pattern: $VAL := reflect.ValueOf($EXPR); ...; $VAL.SetString($VALUE) - Line 66
	hostVal := reflect.ValueOf(&config.Host).Elem()
	hostVal.SetString("api.example.com")
}

func fieldByNameOperations() {
	config := &Config{}

	// Pattern: reflect.ValueOf($EXPR).FieldByName($NAME).Set($VALUE) - Line 75
	reflect.ValueOf(config).FieldByName("Host").Set(reflect.ValueOf("localhost"))

	// Pattern: reflect.ValueOf($EXPR).FieldByName($NAME).SetBool($VALUE) - Line 78
	reflect.ValueOf(config).FieldByName("Enabled").SetBool(true)

	// Pattern: reflect.ValueOf($EXPR).FieldByName($NAME).SetInt($VALUE) - Line 81
	reflect.ValueOf(config).FieldByName("Port").SetInt(8080)

	// Pattern: reflect.ValueOf($EXPR).FieldByName($NAME).SetString($VALUE) - Line 84
	reflect.ValueOf(config).FieldByName("Host").SetString("example.com")

	// Pattern: $FIELD := reflect.ValueOf($EXPR).FieldByName($NAME); ...; $FIELD.Set($VALUE) - Line 87
	field := reflect.ValueOf(config).FieldByName("Port")
	field.Set(reflect.ValueOf(9090))

	// Pattern: $FIELD := reflect.ValueOf($EXPR).FieldByName($NAME); ...; $FIELD.SetBool($VALUE) - Line 91
	enabledField := reflect.ValueOf(config).FieldByName("Enabled")
	enabledField.SetBool(false)

	// Pattern: $FIELD := reflect.ValueOf($EXPR).FieldByName($NAME); ...; $FIELD.SetString($VALUE) - Line 95
	hostField := reflect.ValueOf(config).FieldByName("Host")
	hostField.SetString("api.example.com")
}

func elemOperations() {
	config := &Config{}

	// Pattern: reflect.ValueOf($EXPR).Elem().Set($VALUE) - Line 104
	reflect.ValueOf(&config.Host).Elem().Set(reflect.ValueOf("localhost"))

	// Pattern: reflect.ValueOf($EXPR).Elem().SetBool($VALUE) - Line 107
	reflect.ValueOf(&config.Enabled).Elem().SetBool(true)

	// Pattern: reflect.ValueOf($EXPR).Elem().SetInt($VALUE) - Line 110
	reflect.ValueOf(&config.Port).Elem().SetInt(8080)

	// Pattern: $ELEM := reflect.ValueOf($EXPR).Elem(); ...; $ELEM.Set($VALUE) - Line 113
	elem := reflect.ValueOf(&config.Host).Elem()
	elem.Set(reflect.ValueOf("example.com"))

	// Pattern: $ELEM := reflect.ValueOf($EXPR).Elem(); ...; $ELEM.SetBool($VALUE) - Line 117
	enabledElem := reflect.ValueOf(&config.Enabled).Elem()
	enabledElem.SetBool(false)
}

func elemFieldByNameCombination() {
	// This is a real-world pattern seen in TLS bypass attempts
	config := &tls.Config{}

	// Pattern: reflect.ValueOf($EXPR).Elem().FieldByName($NAME).Set($VALUE) - Line 127
	reflect.ValueOf(config).Elem().FieldByName("InsecureSkipVerify").Set(reflect.ValueOf(true))

	// Pattern: reflect.ValueOf($EXPR).Elem().FieldByName($NAME).SetBool($VALUE) - Line 130
	reflect.ValueOf(config).Elem().FieldByName("InsecureSkipVerify").SetBool(true)

	// Pattern: $FIELD := reflect.ValueOf($EXPR).Elem().FieldByName($NAME); ...; $FIELD.Set($VALUE) - Line 133
	field := reflect.ValueOf(config).Elem().FieldByName("InsecureSkipVerify")
	// Some intervening logic
	field.Set(reflect.ValueOf(true))

	// Pattern: $FIELD := reflect.ValueOf($EXPR).Elem().FieldByName($NAME); ...; $FIELD.SetBool($VALUE) - Line 138
	anotherField := reflect.ValueOf(config).Elem().FieldByName("InsecureSkipVerify")
	anotherField.SetBool(true)
}

func indirectOperations() {
	config := &Config{}
	ptr := reflect.ValueOf(config)

	// Pattern: reflect.Indirect($VALUE).Set($NEWVAL) - Line 148
	reflect.Indirect(ptr).Set(reflect.ValueOf(Config{Host: "localhost"}))

	// Pattern: reflect.Indirect($VALUE).SetBool($NEWVAL) - Line 151
	reflect.Indirect(reflect.ValueOf(&config.Enabled)).SetBool(true)

	// Pattern: reflect.Indirect($VALUE).FieldByName($NAME).Set($NEWVAL) - Line 154
	reflect.Indirect(ptr).FieldByName("Host").Set(reflect.ValueOf("example.com"))

	// Pattern: reflect.Indirect($VALUE).FieldByName($NAME).SetBool($NEWVAL) - Line 157
	reflect.Indirect(ptr).FieldByName("Enabled").SetBool(false)
}

func indexOperations() {
	slice := []string{"a", "b", "c"}
	arr := [3]int{1, 2, 3}

	// Pattern: reflect.ValueOf($EXPR).Index($IDX).Set($VALUE) - Line 166
	reflect.ValueOf(slice).Index(0).Set(reflect.ValueOf("modified"))

	// Pattern: reflect.ValueOf($EXPR).Index($IDX).SetInt($VALUE) - Line 169
	reflect.ValueOf(&arr).Elem().Index(1).SetInt(99)

	// Pattern: reflect.ValueOf($EXPR).Index($IDX).SetString($VALUE) - Line 172
	reflect.ValueOf(slice).Index(2).SetString("changed")

	// Pattern: $ELEM := reflect.ValueOf($EXPR).Index($IDX); ...; $ELEM.Set($VALUE) - Line 175
	elem := reflect.ValueOf(slice).Index(1)
	// Some logic
	elem.Set(reflect.ValueOf("updated"))
}

func mixedComplexPatterns() {
	configs := []Config{{Host: "host1"}, {Host: "host2"}}

	// Complex pattern: Index + FieldByName + Set - Line 185
	reflect.ValueOf(&configs).Elem().Index(0).FieldByName("Host").SetString("newhost")

	// Complex pattern: nested operations - Line 188
	val := reflect.ValueOf(&configs).Elem()
	configVal := val.Index(1)
	hostField := configVal.FieldByName("Host")
	hostField.SetString("anotherhost")
}

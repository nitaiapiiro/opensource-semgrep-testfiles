// License: The GitLab Enterprise Edition (EE) license (the “EE License”)
package deserialization;

import java.io.File;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTypeResolverBuilder;
import com.fasterxml.jackson.databind.json.JsonMapper;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

class App {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        ExecuteVulns executeVulns = new ExecuteVulns(objectMapper);

        executeVulns.enableDefaultTyping();
        executeVulns.defaultTypeResolverBuilder();
        executeVulns.defaultTypeResolverBuilder2();

        executeVulns.safeTypingWithUnsafeBaseAllowed();
        executeVulns.safeTypingWithLaissezFaireSubTypeValidator();
        executeVulns.blockUnsafeBaseType();
        executeVulns.blockUnsafeBaseType2();

    }

}

class ExecuteVulns {

    ObjectMapper objectMapper;

    ExecuteVulns(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    void enableDefaultTyping() throws Exception {

        // ruleid: java_deserialization_rule-JacksonUnsafeDeserialization
        objectMapper.enableDefaultTyping();

        // ruleid: java_deserialization_rule-JacksonUnsafeDeserialization
        ObjectMapper objM = new ObjectMapper().enableDefaultTyping();

        String exploit_JSON = "./car_jdbc.json";
        // From JSON to class
        Car newCar = objectMapper.readValue(new File(exploit_JSON), Car.class);

        objectMapper.writeValue(new File("car_w.json"), newCar);

        System.out.print("Engine type: " + newCar.engine.getClass().getName());
        System.out.print("\n");
        System.out.print("secEngine type: " + newCar.secEngine.getClass().getName());
    }

    // No Annotations
    void defaultTypeResolverBuilder() throws Exception {

        DefaultTypeResolverBuilder resolverBuilder = new DefaultTypeResolverBuilder(
                ObjectMapper.DefaultTyping.NON_FINAL);
        // ruleid: java_deserialization_rule-JacksonUnsafeDeserialization
        resolverBuilder.init(JsonTypeInfo.Id.CLASS, null);
        resolverBuilder.inclusion(JsonTypeInfo.As.WRAPPER_ARRAY);
        resolverBuilder.typeProperty("_type");

        objectMapper.setDefaultTyping(resolverBuilder);

    }

    void defaultTypeResolverBuilder2() throws Exception {

        DefaultTypeResolverBuilder rb = new DefaultTypeResolverBuilder(ObjectMapper.DefaultTyping.NON_FINAL);
        // ruleid: java_deserialization_rule-JacksonUnsafeDeserialization
        rb.init(JsonTypeInfo.Id.MINIMAL_CLASS, null);
        rb.inclusion(JsonTypeInfo.As.WRAPPER_ARRAY);
        rb.typeProperty("_type");

        objectMapper.setDefaultTyping(rb);

    }

    void safeTypingWithUnsafeBaseAllowed() throws Exception {
        // Will Also need higher Jackson dependency version (10+)
        // ruleid: java_deserialization_rule-JacksonUnsafeDeserialization
        objectMapper.activateDefaultTyping(BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(Object.class).build());

        String exploit_JSON = "./mygadgetload.json";

        // From JSON to class
        Car newCar = objectMapper.readValue(new File(exploit_JSON), Car.class);

        System.out.print("Engine type: " + newCar.engine.getClass().getName());
        System.out.print("\n");
        System.out.print("secEngine type: " + newCar.secEngine.getClass().getName());
    }

    void safeTypingWithLaissezFaireSubTypeValidator() throws Exception {
        // Will Also need higher Jackson dependency version (10+)
        // ruleid: java_deserialization_rule-JacksonUnsafeDeserialization
        objectMapper.activateDefaultTyping(new LaissezFaireSubTypeValidator(), ObjectMapper.DefaultTyping.EVERYTHING);

        String exploit_JSON = "./mygadgetload_array.json";

        // From JSON to class
        Car newCar = objectMapper.readValue(new File(exploit_JSON), Car.class);

        System.out.print("Engine type: " + newCar.engine.getClass().getName());
        System.out.print("\n");
        System.out.print("secEngine type: " + newCar.secEngine.getClass().getName());
    }

    // denies resolution of all subtypes of java.lang.Object
    void blockUnsafeBaseType() throws Exception {

        objectMapper.enable(MapperFeature.BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES);

        // ok: java_deserialization_rule-JacksonUnsafeDeserialization
        objectMapper.enableDefaultTyping();

        String exploit_JSON = "./car_jdbc.json";

        // From JSON to class
        CarWithCLASSAnnotations newCar = objectMapper.readValue(new File(exploit_JSON), CarWithCLASSAnnotations.class);

        objectMapper.writeValue(new File("car_w.json"), newCar);

        System.out.print("Engine type: " + newCar.engine.getClass().getName());
        System.out.print("\n");
        System.out.print("secEngine type: " + newCar.secEngine.getClass().getName());
    }

    void blockUnsafeBaseType2() throws Exception {

        ObjectMapper mapper = JsonMapper.builder()
                .enable(MapperFeature.BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES)
                .build();

        // ok: java_deserialization_rule-JacksonUnsafeDeserialization
        mapper.enableDefaultTyping();

        String exploit_JSON = "./car_jdbc.json";

        // From JSON to class
        CarWithCLASSAnnotations newCar = mapper.readValue(new File(exploit_JSON), CarWithCLASSAnnotations.class);

        mapper.writeValue(new File("car_w.json"), newCar);

        System.out.print("Engine type: " + newCar.engine.getClass().getName());
        System.out.print("\n");
        System.out.print("secEngine type: " + newCar.secEngine.getClass().getName());
    }
}

class Car {

    public Object engine;
    public Object secEngine;
    public String color;
    public String model;

    public Car() {
    }

}

class CarWithCLASSAnnotations {
    // ruleid: java_deserialization_rule-JacksonUnsafeDeserialization
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
    public Object engine;
    // ruleid: java_deserialization_rule-JacksonUnsafeDeserialization
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
    public Object secEngine;
    public String color;
    public String model;

    public CarWithCLASSAnnotations() {
    }

}

class CarWithMinimalCLASSAnnotations {

    // ruleid: java_deserialization_rule-JacksonUnsafeDeserialization
    @JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
    public Object engine;
    // ruleid: java_deserialization_rule-JacksonUnsafeDeserialization
    @JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
    public Object secEngine;
    public String color;
    public String model;

    public CarWithMinimalCLASSAnnotations() {
    }

}

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_ARRAY, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ElectricEngine.class, name = "ElectricEngine"),
        @JsonSubTypes.Type(value = FuelEngine.class, name = "FuelEngine")
})
abstract class Engine {
    public int engineModel;
    public int cc;
}

class ElectricEngine extends Engine {
    public int maxEnergy;

    public ElectricEngine() {
    }
}

class FuelEngine extends Engine {
    public int fuel;

    public FuelEngine() {
    }
}

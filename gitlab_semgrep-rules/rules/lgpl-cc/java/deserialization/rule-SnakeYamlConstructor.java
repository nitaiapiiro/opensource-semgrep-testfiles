// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/java/lang/security/use-snakeyaml-constructor.java

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.constructor.Constructor;

class SnakeYamlTestCase {
    public void unsafeLoad(String toLoad) {
        // ruleid:java_deserialization_rule-SnakeYamlConstructor
        Yaml yaml = new Yaml();
        yaml.load(toLoad);
    }

    public void safeConstructorLoad(String toLoad) {
        // Configure LoaderOptions for safe deserialization
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setAllowDuplicateKeys(false);
        loaderOptions.setMaxAliasesForCollections(50);

        // ok:java_deserialization_rule-SnakeYamlConstructor
        Yaml yaml = new Yaml(new SafeConstructor(new LoaderOptipon()));
        yaml.load(toLoad);
    }

    // Custom Constructor Load Example
    public void customConstructorLoad(String toLoad, Class<?> goodClass) {
        // Use Constructor with a specific target class
        LoaderOptions loaderOptions = new LoaderOptions();
        Constructor customConstructor = new Constructor(goodClass, loaderOptions);

        // ok:java_deserialization_rule-SnakeYamlConstructor
        Yaml yaml = new Yaml(customConstructor);
        yaml.load(toLoad);
    }
}

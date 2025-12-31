// License: The GitLab Enterprise Edition (EE) license (the “EE License”)
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.IOException;

public class InsecureDeserialization {
    public Object deserializeObject(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
             t.readObject();

            // ruleid: java_deserialization_rule-InsecureDeserialization
            return ois.readObject();
        }
        byte[] moreData = new byte[8];
        try (ByteArrayInputStream bis = new ByteArrayInputStream(moreData);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
             t.readObject();

            // ok: java_deserialization_rule-InsecureDeserialization
            return ois.readObject();
        }
    }
}

// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/java/mongodb/security/injection/audit/mongodb-nosqli.java

import org.apache.ecs.Document;
import org.apache.ecs.xhtml.col;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.HashMap;

class ContactsService {

    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public ContactsService(MongoDatabase db, MongoCollection<Document> collection) {
        this.db = db;
        this.collection = collection;
    }

    public ArrayList<Document> basicDBObjectPut1(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        // ruleid: java_inject_rule-MongodbNoSQLi
        query.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectPut2(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        // ok: java_inject_rule-MongodbNoSQLi
        query.put("$where", "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"");

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectPut3(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        // ok: java_inject_rule-MongodbNoSQLi
        query.put("sharedWith", userName);
        query.put("email", email);

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectPutAll1(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        HashMap<String, String> paramMap = new HashMap<>();
        // ruleid: java_inject_rule-MongodbNoSQLi
        paramMap.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");
        query.putAll(paramMap);

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectPutAll2(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        HashMap<String, String> paramMap = new HashMap<>();
        // ok: java_inject_rule-MongodbNoSQLi
        paramMap.put("$where", "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"");
        query.putAll(paramMap);

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectPutAll3(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        HashMap<String, String> paramMap = new HashMap<>();
        // ok: java_inject_rule-MongodbNoSQLi
        paramMap.put("sharedWith", userName);
        paramMap.put("email", email);
        query.putAll(paramMap);

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectAppend1(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        // ruleid: java_inject_rule-MongodbNoSQLi
        query.append("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectAppend2(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        // ok: java_inject_rule-MongodbNoSQLi
        query.append("$where", "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"");

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectAppend3(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        // ok: java_inject_rule-MongodbNoSQLi
        query.append("sharedWith", userName);
        query.append("email", email);

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectConstructorKv1(String userName, String email) {
        // ruleid: java_inject_rule-MongodbNoSQLi
        BasicDBObject query = new BasicDBObject("$where",
                "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectConstructorKv2(String userName, String email) {
        // ok: java_inject_rule-MongodbNoSQLi
        BasicDBObject query = new BasicDBObject("$where",
                "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"");

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectConstructorKv3(String userName, String email) {
        BasicDBObject query = new BasicDBObject("sharedWith", userName);
        // ok: java_inject_rule-MongodbNoSQLi
        query.append("email", email);

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectConstructorMap1(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        // ruleid: java_inject_rule-MongodbNoSQLi
        paramMap.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");
        BasicDBObject query = new BasicDBObject(paramMap);

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectConstructorMap2(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        // ok: java_inject_rule-MongodbNoSQLi
        paramMap.put("$where", "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"");
        BasicDBObject query = new BasicDBObject(paramMap);

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectConstructorMap3(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        // ok: java_inject_rule-MongodbNoSQLi
        paramMap.put("sharedWith", userName);
        paramMap.put("email", email);
        BasicDBObject query = new BasicDBObject(paramMap);

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectParse1(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        // ruleid: java_inject_rule-MongodbNoSQLi
        paramMap.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");
        String json = new JSONObject(paramMap).toString();
        BasicDBObject query = new BasicDBObject().parse(json);

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectParse2(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        // ok: java_inject_rule-MongodbNoSQLi
        paramMap.put("$where", "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"");
        String json = new JSONObject(paramMap).toString();
        BasicDBObject query = new BasicDBObject().parse(json);

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectParse3(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        // ok: java_inject_rule-MongodbNoSQLi
        paramMap.put("sharedWith", userName);
        paramMap.put("email", email);
        String json = new JSONObject(paramMap).toString();
        BasicDBObject query = new BasicDBObject().parse(json);

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderAdd1(String userName, String email) {
        // ruleid: java_inject_rule-MongodbNoSQLi
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start()
                .add("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"")
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderAdd2(String userName, String email) {
        // ok: java_inject_rule-MongodbNoSQLi
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start()
                .add("$where", "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"")
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderAdd3(String userName, String email) {
        // ok: java_inject_rule-MongodbNoSQLi
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start()
                .add("sharedWith", userName)
                .add("email", email)
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderAppend1(String userName, String email) {
        // ruleid: java_inject_rule-MongodbNoSQLi
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start()
                .append("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"")
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderAppend2(String userName, String email) {
        // ok: java_inject_rule-MongodbNoSQLi
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start()
                .append("$where", "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"")
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderAppend3(String userName, String email) {
        // ok: java_inject_rule-MongodbNoSQLi
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start()
                .append("sharedWith", userName)
                .append("email", email)
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderStartKv1(String userName, String email) {
        // ruleid: java_inject_rule-MongodbNoSQLi
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"")
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderStartKv2(String userName, String email) {
        // ok: java_inject_rule-MongodbNoSQLi
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start("$where", "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"")
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderStartKv3(String userName, String email) {
        // ok: java_inject_rule-MongodbNoSQLi
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start("sharedWith", userName)
                .append("email", email)
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderStartMap1(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        // ruleid: java_inject_rule-MongodbNoSQLi
        paramMap.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start(paramMap)
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderStartMap2(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        // ok: java_inject_rule-MongodbNoSQLi
        paramMap.put("$where", "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"");
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start(paramMap)
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();

        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public ArrayList<Document> basicDBObjectBuilderStartMap3(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        // ok: java_inject_rule-MongodbNoSQLi
        paramMap.put("sharedWith", userName);
        paramMap.put("email", email);
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start(paramMap)
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    public FindIterable<Document> safe(String userName, String email) {
        // ok: java_inject_rule-MongodbNoSQLi
        FindIterable<Document> docs = collection.find(Filters.eq("username", userName));

        return docs;
    }
}

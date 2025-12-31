// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/java/rmi/security/java_deserialization_rule-ServerDangerousObjectDeserialization.java

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import java.rmi.Remote;
import java.rmi.RemoteException;


interface IBSidesService1 extends Remote {
    // ok:java_deserialization_rule-ServerDangerousObjectDeserialization 
    boolean registerTicket(String ticketID) throws RemoteException;
    // ok:java_deserialization_rule-ServerDangerousObjectDeserialization
    void vistTalk(String talkID) throws RemoteException;
    // ruleid:java_deserialization_rule-ServerDangerousObjectDeserialization
    void poke(Object attendee) throws RemoteException;
}

interface IBSidesService2 extends Remote {
    // ok:java_deserialization_rule-ServerDangerousObjectDeserialization
    boolean registerTicket(String ticketID) throws RemoteException;
    // ruleid:java_deserialization_rule-ServerDangerousObjectDeserialization
    void vistTalk(CustomClass talkID) throws RemoteException;
    // ruleid:java_deserialization_rule-ServerDangerousObjectDeserialization
    void poke(StringBuilder attendee) throws RemoteException;
}

interface IBSidesServiceOK extends Remote {
    // ok:java_deserialization_rule-ServerDangerousObjectDeserialization
    boolean registerTicket(String ticketID) throws RemoteException;
    // ok:java_deserialization_rule-ServerDangerousObjectDeserialization
    void vistTalk(String talkID) throws RemoteException;
    // ok:java_deserialization_rule-ServerDangerousObjectDeserialization
    void poke(int attendee) throws RemoteException;
    // ok:java_deserialization_rule-ServerDangerousObjectDeserialization
    void poke(Integer attendee) throws RemoteException;
}


class BSidesServer {
    public static void main(String[] args) {
        try {
            // Create new RMI registry to which we can register
            LocateRegistry.createRegistry(1099);

            // Make our BSides Server object
            // available under the name "bsides"
            Naming.bind("bsides", new BSidesServiceServerImpl());
            System.out.println("BSides RMI server is ready");

        } catch (Exception e) {
            // In case of an error, print the stacktrace
            // and bail out
            e.printStackTrace();
        }
    }
}

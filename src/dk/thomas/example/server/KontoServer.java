package dk.thomas.example.server;

import dk.thomas.example.interfaces.KontoI;

import java.rmi.Naming;

public class KontoServer
{
    public static void main(String[] arg) throws Exception
    {
        // Enten: Kør programmet 'rmiregistry' fra mappen med .class-filerne, eller:
        java.rmi.registry.LocateRegistry.createRegistry(1099); // start i server-JVM

        KontoI k = new KontoImpl();
        Naming.rebind("rmi://localhost/kontotjeneste", k);
        System.out.println("Kontotjeneste registreret.");
    }
}
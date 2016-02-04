package dk.thomas.example.server;

import com.sun.org.apache.xpath.internal.SourceTree;
import dk.thomas.example.interfaces.KontoI;

import java.util.ArrayList;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class KontoImpl extends UnicastRemoteObject implements KontoI
{
    public int saldo;
    public ArrayList bevægelser;
    public final int startSaldo;

    public KontoImpl() throws java.rmi.RemoteException
    {
        // man starter med 100 kroner
        saldo = 100;
        startSaldo = saldo;
        bevægelser = new ArrayList();
    }

    public synchronized void overførsel(int kroner)
    {
        String s = "Overførsel på "+kroner+" kr. Gammel saldo er "+saldo+" kr.";
        System.out.println(s);
        s = "Vent venligst...";
        System.out.println(s);
        int time;
        time = 500;
        try { Thread.sleep(time); } catch (InterruptedException e) { e.printStackTrace(); }
        saldo += kroner;
        bevægelser.add(s);
        s = "Overførsel blev GENNEMFØRT. Ny saldo er "+saldo+" kr."+"\n";
        System.out.println(s);
        time = 500;
        try { Thread.sleep(time); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public int saldo()
    {
        System.out.println("Der spørges om saldoen. Den er "+saldo+" kr.");
        return saldo;
    }

    public ArrayList bevægelser()
    {
        System.out.println("Der spørges på alle bevægelser.");
        return bevægelser;
    }
}
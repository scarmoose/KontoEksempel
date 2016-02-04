package dk.thomas.example.client;

import dk.thomas.example.interfaces.KontoI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.concurrent.ThreadFactory;

public class KontoClient
{
    public static void main(String[] arg) throws Exception
    {
        KontoI k =(KontoI) Naming.lookup("rmi://localhost/kontotjeneste");
        final Random rand = new Random();

        final int noOfTimes = 1000;
        final int MAX_AMOUNT = 70;
        final int MAX_TIME_MILLIS = 4000;

        Runnable withdrawal = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < noOfTimes; i++) {
                    int amount = rand.nextInt(MAX_AMOUNT);
                    try {
                        k.overførsel(amount);
                        int time = rand.nextInt(MAX_TIME_MILLIS);
                        Thread.sleep(time);
                    } catch (RemoteException e) {
                        System.err.println("Overførslen på "+amount+" kr blev ikke gennemført...");
                    } catch (InterruptedException e) {

                    }
                }
            }
        };
        Runnable deposits = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < noOfTimes; i++) {
                    int amount = -rand.nextInt(MAX_AMOUNT);
                    try {

                        k.overførsel(amount);
                        int time = rand.nextInt(MAX_TIME_MILLIS);
                        Thread.sleep(time);
                    } catch (RemoteException e) {
                        System.err.println("Overførslen på "+amount+" kr blev ikke gennemført...");
                    } catch (InterruptedException e) {

                    }
                }
            }
        };
        Thread withdrawalThread = new Thread(withdrawal);
        Thread depositThread = new Thread(deposits);

        withdrawalThread.start();
        depositThread.start();
    }
}
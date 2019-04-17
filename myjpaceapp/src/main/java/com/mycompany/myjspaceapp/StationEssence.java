package com.mycompany.myjspaceapp;

import org.jspace.SequentialSpace;
import org.jspace.Space;

import java.util.ArrayList;

public class StationEssence {

    public static void main(String[] argv) throws InterruptedException {
        Space ts = new SequentialSpace();
        ts.put("volume_pompe",1000f);
        Thread t1 = new Thread( new Caisse(ts) );
        Thread t2 = new Thread( new Conducteur(ts, new ArrayList<String>(), 10f, 0, "pompe_gauche",
                "payerCode", 20f) );
        Thread t3 = new Thread( new RemplisseurPompe("pompe_gauche", ts));
        Thread t4 = new Thread( new Pompe("pompe_gauche", ts) );

        t1.start();
        t3.start();
        t4.start();
        t2.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }

}
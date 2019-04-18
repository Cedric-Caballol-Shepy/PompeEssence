package com.mycompany.myjspaceapp;

import org.jspace.SequentialSpace;
import org.jspace.Space;

import java.util.ArrayList;

public class StationEssence {

    public static void main(String[] argv) throws InterruptedException {
        Space ts = new SequentialSpace();
        String idPompe = "pompe_gauche";
        ts.put("volume_pompe"+idPompe,1000f); //pour que ça fonctionne il faut obligatoirement initialiser ce tuple (même à 0)
        Thread t1 = new Thread( new Caisse(ts) );
        Thread t2 = new Thread( new Conducteur(ts, new ArrayList<String>(), 10f, 0, idPompe,
                "payerCode", 20f) );
        Thread t3 = new Thread( new RemplisseurPompe(idPompe, ts));
        Thread t4 = new Thread( new Pompe(idPompe, ts) );

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
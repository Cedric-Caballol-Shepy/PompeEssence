package com.mycompany.myjspaceapp;

import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

import java.util.ArrayList;

public class StationEssence {

    public static void main(String[] argv) throws InterruptedException {

        Space space = new SequentialSpace();

        Thread t1 = new Thread( new Caisse(space) );
        Thread t2 = new Thread( new Conducteur(space, new ArrayList<String>(), 50f, 0, "pompe_gauche",
                "payerCode", 20f) );
        Thread t3 = new Thread( new RemplisseurPompe("pompe_gauche", 1000f, space));
        Thread t4 = new Thread( new Pompe("pompe_gauche", 1000f, space) );

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

    }

}
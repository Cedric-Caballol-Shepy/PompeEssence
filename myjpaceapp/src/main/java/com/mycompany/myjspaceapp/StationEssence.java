package com.mycompany.myjspaceapp;

import org.jspace.SequentialSpace;
import org.jspace.Space;

import java.util.ArrayList;

public class StationEssence {

    public static void main(String[] argv) throws InterruptedException {

        // Initialisation de l'espace de tuples
        Space ts = new SequentialSpace();

        // Initialisation de la caisse
        Thread caisse = new Thread( new Caisse(ts) );

        // Initialisation des pompes
        String pompe_gauche = "Pompe_gauche";
        ts.put("volume_pompe"+pompe_gauche,1000f); //pour que ça fonctionne il faut obligatoirement initialiser ce tuple (même à 0)
        Thread remplisseurPompeGauche = new Thread( new RemplisseurPompe(pompe_gauche, ts));
        Thread pompeGauche = new Thread( new Pompe(pompe_gauche, ts) );

        String pompe_centre = "Pompe_centre";
        ts.put("volume_pompe"+pompe_centre,1000f);
        Thread remplisseurPompeCentre = new Thread( new RemplisseurPompe(pompe_centre, ts));
        Thread pompeCentre = new Thread( new Pompe(pompe_centre, ts) );

        String pompe_droite = "Pompe_droite";
        ts.put("volume_pompe"+pompe_droite,1000f);
        Thread remplisseurPompeDroite = new Thread( new RemplisseurPompe(pompe_droite, ts));
        Thread pompeDroite = new Thread( new Pompe(pompe_droite, ts) );

        // Initialisation des conducteurs
        Thread conducteur1 = new Thread( new Conducteur(ts, "Conducteur1", new ArrayList<String>(), 10f, 0, pompe_gauche,
                "payerCode", 20f) );

        Thread conducteur2 = new Thread( new Conducteur(ts, "Conducteur2", new ArrayList<String>(), 20f, 0, pompe_centre,
                "payerCode", 10f) );

        Thread conducteur3 = new Thread( new Conducteur(ts, "Conducteur3", new ArrayList<String>(), 60f, 0, pompe_droite,
                "payerCode", 100f) );

        // Lancement des agents
        caisse.start();
        remplisseurPompeGauche.start();
        pompeGauche.start();
        remplisseurPompeCentre.start();
        pompeCentre.start();
        remplisseurPompeDroite.start();
        pompeDroite.start();
        conducteur1.start();
        conducteur2.start();
        conducteur3.start();

        caisse.join();
        remplisseurPompeGauche.join();
        pompeGauche.join();
        remplisseurPompeCentre.join();
        pompeCentre.join();
        remplisseurPompeDroite.join();
        pompeDroite.join();
        conducteur1.join();
        conducteur2.join();
        conducteur3.join();
    }

}
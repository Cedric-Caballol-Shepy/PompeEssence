package com.mycompany.myjspaceapp;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;
import java.util.ArrayList;

public class Conducteur implements Runnable {

    private Space ts;
    private ArrayList<String> codes;
    private float volume_reservoir;
    private int code_choisi;
    private String pompe_choisie;
    private String etat;
    private float somme_argent;

    public Conducteur(Space ts, ArrayList<String> codes, float volume_reservoir, int code_choisi, String pompe_choisie,
            String etat, float somme_argent) {
        this.ts = ts;
        this.codes = codes;
        this.volume_reservoir = volume_reservoir;
        this.code_choisi = code_choisi;
        this.pompe_choisie = pompe_choisie;
        this.etat = etat;
        this.somme_argent = somme_argent;
    }

    public void run() {
        try {
            if (etat.equals("payerCode")) {
                ts.put("somme_paye", somme_argent);
                System.out.println("Conducteur : envoie l'argent");
                ts.put("pompe_choisie", pompe_choisie);
                System.out.println("Conducteur : envoie la pompe choisie");
                String code = (String) ts.get(new ActualField("code_donne"), new FormalField(String.class))[1];
                System.out.println("Conducteur : recupere la code : " + code);
                codes.add(code);
                System.out.println("Conducteur : ajoute le nouveau code dans sa liste de codes");
                Thread t = new Thread( new Conducteur(ts, codes, volume_reservoir, code_choisi, pompe_choisie,
                        "remplirVoiture", somme_argent) );
                t.start();
                t.join();
            } else if (!codes.isEmpty() && etat.equals("remplirVoiture")) {
                ts.put("active_pompe" + pompe_choisie, codes.get(code_choisi));
                System.out.println("Conducteur : active la pompe");
                ts.put("remplir_voiture" + pompe_choisie, volume_reservoir);
                System.out.println("Conducteur : remplit la voiture");
                String code_epuise = (String) ts.get(new ActualField("code_epuise" + pompe_choisie), new FormalField(String.class))[1];
                System.out.println("Conducteur : regarde si le code est epuise");
                if (!code_epuise.equals("null")) {
                    codes.remove(code_epuise);
                    System.out.println("Conducteur : le code est epuise, supprime le code epuise");
                }
                else{
                    System.out.println("Conducteur : le code n'est pas epuise");
                }
                etat = "rien";
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
package com.mycompany.myjspaceapp;

import org.jspace.*;
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
                ts.put("pompe_choisie", pompe_choisie);
                Object code = ts.get(new ActualField("code_donne"), new FormalField(String.class),
                        new FormalField(String.class));
                codes.add((String) code);
                etat = "remplirVoiture";
            } else if (!codes.isEmpty() && etat.equals("remplirVoiture")) {
                ts.put("active_pompe" + pompe_choisie, codes.get(code_choisi));
                ts.put("remplir_voiture" + pompe_choisie, volume_reservoir);
                // ( In(ts, <| code_epuise + pompe, string, ? c, string|>).
                // Conducteur(codes.retire(c), volume_reservoir, i, pompe, rien)
                // +
                // Conducteur(codes, 0, i, pompe, rien) )
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
package com.mycompany.myjspaceapp;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Caisse implements Runnable {

    private Space ts;

    public Caisse(Space ts){
        this.ts = ts;
    }

    public void run() {
        try {
            float argent = (float) ts.get(new ActualField("somme_paye"), new FormalField(Float.class))[1];
            System.out.println("Caisse : recupere l'argent : " + argent);

            String pompe_choisie = (String) ts.get(new ActualField("pompe_choisie"), new FormalField(String.class))[1];
            System.out.println("Caisse : recupere la pompe choisie : " + pompe_choisie);

            CodeGenerator c = CodeGenerator.getInstance();
            String code = c.next();
            ts.put(code, argentToVolumeEssence(argent));
            System.out.println("Caisse : creer le code : " + code);

            ts.put("code_donne", code);
            System.out.println("Caisse : envoie le code");

            ts.put(pompe_choisie,argentToVolumeEssence(argent));
            System.out.println("Caisse : remplit la pompe");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private float argentToVolumeEssence(float argent){
        return argent/1.5f;
    }
}
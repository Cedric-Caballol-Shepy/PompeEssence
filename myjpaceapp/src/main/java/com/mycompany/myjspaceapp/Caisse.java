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
        String code;
        Object[] argent, pompe_choisie;
        try {
            argent = ts.get(new ActualField("somme_paye"), new FormalField(Float.class));
            System.out.println("Caisse : recupere l'argent : " + argent[1]);
            pompe_choisie = ts.get(new ActualField("pompe_choisie"), new FormalField(String.class));
            System.out.println("Caisse : recupere la pompe choisie : " + pompe_choisie[1]);
            CodeGenerator c = CodeGenerator.getInstance();
            code = c.next();
            ts.put(code, argentToVolumeEssence((float)argent[1]));
            System.out.println("Caisse : creer le code : " + code);
            ts.put("code_donne", code);
            System.out.println("Caisse : envoie le code");
            ts.put((String)pompe_choisie[1],argentToVolumeEssence((float)argent[1]));
            System.out.println("Caisse : remplit la pompe");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private float argentToVolumeEssence(float argent){
        return argent/1.5f;
    }
}
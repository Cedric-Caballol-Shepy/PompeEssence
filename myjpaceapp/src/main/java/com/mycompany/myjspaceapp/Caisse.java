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
        Object argent, pompe_choisie;
        try {
            argent = ts.get(new ActualField("somme_paye"), new FormalField(Float.class));
            pompe_choisie = ts.get(new ActualField("pompe_choisie"), new FormalField(String.class));
            CodeGenerator c = CodeGenerator.getInstance();
            code = c.next();
            ts.put(code, argentToVolumeEssence((float)argent));
            ts.put("code_donne", code);
            ts.put(pompe_choisie,argentToVolumeEssence((float)argent));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private float argentToVolumeEssence(float argent){
        return argent/1.5f;
    }
}
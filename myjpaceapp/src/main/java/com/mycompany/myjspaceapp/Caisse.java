
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
        //float somme_payee;
        //String pompe_choisie;
        String code;
        Object[] somme_payee, pompe_choisie;
        try {
            somme_payee = ts.get(new ActualField("somme_paye"), new FormalField(Float.class));
            pompe_choisie = ts.get(new ActualField("pompe_choisie"), new FormalField(String.class));
            //TODO : code_donne = random code (faire une fonction)
            ts.put("code_donne", code);
            ts.put(code, argentToVolumeEssence((float)somme_payee));
            ts.put("pompe",pompe_choisie);



        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public float argentToVolumeEssence(float argent){
        return argent/1.5f;
    }
}

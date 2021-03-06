package com.mycompany.myjspaceapp;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class RemplisseurPompe extends PompeAbs{

    RemplisseurPompe(String id, Space ts){
        super(id, ts);
    }

    @Override
    public void run() {
        try {
            float volume_essence_a_ajouter = (float) ts.get(new ActualField(id), new FormalField(Float.class))[1];
            System.out.println(id + " : recupere le volume d'essence : " + volume_essence_a_ajouter);

            float old_volume_pompe = (float) ts.get(new ActualField("volume_pompe"+id), new FormalField(Float.class))[1];
            float volume_pompe = old_volume_pompe + volume_essence_a_ajouter;
            ts.put("volume_pompe"+id,volume_pompe);
            System.out.println(id + " : la pompe s'est remplie");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //run();
    }

}

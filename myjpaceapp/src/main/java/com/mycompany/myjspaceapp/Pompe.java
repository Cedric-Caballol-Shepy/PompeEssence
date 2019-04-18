package com.mycompany.myjspaceapp;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Pompe extends PompeAbs{

    Pompe(String id, Space ts){
       super(id, ts);
    }


    @Override
    public void run() {
        try {
            String code = (String) ts.get(new ActualField("active_pompe"+id), new FormalField(String.class))[1];
            System.out.println("Pompe : recupere le code pour s'activer : " + code);
            float volume_code = (float) ts.get(new ActualField(code), new FormalField(Float.class))[1];
            System.out.println("Pompe : recupere le volume du code : " + volume_code);
            float volume_reservoir = (float) ts.get(new ActualField("remplir_voiture"+id), new FormalField(Float.class))[1];
            System.out.println("Pompe : recupere le volume du reservoir de la voiture : " + volume_reservoir);
            float volume_pompe = (float) ts.get(new ActualField("volume_pompe"+id), new FormalField(Float.class))[1];
            System.out.println("Pompe : Volume d'essence dans la pompe = " + volume_pompe);
            if(volume_pompe > volume_reservoir && volume_code - volume_reservoir > 0){
                ts.put(code, volume_code-volume_reservoir);
                System.out.println("Pompe : met a jour le code {code: " + code + ", volume: " + (volume_code-volume_reservoir) +"}");
                ts.put("code_epuise"+id, "null");
                System.out.println("Pompe : indique que le code n'est pas epuisé");
                volume_pompe -= volume_code;
                ts.put("volume_pompe"+id, volume_pompe);
                System.out.println("Pompe : deduit le volume du code au volume de la pompe {volume_pompe = "+ volume_pompe +"}");
            }
            else if(volume_pompe > volume_reservoir && volume_code - volume_reservoir <= 0){
                ts.put("code_epuise"+id, code);
                System.out.println("Pompe : indique que le code est epuisé");
                volume_pompe -= volume_code;
                ts.put("volume_pompe"+id, volume_pompe);
                System.out.println("Pompe : deduit le volume du code au volume de la pompe {volume_pompe = "+ volume_pompe +"}");
            }
            else{
                // volume_pompe < volume_reservoir
                ts.put("active_pompe"+id,code);
                ts.put(code, volume_code);
                ts.put("remplir_voiture"+id,volume_reservoir);
                ts.put("volume_pompe"+id, volume_pompe);
                System.out.println("Pompe : Pas assez d'essence dans la pompe " + id + "... La pompe attend un nouveau plein");
                ts.query(new ActualField(id), new FormalField(Float.class));
                //on bloque jusqu'à-ce ce que RemplisseurPompe ait réussi à lire un ordre de plein + le temps de remplir la pompe
                run();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

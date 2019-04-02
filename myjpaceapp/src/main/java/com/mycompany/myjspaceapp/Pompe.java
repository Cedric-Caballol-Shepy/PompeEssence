package com.mycompany.myjspaceapp;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Pompe extends PompeAbs{

    public String id;
    private float volume_pompe;
    private Space ts;


    Pompe(String id, float volume_pompe, Space ts){
       super(id,volume_pompe,ts);
    }


    @Override
    public void run() {
        try {
            Object code = ts.get(new ActualField("active_pompe"+id), new FormalField(String.class));
            System.out.println("Pompe : recupere le code pour s'activer : " + (String)code);
            Object volume_code = ts.get(new ActualField((String)code), new FormalField(Float.class));
            System.out.println("Pompe : recupere le volume du code : " + (float)volume_code);
            Object volume_reservoir = ts.get(new ActualField("remplir_pompe"+id), new FormalField(Float.class));
            System.out.println("Pompe : recupere le volume du reservoir de la voiture : " + (float)volume_reservoir);
            if(volume_pompe > (float)volume_reservoir){
                ts.put((String)code,(float)volume_code-(float)volume_reservoir);
                System.out.println("Pompe : met a jour le code");
                ts.put("code_epuise"+id,null);
                System.out.println("Pompe : indique que le code n'est pas epuisé");
                volume_pompe -= (float)volume_reservoir;
                System.out.println("Pompe : deduit son volume");
            }
            else{
                ts.put("code_epuise"+id,(String)code);
                System.out.println("Pompe : indique que le code est epuisé");
                volume_pompe -= (float)volume_code;
                System.out.println("Pompe : deduit son volume");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

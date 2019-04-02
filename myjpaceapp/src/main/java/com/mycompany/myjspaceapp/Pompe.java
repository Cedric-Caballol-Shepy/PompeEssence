package com.mycompany.myjspaceapp;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Pompe extends PompeAbs{

    public int id;
    private float volume_pompe;
    private Space ts;


    Pompe(int id, float volume_pompe, Space ts){
       super(id,volume_pompe,ts);
    }


    @Override
    public void run() {
        try {
            Object code = ts.get(new ActualField("active_pompe"+id), new FormalField(String.class));
            Object volume_code = ts.get(new ActualField((String)code), new FormalField(Float.class));
            Object volume_reservoir = ts.get(new ActualField("remplir_pompe"+id), new FormalField(Float.class));
            if(volume_pompe > (float)volume_reservoir){
                ts.put((String)code,(float)volume_code-(float)volume_reservoir);
                ts.put("code_epuise"+id,null);
                volume_pompe -= (float)volume_reservoir;
            }
            else{
                ts.put("code_epuise"+id,(String)code);
                volume_pompe -= (float)volume_code;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

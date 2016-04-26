package android.bignerdranch.com.mygymbuddy;

/**
 * Created by Mike on 4/25/16.
 */
public class RecoveryEx {

    String name, body_part, discomfort, ins1, ins2, ins3, further;

    public RecoveryEx(String name, String body_part, String discomfort, String ins1, String ins2,
                      String ins3, String further) {

        this.name = name;
        this.body_part = body_part;
        this.discomfort = discomfort;
        this.ins1 = ins1;
        this.ins2 = ins2;
        this.ins3 = ins3;
        this.further = further;

    }


    public RecoveryEx() {

        this.name = "";
        this.body_part = "";
        this.discomfort = "";
        this.ins1 = "";
        this.ins2 = "";
        this.ins3 = "";
        this.further = "";

    }
}
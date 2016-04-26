package android.bignerdranch.com.mygymbuddy;

/**
 * Created by Mike on 3/30/16.
 */
public class Trainer {
        String name, email, password;
        int age, experience, focus, numClients, id;


    public Trainer(String name, String email, String password, int age, int experience, int focus, int numClients, int id){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.experience = experience;
        this.focus = focus;
        this.numClients = numClients;
    }

    public Trainer(String name, String email, String password, int age, int experience, int focus, int id){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.experience = experience;
        this.focus = focus;
        this.numClients = 0;
    }

    public Trainer(String email, String password){
        this.id = 0;
        this.name = "";
        this.email = email;
        this.password = password;
        this.age = -1;
        this.experience = -1;
        this.focus = -1;
        this.numClients = 0;
    }

    public Trainer(int id){
        this.id = id;
        this.name = "";
        this.email = "";
        this.password = "";
        this.age = -1;
        this.experience = -1;
        this.focus = -1;
        this.numClients = 0;
    }

}

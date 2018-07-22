package life.dgrullon.caloriesburned;

import java.io.Serializable;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class User implements Serializable{
    private int mID;
    private String mName;
    private String mAge;
    private String mWeight;
    private String mGender;

    public User(){
        this.mID = 0;
        this.mName = "";
        this.mAge = "";
        this.mWeight = "";
        this.mGender = "";
    }

    // Constructor insert title
    public User(String name) {
        this.mID = 0;
        this.mName = name;
        this.mAge = "";
        this.mWeight = "";
        this.mGender = "";
    }

    public int getID() {
        return mID;
    }

    public void setID(int iD) {
        this.mID = iD;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getAge() {
        return mAge;
    }

    public void setAge(String age) {
        this.mAge = age;
    }

    public String getWeight() {
        return mWeight;
    }

    public void setWeight(String weight) {
        this.mWeight = weight;
    }

    public String getGender() { return mGender; }

    public void setGender(String gender) { this.mGender = gender; }
}

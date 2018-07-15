package life.dgrullon.caloriesburned;

public class METSValues {

    private String metsValue;
    private String name;

    public METSValues(String metsValue, String name) {
        this.metsValue = metsValue;
        this.name = name;
    }


    public String getMetsValue() {
        return metsValue;
    }

    public void setMetsValue(String metsValue) {
        this.metsValue = metsValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof METSValues){
            METSValues mV = (METSValues)obj;
            if(mV.getName().equals(name) && mV.getMetsValue()== metsValue) return true;
        }

        return false;
    }
}

package life.dgrullon.caloriesburned;

public class presetUser {
    private int _id;
    private String _name;
    private String _age;
    private String _weight;

    public presetUser(){
        this._id = 0;
        this._name = "";
        this._age = "";
        this._weight = "";
    }

    // Constructor insert title
    public presetUser(String name) {
        this._id = 0;
        this._name = name;
        this._age = "";
        this._weight = "";
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_age() {
        return _age;
    }

    public void set_age(String _age) {
        this._age = _age;
    }

    public String get_weight() {
        return _weight;
    }

    public void set_weight(String _weight) {
        this._weight = _weight;
    }
}

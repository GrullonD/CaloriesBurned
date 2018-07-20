package life.dgrullon.caloriesburned;

public class presetUser {
    private int _id;
    private String _name;
    private int _age;
    private int _weight;

    public presetUser(){
        this._id = 0;
        this._name = "";
        this._age = 0;
        this._weight = 0;
    }

    // Constructor insert title
    public presetUser(String name) {
        this._id = 0;
        this._name = name;
        this._age = 0;
        this._weight = 0;
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

    public int get_age() {
        return _age;
    }

    public void set_age(int _age) {
        this._age = _age;
    }

    public int get_weight() {
        return _weight;
    }

    public void set_weight(int _weight) {
        this._weight = _weight;
    }
}

package life.dgrullon.caloriesburned;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class enterPreset extends AppCompatActivity {

    EditText mName;
    EditText mAge;
    EditText mWeight;

    String mNameString;
    int mAgeInt;
    double mWeightDouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_preset);

        mName = findViewById(R.id.name);
        mAge = findViewById(R.id.age);
        mWeight = findViewById(R.id.weight);
    }
}

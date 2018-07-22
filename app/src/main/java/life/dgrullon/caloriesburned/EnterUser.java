package life.dgrullon.caloriesburned;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EnterUser extends AppCompatActivity {

    RadioButton mMen;
    RadioButton mWomen;

    EditText mName;
    EditText mAge;
    EditText mWeight;

    String mGenderString;
    String mNameString;
    String mAgeInt;
    String mWeightDouble;

    Button mSaveButton;

    DBHandler mDBHandler;
    User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_preset);

        mMen = findViewById(R.id.menButton);
        mWomen = findViewById(R.id.womenButton);
        mName = findViewById(R.id.name);
        mAge = findViewById(R.id.age);
        mWeight = findViewById(R.id.weight);
        mSaveButton = findViewById(R.id.saveButton);

        mDBHandler = new DBHandler(this, null, null,1);

        setRadioButtonClickListeners();

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameString = mName.getText().toString();
                mAgeInt = mAge.getText().toString();
                mWeightDouble = mWeight.getText().toString();

                if(mMen.isChecked()) mGenderString = "Man";
                else mGenderString = "Woman";

                mUser.setGender(mGenderString);
                mUser.setName(mNameString);
                mUser.setAge(mAgeInt);
                mUser.setWeight(mWeightDouble);

                addNewThoughtToDatabase();

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void setRadioButtonClickListeners() {

        mMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMen.isChecked()){
                    mMen.setChecked(true);
                    mWomen.setChecked(false);
                }
            }
        });

        mWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mWomen.isChecked()){
                    mWomen.setChecked(true);
                    mMen.setChecked(false);
                }
            }
        });

    }
    private void addNewThoughtToDatabase() {
        mDBHandler.addToUsersTable(mUser);
    }

}

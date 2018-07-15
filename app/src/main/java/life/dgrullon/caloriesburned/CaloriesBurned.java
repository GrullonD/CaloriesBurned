package life.dgrullon.caloriesburned;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class CaloriesBurned extends AppCompatActivity {

    TextView mCalculationDisplay;

    Switch  mSwitch;

    RadioButton mMen;
    RadioButton mWomen;

    EditText mAge;
    EditText mWeight;
    EditText mHeartRate;
    EditText mTime;

    Spinner mMETS;

    LinearLayout mGenderLayout;
    FrameLayout mAgeLayout;
    FrameLayout mWeightLayout;
    FrameLayout mHeartRateLayout;
    FrameLayout mTimeLayout;
    FrameLayout mMETSLayout;

    Button mSubmit;

    ArrayList<METSValues> metsList = new ArrayList<>();

    double mCalculationDouble;
    int mAgeInt;
    double mWeightDouble;
    int mHeartRateInt;
    double mTimeDouble;
    double mMETSDouble;

    boolean isCalc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_burned);

        findViews();
        setSwitchOnClickListener();
        setSpinnerAdapterAndOptions();
        setDefaultInputs();
        setTimeTextChangeListener();
        setButtonClickListener();

    }

    private void findViews() {
        mCalculationDisplay = findViewById(R.id.caloriesBurnedNumber);

        mSwitch = findViewById(R.id.switchIcon);
        mMen = findViewById(R.id.menButton);
        mWomen = findViewById(R.id.womenButton);
        mAge = findViewById(R.id.age);
        mWeight = findViewById(R.id.weight);
        mHeartRate = findViewById(R.id.heartRate);
        mTime = findViewById(R.id.time);
        mMETS = findViewById(R.id.METs);

        mSubmit = findViewById(R.id.calculateButton);

        mGenderLayout = findViewById(R.id.genderLayout);
        mAgeLayout = findViewById(R.id.ageLayout);
        mWeightLayout = findViewById(R.id.weightLayout);
        mHeartRateLayout = findViewById(R.id.heartRateLayout);
        mTimeLayout = findViewById(R.id.timeLayout);
        mMETSLayout = findViewById(R.id.METSLayout);
    }

    private void setSwitchOnClickListener() {
        mSwitch.setText(R.string.CALC);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    mSwitch.setText(R.string.METS);
                    isCalc = false;
                    switchToMETS();
                }
                else {
                    mSwitch.setText(R.string.CALC);
                    isCalc = true;
                    switchToCALC();
                }
            }
        });
    }
    private void switchToMETS() {
        mAgeLayout.setVisibility(View.GONE);
        mHeartRateLayout.setVisibility(View.GONE);

        mTimeLayout.setVisibility(View.VISIBLE);
        mWeightLayout.setVisibility(View.VISIBLE);
        mMETSLayout.setVisibility(View.VISIBLE);
    }
    private void switchToCALC() {
        mMETSLayout.setVisibility(View.GONE);

        mAgeLayout.setVisibility(View.VISIBLE);
        mHeartRateLayout.setVisibility(View.VISIBLE);
        mTimeLayout.setVisibility(View.VISIBLE);
        mWeightLayout.setVisibility(View.VISIBLE);
    }

    private void setSpinnerAdapterAndOptions() {
        setSpinnerData();

        //fill data in spinner
        ArrayAdapter<METSValues> adapter = new ArrayAdapter<>(CaloriesBurned.this, R.layout.spinner_toplevel_view, metsList);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_view);
        mMETS.setAdapter(adapter);

    }
    private void setSpinnerData() {
        metsList.add(new METSValues("0.9", "Sleeping"));
        metsList.add(new METSValues("1.0", "Watching Television"));
        metsList.add(new METSValues("1.5", "Desk Work"));
        metsList.add(new METSValues("2.3", "Walking (1.7 mph)"));
        metsList.add(new METSValues("2.9", "Walking (2.5 mph)"));
        metsList.add(new METSValues("3.3", "Walking (3.0 mph)"));
        metsList.add(new METSValues("3.6", "Walking (3.4 mph)"));
        metsList.add(new METSValues("3.0", "Stationary Bicycle (50 watts)"));
        metsList.add(new METSValues("5.5", "Stationary Bicycle (100 watts)"));
        metsList.add(new METSValues("4.0", "Outdoor Bicycle (<10 mph)"));
        metsList.add(new METSValues("3.5", "Calisthenics (Light)"));
        metsList.add(new METSValues("8.0", "Calisthenics (Heavy)"));
        metsList.add(new METSValues("8.0", "Jogging in Place"));
        metsList.add(new METSValues("10.0", "Jump Rope"));
        metsList.add(new METSValues("5.8", "Sexual Activity"));

    }

    private void setButtonClickListener() {

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCalc) {
                    if (!mAge.getText().toString().equals("")) {
                        mAgeInt = Integer.parseInt(mAge.getText().toString());
                    } else mAgeInt = 0;
                    if (!mWeight.getText().toString().equals("")) {
                        mWeightDouble = Double.parseDouble(mWeight.getText().toString());
                    } else mWeightDouble = 0;
                    if (!mHeartRate.getText().toString().equals("")) {
                        mHeartRateInt = Integer.parseInt(mHeartRate.getText().toString());
                    } else mHeartRateInt = 0;
                    if (!mTime.getText().toString().equals("")) {
                        double minutes = Integer.parseInt(mTime.getText().toString().substring(0, 2));
                        double seconds = Integer.parseInt(mTime.getText().toString().substring(4, 6));
                        mTimeDouble = minutes + seconds / 60;
                    } else mTimeDouble = 0;

                    if(mMen.isChecked()){
                        mCalculationDouble = mensCaloriesBurned(mAgeInt, mWeightDouble, mHeartRateInt, mTimeDouble);
                    }
                    else {
                        mCalculationDouble = womensCaloriesBurned(mAgeInt, mWeightDouble, mHeartRateInt, mTimeDouble);
                    }
                }
                else {
                    if (Double.parseDouble(((METSValues) mMETS.getSelectedItem()).getMetsValue()) >= 0) {
                        mMETSDouble = Double.parseDouble(((METSValues) mMETS.getSelectedItem()).getMetsValue());
                    } else mMETSDouble = 0;
                    if (!mTime.getText().toString().equals("")) {
                        double minutes = Integer.parseInt(mTime.getText().toString().substring(0, 2));
                        double seconds = Integer.parseInt(mTime.getText().toString().substring(4, 6));
                        mTimeDouble = (minutes + seconds / 60) / 60;
                    } else mTimeDouble = 0;
                    if (!mWeight.getText().toString().equals("")) {
                        mWeightDouble = Double.parseDouble(mWeight.getText().toString());
                        mWeightDouble = mWeightDouble * 0.453592; // Convert Lbs to KG
                    } else mWeightDouble = 0;

                    mCalculationDouble = metsCaloriesBurned(mMETSDouble, mWeightDouble, mTimeDouble);
                }
                mCalculationDisplay.setText(String.format(Locale.getDefault(), "%.2f", mCalculationDouble));

            }
        });
    }
    private double mensCaloriesBurned (int age, double weight, int heartRate, double time) {
        return round(((0.2017*age + 0.1988*weight + 0.6309*heartRate - 55.0969)*time) / 4.184,2);
    }
    private double womensCaloriesBurned (int age, double weight, int heartRate, double time) {
        return round(((0.0740*age + 0.1263*weight + 0.4472*heartRate - 20.4022)*time) / 4.184,2);
    }
    private double metsCaloriesBurned (double metsValue, double weight, double time) {
        return round(metsValue*weight*time,2);
    }
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);

        return (double) tmp / factor;
    }

    private void setTimeTextChangeListener() {
        mTime.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                if(mTime.getSelectionStart() == 2) {
                //if (mTime.getText().length() == 2) {
                    String tempText = mTime.getText() + "m ";
                    mTime.setText(tempText);
                    mTime.setSelection(mTime.getText().length());
                }
                if(mTime.getSelectionStart() == 6) {
                //if (mTime.getText().length() == 6) {
                    String tempText = mTime.getText() + "s";
                    mTime.setText(tempText);
                    mTime.setSelection(mTime.getText().length());
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        mTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    if (mTime.getText().length() == 1) {
                        String tempText = "0" + mTime.getText() + "m 00s";
                        mTime.setText(tempText);
                    }
                    else if (mTime.getText().length() == 2) {
                        String tempText = mTime.getText() + "m 00s";
                        mTime.setText(tempText);
                    }
                    else if (mTime.getText().length() == 3) {
                        String tempText = mTime.getText() + " 00s";
                        mTime.setText(tempText);
                    }
                }
                else {
                    mTime.setText("");
                    mTime.setSelection(mTime.getText().length());
                }
            }
        });
    }

    private void setDefaultInputs(){

        int tempAge = 28;
        double tempWeight = 176.42;
        int tempHeartRate = 135;
        int tempTimeMinutes = 15;
        int tempTimeSeconds = 30;
        String tempTime =   Integer.toString(tempTimeMinutes) + "m " +
                            Integer.toString(tempTimeSeconds) + "s";

        mMen.setChecked(true);
        mAge.setText(String.format(Locale.getDefault(),"%d",tempAge),TextView.BufferType.EDITABLE);
        mWeight.setText(String.format(Locale.getDefault(),"%.2f",tempWeight),TextView.BufferType.EDITABLE);
        mHeartRate.setText(String.format(Locale.getDefault(),"%d",tempHeartRate),TextView.BufferType.EDITABLE);
        mTime.setText(tempTime,TextView.BufferType.EDITABLE);

    }
}

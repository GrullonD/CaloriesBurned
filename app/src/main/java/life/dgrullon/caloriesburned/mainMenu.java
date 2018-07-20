package life.dgrullon.caloriesburned;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

public class mainMenu extends AppCompatActivity {

    RecyclerView mPresetList;
    ImageButton mAddPreset;
    ImageButton mDeletePreset;
    ImageButton mQuickAdd;

    int testValue = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        findViews();

        mAddPreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mainMenu.this, enterPreset.class);
                mainMenu.this.startActivity(myIntent);
            }
        });

        mQuickAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mainMenu.this, CaloriesBurned.class);
                myIntent.putExtra("MAINMENU", testValue); //Optional parameters
                mainMenu.this.startActivity(myIntent);
            }
        });
    }

    private void findViews() {
        mPresetList = findViewById(R.id.presetList);
        mAddPreset = findViewById(R.id.addPreset);
        mDeletePreset = findViewById(R.id.deletePreset);
        mQuickAdd = findViewById(R.id.quickAdd);
    }

}

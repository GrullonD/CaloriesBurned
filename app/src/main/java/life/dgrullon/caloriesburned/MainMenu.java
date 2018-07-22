package life.dgrullon.caloriesburned;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainMenu extends AppCompatActivity {

    ListView mPresetList;
    Context mContext;

    ImageButton mAddPreset;
    ImageButton mDeletePreset;
    ImageButton mQuickAdd;

    int testValue = 5;

    DBHandler mDBHandler;
    User[] mUser;
    User mBlankUser = new User("QuickAdd");

    boolean mUserSelected = false;
    int mPositionOldSelectedUser = 0;
    View mViewOldSelectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //this.deleteDatabase("maindatabase.db");

        mDBHandler = new DBHandler(this, null, null,1);

        findViews();
        mContext = MainMenu.this.getApplicationContext();

        mAddPreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this, EnterUser.class);
                MainMenu.this.startActivity(myIntent);
            }
        });

        mQuickAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this, CaloriesBurned.class);
                if(mUserSelected) {
                    myIntent.putExtra("MAINMENU", mUser[mPositionOldSelectedUser]);
                }
                else {
                    myIntent.putExtra("MAINMENU", mBlankUser); //Optional parameters
                }
                MainMenu.this.startActivity(myIntent);
            }
        });

        mDeletePreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDBHandler.deleteDBUserRow(mUser[mPositionOldSelectedUser]);
                mUserSelected = false;
                mViewOldSelectedUser = null;
                printUsers();
            }
        });

        printUsers();
        mPresetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deselectItem(mViewOldSelectedUser, mPositionOldSelectedUser);
                selectItem(view, position);
                mPositionOldSelectedUser = position;
                mViewOldSelectedUser = view;
                mUserSelected = true;
            }
        });


    }

    private void deselectItem(View view, int position) {
        mPresetList.setItemChecked(position, false);
        if(view!=null){ view.setBackgroundColor(getResources().getColor(R.color.mainMenuSpinnerBackground)); }
    }

    private void selectItem(View view, int position) {
        mPresetList.setItemChecked(position, true);
        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onResume(){
        super.onResume();

        // Functions
        printUsers();
        mUserSelected = false;
        deselectItem(mViewOldSelectedUser, mPositionOldSelectedUser);
    }


    private void findViews() {
        mPresetList = findViewById(R.id.presetList);
        mAddPreset = findViewById(R.id.addPreset);
        mDeletePreset = findViewById(R.id.deletePreset);
        mQuickAdd = findViewById(R.id.quickAdd);
    }

    public void printUsers() {
        mUser = mDBHandler.dbTableUsersToArray();
        if(mUser != null){
            int arrayCount = mUser.length;
            String[] dbString = new String[arrayCount];

            for(int x = 0; x<arrayCount ; x++)
            {
                dbString[x] = mUser[x].getName();
            }
            ListAdapter presetListAdapter = new ArrayAdapter<>(this, R.layout.list_white_text, R.id.list_content, dbString);
            mPresetList.setAdapter(presetListAdapter);
        }
    }
}

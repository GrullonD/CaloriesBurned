package life.dgrullon.caloriesburned;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "maindatabase.db";

    // For the table 'Journal Entry'
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_GENDER = "_gender";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_AGE = "_age";
    private static final String COLUMN_WEIGHT = "_weight";

    // Commom table columns
    private static final String COLUMN_ID = "_id";

    private SQLiteDatabase mDatabase;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        mDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDBTableUsers(db);

        this.mDatabase = db;
    }
    private void createDBTableUsers(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " TEXT, " +
                COLUMN_WEIGHT + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Add a new row to the database
    public int addToUsersTable(User user) {

        int rowID = 0;

        // Get values to insert to row
        ContentValues values = new ContentValues();
        values.put( COLUMN_GENDER, user.getGender() );
        values.put( COLUMN_NAME, user.getName() );
        values.put( COLUMN_AGE, user.getAge() );
        values.put( COLUMN_WEIGHT, user.getWeight() );

        // Insert into database
        rowID = (int) this.mDatabase.insert(TABLE_USERS, null, values);

        return rowID;
    }

    // Edit a row in the database
    public void editJournalEntriesTable(User oldUserEntry, User newUserEntry) {

        String whereClause = COLUMN_ID + " = " + oldUserEntry.getID();

        // Get values to insert to row
        ContentValues values = new ContentValues();
        values.put( COLUMN_GENDER, newUserEntry.getGender() );
        values.put( COLUMN_NAME, newUserEntry.getName() );
        values.put( COLUMN_AGE, newUserEntry.getAge() );
        values.put( COLUMN_WEIGHT, newUserEntry.getWeight() );

        // Insert into database
        this.mDatabase.update(TABLE_USERS, values, whereClause, null);

    }

    // Delete a row from the database
    public void deleteDBUserRow(User user) {

        String[] whereArgs = new String[1];
        String table;
        String whereClause;
        table = TABLE_USERS;
        whereClause = COLUMN_ID + "=?";
        whereArgs[0] = String.valueOf(user.getID());
        this.mDatabase.delete(table, whereClause, whereArgs);
    }

    // Get information from table 'Journal Entries' and put into array
    public User[] dbTableUsersToArray() {

        // Needed variables
        User[] users;
        int tableIncrementer = 0;

        // Point cursor to first location
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE 1";
        Cursor cursor = this.mDatabase.rawQuery(query,null);

        users = getNumberOfUsersAvailable(cursor);

        Log.v("UsersTable", DatabaseUtils.dumpCursorToString(cursor));

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            if(cursor.getString(cursor.getColumnIndex(COLUMN_NAME))!=null){
                setGenderNameAgeWeight(users[tableIncrementer], cursor);
                tableIncrementer++;
            }
            cursor.moveToNext();
        }

        return users;
    }
    private void setGenderNameAgeWeight(User user, Cursor cursor) {
        user.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        user.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
        user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        user.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)));
        user.setWeight(cursor.getString(cursor.getColumnIndex(COLUMN_WEIGHT)));
    }

    @NonNull
    private User[] getNumberOfUsersAvailable(Cursor cursor) {
        User[] users;
        int tableCount = cursor.getCount();
        users = new User[tableCount];
        for( int i = 0; i < users.length; i++) { users[i] = new User(); }
        return users;
    }

    public void closeDB() {
        mDatabase.close();
    }

}
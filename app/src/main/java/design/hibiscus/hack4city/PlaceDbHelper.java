package design.hibiscus.hack4city;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS-PC on 8.10.2017.
 */

public class PlaceDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Location.db";
    private static final int DATABASE_VERSION = 1;

    public PlaceDbHelper(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}



    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PLACES_TABLE = "CREATE TABLE"+ PlaceContract.PlaceEntry.TABLE_NAME+"("+
                PlaceContract.PlaceEntry.COLUMN_PLACE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                PlaceContract.PlaceEntry.COLUMN_PLACE_ID+" TEXT NOT NULL,"+
                "UNIQUE ("+ PlaceContract.PlaceEntry.COLUMN_PLACE_ID+") ON CONFLICT REPLACE"+"); ";

        db.execSQL(SQL_CREATE_PLACES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS"+ PlaceContract.PlaceEntry.TABLE_NAME);
            onCreate(db);
    }
}

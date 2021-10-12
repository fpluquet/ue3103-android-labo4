package be.fpluquet.labo4.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import be.fpluquet.labo4.db.CrimeDbSchema.CrimeTable;

public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ CrimeTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, "
                + CrimeTable.cols.UUID + ", " + CrimeTable.cols.TITLE + ", "
                + CrimeTable.cols.DATE + ", " + CrimeTable.cols.SOLVED + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

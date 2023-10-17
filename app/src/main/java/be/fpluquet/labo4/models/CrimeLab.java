package be.fpluquet.labo4.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import be.fpluquet.labo4.db.CrimeBaseHelper;
import be.fpluquet.labo4.db.CrimeCursorWrapper;
import be.fpluquet.labo4.db.CrimeDbSchema;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context;
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public void addCrime(Crime crime) {
        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, getContentValues(crime));
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbSchema.CrimeTable.NAME,
                values,
                CrimeDbSchema.CrimeTable.cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor =
                queryCrimes(CrimeDbSchema.CrimeTable.cols.UUID + " = ? ",
                        new String[]{id.toString()}
                );
        try {
            if (cursor.getCount() == 0)
                return null;

            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public List<Crime> getCrimes() {
        ArrayList<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    private ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.CrimeTable.cols.UUID, crime.getId().toString());
        values.put(CrimeDbSchema.CrimeTable.cols.TITLE, crime.getTitle());
        values.put(CrimeDbSchema.CrimeTable.cols.DATE, crime.getDate().getTime());
        values.put(CrimeDbSchema.CrimeTable.cols.SOLVED, crime.isSolved() ? 1 : 0);
        return values;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        return new CrimeCursorWrapper(mDatabase.query(
                CrimeDbSchema.CrimeTable.NAME,
                null, //all columns
                whereClause,
                whereArgs,
                null, null, null));
    }
}
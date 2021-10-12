package be.fpluquet.labo4.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import be.fpluquet.labo4.models.Crime;

public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }

    public Crime getCrime()
    {
        String uuidString = getString(getColumnIndex(CrimeDbSchema.CrimeTable.cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.cols.TITLE));
        long date = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.cols.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved!=0);
        return crime;
    }
}


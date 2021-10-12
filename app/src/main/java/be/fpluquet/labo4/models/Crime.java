package be.fpluquet.labo4.models;

import java.util.Date;
import java.util.UUID;

public class Crime {
    protected java.util.UUID mId;
    protected String mTitle;
    protected java.util.Date mDate;
    protected boolean mSolved;

    public Crime() {
        this(UUID.randomUUID());
        this.mTitle = "Titre par défaut";
        this.mSolved = false;
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public void setSolved(boolean solved) {
        this.mSolved = solved;
    }
}

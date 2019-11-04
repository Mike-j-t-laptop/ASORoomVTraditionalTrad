package aso.asoroomvtraditionaltrad;

public class Genre {

    long mGenreId = -1;
    String mName;

    public Genre() {
        this.mGenreId = -1;
    }

    public Genre(String mName) {
        this.mGenreId = -1;
        this.mName = mName;
    }

    public long getmGenreId() {
        return mGenreId;
    }

    public void setmGenreId(long mGenreId) {
        this.mGenreId = mGenreId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}

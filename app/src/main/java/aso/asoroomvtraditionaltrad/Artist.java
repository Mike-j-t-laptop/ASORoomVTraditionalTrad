package aso.asoroomvtraditionaltrad;

public class Artist {

    long mArtistid = -1;
    String mName;

    public Artist() {}

    public Artist(String mName) {
        this.mArtistid = -1;
        this.mName = mName;
    }

    public long getmArtistid() {
        return mArtistid;
    }

    public void setmArtistid(long mArtistid) {
        this.mArtistid = mArtistid;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}

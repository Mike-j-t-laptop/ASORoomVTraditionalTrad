package aso.asoroomvtraditionaltrad;

public class PlayList {

    long mPlayListId = -1;
    String mName;

    public PlayList() {
        this.mPlayListId = -1;
    }

    public PlayList(String mName) {
        this.mPlayListId = -1;
        this.mName = mName;
    }

    public long getmPlayListId() {
        return mPlayListId;
    }

    public void setmPlayListId(long mPlayListId) {
        this.mPlayListId = mPlayListId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
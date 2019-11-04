package aso.asoroomvtraditionaltrad;

public class PlayListTrack {
    long mPlayListIdReference = -1;
    long mTrackIdReference = -1;

    public PlayListTrack(){
    }

    public PlayListTrack(long mPlayListIdReference, long mTrackIdReference) {
        this.mPlayListIdReference = mPlayListIdReference;
        this.mTrackIdReference = mPlayListIdReference;
    }

    public long getmPlayListIdReference() {
        return mPlayListIdReference;
    }

    public void setmPlayListIdReference(long mPlayListIdReference) {
        this.mPlayListIdReference = mPlayListIdReference;
    }

    public long getmTrackIdReference() {
        return mTrackIdReference;
    }

    public void setmTrackIdReference(long mTrackIdReference) {
        this.mTrackIdReference = mTrackIdReference;
    }
}

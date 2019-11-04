package aso.asoroomvtraditionaltrad;

public class Track {

    long mTrackId = -1;
    String mName;
    long mAlbumIdReference;
    long mMediaTypeIdReference;
    long mGenreIdReference;
    String mComposer;
    long mMilliseconds;
    long mBytes;
    String mUnitPrice;

    public Track() {
        mTrackId = -1;
    }

    public Track(
            String mName,
            long mAlbumIdReference,
            long mMediaTypeIdReference,
            long mGenreIdReference,
            String mComposer,
            long mMilliseconds,
            long mBytes,
            String mUnitPrice
    ) {
        this.mTrackId = -1;
        this.mName = mName;
        this.mAlbumIdReference = mAlbumIdReference;
        this.mMediaTypeIdReference = mMediaTypeIdReference;
        this.mGenreIdReference = mGenreIdReference;
        this.mComposer = mComposer;
        this.mMilliseconds = mMilliseconds;
        this.mBytes = mBytes;
        this.mUnitPrice = mUnitPrice;
    }

    public long getmTrackId() {
        return mTrackId;
    }

    public void setmTrackId(long mTrackId) {
        this.mTrackId = mTrackId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public long getmAlbumIdReference() {
        return mAlbumIdReference;
    }

    public void setmAlbumIdReference(long mAlbumIdReference) {
        this.mAlbumIdReference = mAlbumIdReference;
    }

    public long getmMediaTypeIdReference() {
        return mMediaTypeIdReference;
    }

    public void setmMediaTypeIdReference(long mMediaTypeIdReference) {
        this.mMediaTypeIdReference = mMediaTypeIdReference;
    }

    public long getmGenreIdReference() {
        return mGenreIdReference;
    }

    public void setmGenreIdReference(long mGenreIdReference) {
        this.mGenreIdReference = mGenreIdReference;
    }

    public String getmComposer() {
        return mComposer;
    }

    public void setmComposer(String mComposer) {
        this.mComposer = mComposer;
    }

    public long getmMilliseconds() {
        return mMilliseconds;
    }

    public void setmMilliseconds(long mMilliseconds) {
        this.mMilliseconds = mMilliseconds;
    }

    public long getmBytes() {
        return mBytes;
    }

    public void setmBytes(long mBytes) {
        this.mBytes = mBytes;
    }

    public String getmUnitPrice() {
        return mUnitPrice;
    }

    public void setmUnitPrice(String mUnitPrice) {
        this.mUnitPrice = mUnitPrice;
    }
}

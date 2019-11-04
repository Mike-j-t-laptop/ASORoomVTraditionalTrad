package aso.asoroomvtraditionaltrad;

public class Album {
    long mAlbumId;
    String mTitle;
    long mArtistIdReference;

    public Album(){
        this.mAlbumId = -1;
    }

    public Album(String title, long artistIdReference) {
        this.mAlbumId = -1;
        this.mTitle = title;
        this.mArtistIdReference = artistIdReference;
    }

    public long getmAlbumId() {
        return mAlbumId;
    }

    public void setmAlbumId(long mAlbumId) {
        this.mAlbumId = mAlbumId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public long getmArtistIdReference() {
        return mArtistIdReference;
    }

    public void setmArtistIdReference(long mArtistIdReference) {
        this.mArtistIdReference = mArtistIdReference;
    }
}

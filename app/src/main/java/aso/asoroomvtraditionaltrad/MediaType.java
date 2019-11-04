package aso.asoroomvtraditionaltrad;

public class MediaType {

    long mMediaTypeId = -1;
    String mName;

    public MediaType(){
        this.mMediaTypeId = -1;
    }

    public MediaType(
            String mName
    ) {
        this.mMediaTypeId = -1;
        this.mName = mName;
    }

    public long getmMediaTypeId() {
        return mMediaTypeId;
    }

    public void setmMediaTypeId(long mMediaTypeId) {
        this.mMediaTypeId = mMediaTypeId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}

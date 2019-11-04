package aso.asoroomvtraditionaltrad;

public class Customer {

    long mCustomerid = -1;
    String mFirstname;
    String mLastName;
    String mCompany;
    String mAddress;
    String mCity;
    String mState;
    String mCountry;
    String mPostCode;
    String mPhone;
    String mFax;
    String mEmail;
    long mSupportRepReference;

    public Customer() {
        mCustomerid = -1;
    }

    public Customer(
            String mFirstname, String mLastName,
            String mCompany,
            String mAddress, String mCity, String mState, String mCountry, String mPostCode,
            String mPhone, String mFax, String mEmail,
            long mSupportRepReference
    )
    {
        this.mCustomerid = -1;
        this.mFirstname = mFirstname;
        this.mLastName = mLastName;
        this.mCompany = mCompany;
        this.mAddress = mAddress;
        this.mCity = mCity;
        this.mState = mState;
        this.mCountry = mCountry;
        this.mPostCode = mPostCode;
        this.mPhone = mPhone;
        this.mFax = mFax;
        this.mEmail = mEmail;
        this.mSupportRepReference = mSupportRepReference;
    }

    public long getmCustomerid() {
        return mCustomerid;
    }

    public void setmCustomerid(long mCustomerid) {
        this.mCustomerid = mCustomerid;
    }

    public String getmFirstname() {
        return mFirstname;
    }

    public void setmFirstname(String mFirstname) {
        this.mFirstname = mFirstname;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public String getmPostCode() {
        return mPostCode;
    }

    public void setmPostCode(String mPostCode) {
        this.mPostCode = mPostCode;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmFax() {
        return mFax;
    }

    public void setmFax(String mFax) {
        this.mFax = mFax;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public long getmSupportRepReference() {
        return mSupportRepReference;
    }

    public void setmSupportRepReference(long mSupportRepReference) {
        this.mSupportRepReference = mSupportRepReference;
    }
}

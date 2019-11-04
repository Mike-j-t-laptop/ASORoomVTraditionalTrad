package aso.asoroomvtraditionaltrad;

public class Employee {

    long mEmployeeId = -1;
    String mLastName;
    String mFirstName;
    String mTitle;
    long mReportsTo;
    String mBirthDate;
    String mHireDate;
    String mAddress;
    String mCity;
    String mState;
    String mCountry;
    String mPostCode;
    String mPhone;
    String mFax;
    String mEmail;

    public Employee() {
        this.mEmployeeId = -1;
    }

    public Employee(
            String mLastName, String mFirstName, String mTitle,
            long mReportsTo,
            String mBirthDate, String mHireDate,
            String mAddress, String mCity, String mState, String mCountry, String mPostCode,
            String mPhone, String mFax, String mEmail
    ) {
        this.mEmployeeId = -1;
        this.mLastName = mLastName;
        this.mFirstName = mFirstName;
        this.mTitle = mTitle;
        this.mReportsTo = mReportsTo;
        this.mBirthDate = mBirthDate;
        this.mHireDate = mHireDate;
        this.mAddress = mAddress;
        this.mCity = mCity;
        this.mState = mState;
        this.mCountry = mCountry;
        this.mPostCode = mPostCode;
        this.mPhone = mPhone;
        this.mFax = mFax;
        this.mEmail = mEmail;
    }


    public long getmEmployeeId() {
        return mEmployeeId;
    }

    public void setmEmployeeId(long mEmployeeId) {
        this.mEmployeeId = mEmployeeId;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastname) {
        this.mLastName = mLastname;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public long getmReportsTo() {
        return mReportsTo;
    }

    public void setmReportsTo(long mReportsTo) {
        this.mReportsTo = mReportsTo;
    }

    public String getmBirthDate() {
        return mBirthDate;
    }

    public void setmBirthDate(String mBirthDate) {
        this.mBirthDate = mBirthDate;
    }

    public String getmHireDate() {
        return mHireDate;
    }

    public void setmHireDate(String mHireDate) {
        this.mHireDate = mHireDate;
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

    public String getmPostCode() {
        return mPostCode;
    }

    public void setmPostCode(String mPostCode) {
        this.mPostCode = mPostCode;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
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
}

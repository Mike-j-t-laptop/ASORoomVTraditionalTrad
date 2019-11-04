package aso.asoroomvtraditionaltrad;

public class Invoice {
    long mInvoiceId = -1;
    long mCustomerIdReference;
    String mInvoiceDate;
    String mBillingAddress;
    String mBillingCity;
    String mBillingState;
    String mBillingCountry;
    String mBillingPostcode;
    String mTotal;

    public Invoice() {
        mInvoiceId = -1;
    }

    public Invoice(
            long mCustomerIdReference,
            String mInvoiceDate,
            String mBillingAddress, String mBillingCity, String mBillingState, String mBillingCountry, String mBillingPostcode,
            String mTotal
    ) {

        this.mInvoiceId = -1;
        this.mCustomerIdReference = mCustomerIdReference;
        this.mInvoiceDate = mInvoiceDate;
        this.mBillingAddress = mBillingAddress;
        this.mBillingCity = mBillingCity;
        this.mBillingState = mBillingState;
        this.mBillingCountry = mBillingCountry;
        this.mBillingPostcode = mBillingPostcode;
        this.mTotal = mTotal;
    }

    public long getmInvoiceId() {
        return mInvoiceId;
    }

    public void setmInvoiceId(long mInvoiceId) {
        this.mInvoiceId = mInvoiceId;
    }

    public long getmCustomerIdReference() {
        return mCustomerIdReference;
    }

    public void setmCustomerIdReference(long mCustomerIdReference) {
        this.mCustomerIdReference = mCustomerIdReference;
    }

    public String getmInvoiceDate() {
        return mInvoiceDate;
    }

    public void setmInvoiceDate(String mInvoiceDate) {
        this.mInvoiceDate = mInvoiceDate;
    }

    public String getmBillingAddress() {
        return mBillingAddress;
    }

    public void setmBillingAddress(String mBillingAddress) {
        this.mBillingAddress = mBillingAddress;
    }

    public String getmBillingCity() {
        return mBillingCity;
    }

    public void setmBillingCity(String mBillingCity) {
        this.mBillingCity = mBillingCity;
    }

    public String getmBillingState() {
        return mBillingState;
    }

    public void setmBillingState(String mBillingState) {
        this.mBillingState = mBillingState;
    }

    public String getmBillingCountry() {
        return mBillingCountry;
    }

    public void setmBillingCountry(String mBillingCountry) {
        this.mBillingCountry = mBillingCountry;
    }

    public String getmBillingPostcode() {
        return mBillingPostcode;
    }

    public void setmBillingPostcode(String mBillingPostcode) {
        this.mBillingPostcode = mBillingPostcode;
    }

    public String getmTotal() {
        return mTotal;
    }

    public void setmTotal(String mTotal) {
        this.mTotal = mTotal;
    }
}

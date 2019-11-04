package aso.asoroomvtraditionaltrad;

public class InvoiceItem {
    long mInvoiceItemId = -1;
    long mInvoiceIdReference;
    long mTrackIdReference;
    String mUnitPrice;
    int mQuantity;

    public InvoiceItem() {
        mInvoiceItemId = -1;
    }

    public InvoiceItem(
            long mInvoiceIdReference,long mTrackIdReference,
            String mUnitPrice, int mQuantity
    ) {
        this.mInvoiceItemId = -1;
        this.mInvoiceIdReference = mInvoiceIdReference;
        this.mTrackIdReference = mTrackIdReference;
        this.mUnitPrice = mUnitPrice;
        this.mQuantity = mQuantity;
    }

    public long getmInvoiceItemId() {
        return mInvoiceItemId;
    }

    public void setmInvoiceItemId(long mInvoiceItemId) {
        this.mInvoiceItemId = mInvoiceItemId;
    }

    public long getmInvoiceIdReference() {
        return mInvoiceIdReference;
    }

    public void setmInvoiceIdReference(long mInvoiceIdReference) {
        this.mInvoiceIdReference = mInvoiceIdReference;
    }

    public long getmTrackIdReference() {
        return mTrackIdReference;
    }

    public void setmTrackIdReference(long mTrackIdReference) {
        this.mTrackIdReference = mTrackIdReference;
    }

    public String getmUnitPrice() {
        return mUnitPrice;
    }

    public void setmUnitPrice(String mUnitPrice) {
        this.mUnitPrice = mUnitPrice;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }
}

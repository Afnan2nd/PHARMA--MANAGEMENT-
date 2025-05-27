public abstract class Medicine {
    protected String brandName;
    protected String medicineName;
    protected String pharmaceuticals;
    protected double price;
    protected int availableQuantity;

    public Medicine() {}

    public Medicine(String brandName, String medicineName, String pharmaceuticals, double price, int availableQuantity) {
        this.brandName = brandName;
        this.medicineName = medicineName;
        this.pharmaceuticals = pharmaceuticals;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public void setBrandName(String brandName) { this.brandName = brandName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    public void setPharmaceuticals(String pharmaceuticals) { this.pharmaceuticals = pharmaceuticals; }
    public void setPrice(double price) { this.price = price; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }

    public String getBrandName() { return brandName; }
    public String getMedicineName() { return medicineName; }
    public String getPharmaceuticals() { return pharmaceuticals; }
    public double getPrice() { return price; }
    public int getAvailableQuantity() { return availableQuantity; }

    public abstract void showDetails();
}
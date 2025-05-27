public class Tablet extends Medicine {
    private String category;

    public Tablet() {}

    public Tablet(String brandName, String medicineName, String pharmaceuticals, double price, int availableQuantity, String category) {
        super(brandName, medicineName, pharmaceuticals, price, availableQuantity);
        this.category = category;
    }

    public void setCategory(String category) { this.category = category; }
    public String getCategory() { return category; }

    public void showDetails() {
        System.out.println("Tablet: " + medicineName + ", Brand: " + brandName + ", Category: " + category);
    }
}
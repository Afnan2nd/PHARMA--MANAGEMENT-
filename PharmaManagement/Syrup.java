public class Syrup extends Medicine {
    private String flavor;

    public Syrup() {}

    public Syrup(String brandName, String medicineName, String pharmaceuticals, double price, int availableQuantity, String flavor) {
        super(brandName, medicineName, pharmaceuticals, price, availableQuantity);
        this.flavor = flavor;
    }

    public void setFlavor(String flavor) { this.flavor = flavor; }
    public String getFlavor() { return flavor; }

    public void showDetails() {
        System.out.println("Syrup: " + medicineName + ", Brand: " + brandName + ", Flavor: " + flavor);
    }
}
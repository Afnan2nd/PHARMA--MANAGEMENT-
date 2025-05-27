public class Pharmacy {
    private String name;
    private Medicine[] listOfMedicines;

    public Pharmacy() {
        listOfMedicines = new Medicine[100];
    }

    public Pharmacy(String name) {
        this();
        this.name = name;
    }

    public boolean insertMedicine(Medicine m) {
        for (int i = 0; i < listOfMedicines.length; i++) {
            if (listOfMedicines[i] == null) {
                listOfMedicines[i] = m;
                return true;
            }
        }
        return false;
    }

    public Medicine searchMedicine(String brandName) {
        for (Medicine m : listOfMedicines) {
            if (m != null && m.getBrandName().equals(brandName)) {
                return m;
            }
        }
        return null;
    }

    public void showAllMedicines() {
        for (Medicine m : listOfMedicines) {
            if (m != null) {
                m.showDetails();
            }
        }
    }
}
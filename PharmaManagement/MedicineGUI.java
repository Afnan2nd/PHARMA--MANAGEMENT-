import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class MedicineGUI extends JFrame implements ActionListener {
    private JTextField brandNameField, medicineNameField, pharmaceuticalsField, priceField, quantityField, amountField;
    private JTextArea outputArea;
    private Medicine currentMedicine;
    private JButton createButton, addQtyBtn, sellQtyBtn, showDetailsBtn;
    
    private Medicine[] medicines = new Medicine[100];
    private int medicineCount = 0;

    public MedicineGUI() {
        setTitle("Medicine Information System");
        setLayout(null);
        setSize(515, 740);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon logoIcon = new ImageIcon("pharma-logo.png"); // Logo Input
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(180, 10, 150, 100);  
        add(logoLabel);

        JLabel brandLabel = new JLabel("Brand Name:");
        brandLabel.setBounds(30, 120, 100, 25);
        add(brandLabel);

        brandNameField = new JTextField();
        brandNameField.setBounds(150, 120, 200, 25);
        add(brandNameField);

        JLabel medLabel = new JLabel("Medicine Name:");
        medLabel.setBounds(30, 160, 120, 25);
        add(medLabel);

        medicineNameField = new JTextField();
        medicineNameField.setBounds(150, 160, 200, 25);
        add(medicineNameField);

        JLabel pharmaLabel = new JLabel("Pharmaceuticals:");
        pharmaLabel.setBounds(30, 200, 120, 25);
        add(pharmaLabel);

        pharmaceuticalsField = new JTextField();
        pharmaceuticalsField.setBounds(150, 200, 200, 25);
        add(pharmaceuticalsField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(30, 240, 100, 25);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(150, 240, 200, 25);
        add(priceField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(30, 280, 100, 25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(150, 280, 200, 25);
        add(quantityField);

        createButton = new JButton("Create Medicine");
        createButton.setBounds(150, 320, 180, 25);
        createButton.setBackground(new Color(34, 139, 34)); // Button Color
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(this);
        add(createButton);

        JLabel amountLabel = new JLabel("Amount (+/-):");
        amountLabel.setBounds(30, 360, 100, 25);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 360, 200, 25);
        add(amountField);

        addQtyBtn = new JButton("Add Quantity");
        addQtyBtn.setBounds(30, 400, 140, 25);
        addQtyBtn.setBackground(new Color(34, 139, 34)); // Button Color
        addQtyBtn.setForeground(Color.WHITE);
        addQtyBtn.addActionListener(this);
        add(addQtyBtn);

        sellQtyBtn = new JButton("Sell Quantity");
        sellQtyBtn.setBounds(190, 400, 140, 25);
        sellQtyBtn.setBackground(new Color(34, 139, 34)); // Button Color
        sellQtyBtn.setForeground(Color.WHITE);
        sellQtyBtn.addActionListener(this);
        add(sellQtyBtn);

        showDetailsBtn = new JButton("Show Details");
        showDetailsBtn.setBounds(350, 400, 120, 25);
        showDetailsBtn.setBackground(new Color(34, 139, 34)); // Button Color
        showDetailsBtn.setForeground(Color.WHITE);
        showDetailsBtn.addActionListener(this);
        add(showDetailsBtn);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(30, 440, 440, 240);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
       
        loadMedicineFromFile();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == createButton) {
                String brand = brandNameField.getText();
                String name = medicineNameField.getText();
                String pharma = pharmaceuticalsField.getText();
                double price = Double.parseDouble(priceField.getText());
                int qty = Integer.parseInt(quantityField.getText());
                
                currentMedicine = new Tablet(brand, name, pharma, price, qty, "General");
                
                if (medicineCount < medicines.length) {
                    medicines[medicineCount++] = currentMedicine;
                    saveMedicineToFile();
                    outputArea.setText("Medicine Created and Saved!\n");
                    currentMedicine.showDetails();
                } else {
                    outputArea.setText("Cannot add more medicines. Storage full!");
                }
            } 
            else if (currentMedicine == null) {
                outputArea.setText("Please create a medicine first!");
            }
            else if (e.getSource() == addQtyBtn) {
                int amt = Integer.parseInt(amountField.getText());
                currentMedicine.setAvailableQuantity(currentMedicine.getAvailableQuantity() + amt);
                outputArea.setText("Quantity Added!\nNew Quantity: " + currentMedicine.getAvailableQuantity());
                saveMedicineToFile();
            } 
            else if (e.getSource() == sellQtyBtn) {
                int amt = Integer.parseInt(amountField.getText());
                if (currentMedicine.getAvailableQuantity() >= amt) {
                    currentMedicine.setAvailableQuantity(currentMedicine.getAvailableQuantity() - amt);
                    outputArea.setText("Quantity Sold!\nNew Quantity: " + currentMedicine.getAvailableQuantity());
                    saveMedicineToFile();
                } else {
                    outputArea.setText("Error: Not enough quantity available!");
                }
            } 
            else if (e.getSource() == showDetailsBtn) {
                StringBuilder details = new StringBuilder();
                details.append("All Medicine Details\n");
                for (int i = 0; i < medicineCount; i++) {
                    Medicine m = medicines[i];
                    details.append("Medicine ").append(i + 1).append(":\n")
                          .append("Brand: ").append(m.getBrandName()).append("\n")
                          .append("Name: ").append(m.getMedicineName()).append("\n")
                          .append("Pharma: ").append(m.getPharmaceuticals()).append("\n")
                          .append("Price: ৳ ").append(m.getPrice()).append("\n")
                          .append("Quantity: ").append(m.getAvailableQuantity()).append("\n\n");
                }
                outputArea.setText(details.toString());
            }
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }

    private void saveMedicineToFile() {
        try {
            FileWriter writer = new FileWriter("medicines.txt");
            for (int i = 0; i < medicineCount; i++) {
                Medicine m = medicines[i];
                writer.write(m.getBrandName() + "," + 
                           m.getMedicineName() + "," + 
                           m.getPharmaceuticals() + "," + 
                           m.getPrice() + "," + 
                           m.getAvailableQuantity() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            outputArea.setText("Error saving medicines: " + e.getMessage());
        }
    }

    private void loadMedicineFromFile() {
        try {
            File file = new File("medicines.txt");
            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            Scanner scanner = new Scanner(file);
            medicineCount = 0;
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length >= 5) {
                    String brand = data[0];
                    String name = data[1];
                    String pharma = data[2];
                    double price = Double.parseDouble(data[3]);
                    int qty = Integer.parseInt(data[4]);
                    
                    medicines[medicineCount++] = new Tablet(brand, name, pharma, price, qty, "General");
                }
            }
            scanner.close();
            
            if (medicineCount > 0) {
                currentMedicine = medicines[medicineCount - 1];
            }
        } catch (IOException e) {
            outputArea.setText("Error loading medicines: " + e.getMessage());
        } catch (Exception e) {
            outputArea.setText("Error parsing medicine data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new MedicineGUI().setVisible(true);
    }
}
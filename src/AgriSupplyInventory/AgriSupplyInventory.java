/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agrisupplyinventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class AgriSupplyInventory {


public class AgriSuppInv extends JFrame {

 private Inventory inventory;
    

    private JTextField nameField;
    private JTextField categoryField;
    private JTextField quantityField;
    private JTextField unitField;
    private JTextField searchField;

    private JTable inventoryTable;
    private DefaultTableModel tableModel;

    public AgriSuppInv() {

        inventory = new Inventory();

        setTitle("Agricultural Supply Inventory");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // HEADER
        JLabel title = new JLabel(
                "AGRICULTURAL SUPPLY INVENTORY",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(35, 100, 55));

        add(title, BorderLayout.NORTH);

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // ADD PRODUCT FORM
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 8, 8));
        formPanel.setBorder(
                BorderFactory.createTitledBorder("Add New Product")
        );

        nameField = new JTextField();
        categoryField = new JTextField();
        quantityField = new JTextField();
        unitField = new JTextField();

        formPanel.add(new JLabel("Product Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryField);

        formPanel.add(new JLabel("Quantity:"));
        formPanel.add(quantityField);

        formPanel.add(new JLabel("Unit:"));
        formPanel.add(unitField);

        JButton addButton = new JButton("Add Product");

        formPanel.add(new JLabel());
        formPanel.add(addButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);

        // INVENTORY TABLE
        String[] columns = {
            "Product ID", "Product Name",
            "Category", "Quantity", "Unit"
        };

        tableModel = new DefaultTableModel(columns, 0);
        inventoryTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(inventoryTable);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(
                BorderFactory.createTitledBorder("Inventory Records")
        );

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // SEARCH PANEL
        JPanel searchPanel = new JPanel(new FlowLayout());

        searchField = new JTextField(20);

        JButton searchButton = new JButton("Search Product");
        JButton displayButton = new JButton("Display All");
        JButton updateButton = new JButton("Update Stock");

        searchPanel.add(new JLabel("Search by Product Name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(displayButton);
        searchPanel.add(updateButton);

        add(searchPanel, BorderLayout.SOUTH);

        // BUTTON ACTIONS
        addButton.addActionListener(e -> addProduct());
        searchButton.addActionListener(e -> searchProduct());
        displayButton.addActionListener(e -> displayInventory());
        updateButton.addActionListener(e -> updateStock());
    }

    // ADD PRODUCT
    private void addProduct() {

        String name = nameField.getText().trim();
        String category = categoryField.getText().trim();
        String quantityText = quantityField.getText().trim();
        String unit = unitField.getText().trim();

        if (name.isEmpty() || category.isEmpty()
                || quantityText.isEmpty() || unit.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please complete all fields.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity < 0) {
                JOptionPane.showMessageDialog(this,
                        "Quantity cannot be negative.");
                return;
            }

            boolean added = inventory.addProduct(
                    name, category, quantity, unit
            );

            if (added) {
                JOptionPane.showMessageDialog(this,
                        "Product added successfully!");

                nameField.setText("");
                categoryField.setText("");
                quantityField.setText("");
                unitField.setText("");

                displayInventory();

            } else {
                JOptionPane.showMessageDialog(this,
                        "Inventory is full!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid whole number for quantity.");
        }
    }

    // SEARCH PRODUCT USING LINEAR SEARCH
    private void searchProduct() {

        String name = searchField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a product name.");
            return;
        }

        Product result = inventory.searchProduct(name);

        if (result != null) {

            tableModel.setRowCount(0);

            tableModel.addRow(new Object[]{
                result.getProductId(),
                result.getProductName(),
                result.getCategory(),
                result.getQuantity(),
                result.getUnit()
            });

            JOptionPane.showMessageDialog(this,
                    "Product found!");

        } else {
            JOptionPane.showMessageDialog(this,
                    "Product not found.");
        }
    }

    // DISPLAY ALL PRODUCTS
    private void displayInventory() {

        tableModel.setRowCount(0);

        Product[] products = inventory.getProducts();

        for (int i = 0; i < inventory.getCount(); i++) {

            tableModel.addRow(new Object[]{
                products[i].getProductId(),
                products[i].getProductName(),
                products[i].getCategory(),
                products[i].getQuantity(),
                products[i].getUnit()
            });
        }
    }
    // Update Stock
    // UPDATE PRODUCT STOCK
    private void updateStock() {

    int selectedRow = inventoryTable.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this,
                "Please select a product from the table.");
        return;
    }

    String productId = tableModel.getValueAt(
            selectedRow, 0
    ).toString();

    String input = JOptionPane.showInputDialog(this,
            "Enter the new stock quantity:");

    if (input == null) {
        return;
    }

    try {
        int newQuantity = Integer.parseInt(input.trim());

        if (newQuantity < 0) {
            JOptionPane.showMessageDialog(this,
                    "Quantity cannot be negative.");
            return;
        }

        boolean updated = inventory.updateStock(
                productId, newQuantity
        );

        if (updated) {
            JOptionPane.showMessageDialog(this,
                    "Stock updated successfully!");

            displayInventory();

        } else {
            JOptionPane.showMessageDialog(this,
                    "Unable to update product stock.");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
                "Please enter a valid whole number.");
    }
}
    // MAIN METHOD
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new AgriSupplyInventory().setVisible(true);
        });
    }
}
}


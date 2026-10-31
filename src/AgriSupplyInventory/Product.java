/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agrisupplyinventory;

/**
 *
 * @author biaap
 */
public class Product {
    private String productId;
    private String productName;
    private String category;
    private int quantity;
    private String unit;

    public Product(String productId, String productName,
        String category, int quantity, String unit) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}


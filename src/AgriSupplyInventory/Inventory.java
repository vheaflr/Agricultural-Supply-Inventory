/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agrisupplyinventory;

/**
 *
 * @author biaap
 */
public class Inventory {

    private Product[] products;
    private int count;

    public Inventory() {
        products = new Product[100];
        count = 0;
    }

    // Add a product with an automatically generated ID
    public boolean addProduct(String name, String category,
                              int quantity, String unit) {

        if (count >= products.length) {
            return false;
        }

        String productId = String.format("P%03d", count + 1);

        products[count] = new Product(
            productId, name, category, quantity, unit
        );

        count++;
        return true;
    
    }

 // Display all products
    public Product[] getProducts() {
        return products;
    }

    public int getCount() {
        return count;
    }
// Update a product's stock using its ID
    public boolean updateStock(String productId, int newQuantity) {

        for (int i = 0; i < count; i++) {

            if (products[i].getProductId().equalsIgnoreCase(productId)) {

                if (newQuantity < 0) {
                    return false;
                }

                products[i].setQuantity(newQuantity);
                return true;
            }
        }

        return false;
    }
}


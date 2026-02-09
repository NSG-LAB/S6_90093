package com.example;

import com.example.dao.ProductDao;
import com.example.entity.Product;
import com.example.util.HibernateUtil;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();

        // 3. Insert multiple Product records into the database
        Product p1 = new Product("Laptop", "Gaming laptop", 1200.0, 10);
        Product p2 = new Product("Phone", "Smartphone", 700.0, 25);
        Product p3 = new Product("Headphones", "Noise cancelling", 150.0, 40);

        productDao.saveProducts(Arrays.asList(p1, p2, p3));

        System.out.println("Inserted products with generated IDs:");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        Long exampleId = p1.getId();

        // 4. Retrieve a product using its id
        System.out.println("\nRetrieving product with id = " + exampleId);
        Product retrieved = productDao.getProductById(exampleId);
        System.out.println("Retrieved: " + retrieved);

        // 5. Update the price or quantity of any selected product
        System.out.println("\nUpdating price and quantity for product id = " + exampleId);
        productDao.updateProductPrice(exampleId, 1300.0);
        productDao.updateProductQuantity(exampleId, 8);
        Product updated = productDao.getProductById(exampleId);
        System.out.println("Updated: " + updated);

        // 6. Delete a product record by id if it is discontinued
        Long discontinuedId = p3.getId();
        System.out.println("\nDeleting discontinued product with id = " + discontinuedId);
        productDao.deleteProductById(discontinuedId);
        Product deletedCheck = productDao.getProductById(discontinuedId);
        System.out.println("After delete, found: " + deletedCheck);

        // Shutdown Hibernate SessionFactory
        HibernateUtil.shutdown();
    }
}

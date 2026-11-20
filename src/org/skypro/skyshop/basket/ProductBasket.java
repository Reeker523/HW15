package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductBasket {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может быть null");
        }

        products.add(product);
    }

    public int getTotalPrice() {
        int total = 0;

        for (Product product : products) {
            total += product.getPrice();
        }

        return total;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println("Итого: " + getTotalPrice() + " руб.");
    }

    public boolean containsProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    public List<Product> removeProduct(String name) {
        List<Product> removedProducts = new ArrayList<>();

        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();

            if (product.getName().equalsIgnoreCase(name)) {
                removedProducts.add(product);
                iterator.remove();
            }
        }

        return removedProducts;
    }

    public void clearBasket() {
        products.clear();
    }
}

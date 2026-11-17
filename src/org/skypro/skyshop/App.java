package org.skypro.skyshop;

import org.skypro.skyshop.basket.basket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {

        Product product1 = new Product("Хлеб", 50);
        Product product2 = new Product("Молоко", 90);
        Product product3 = new Product("Сыр", 250);
        Product product4 = new Product("Яблоки", 120);
        Product product5 = new Product("Сок", 100);
        Product product6 = new Product("Шоколад", 80);

        basket basket = new basket();

        // Добавление товаров
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        // Попытка добавить шестой товар
        basket.addProduct(product6);

        // Печать корзины
        basket.printBasket();

        // Стоимость корзины
        System.out.println("Стоимость: " + basket.getTotalPrice());

        // Поиск существующего товара
        System.out.println("Хлеб: " + basket.containsProduct("Хлеб"));

        // Поиск отсутствующего товара
        System.out.println("Чай: " + basket.containsProduct("Чай"));

        // Очистка корзины
        basket.clearBasket();

        // Печать пустой корзины
        basket.printBasket();

        // Стоимость пустой корзины
        System.out.println("Стоимость: " + basket.getTotalPrice());

        // Поиск в пустой корзине
        System.out.println("Хлеб: " + basket.containsProduct("Хлеб"));
    }
}

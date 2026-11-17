package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.basket.ProductBasket;

public class App {
    public static void main(String[] args) {

        Product product1 = new SimpleProduct("Хлеб", 50);
        Product product2 = new SimpleProduct("Молоко", 90);
        Product product3 = new DiscountedProduct("Сыр", 250, 20);
        Product product4 = new DiscountedProduct("Яблоки", 120, 10);
        Product product5 = new FixPriceProduct("Сок");

        ProductBasket basket = new ProductBasket();

        // Добавление товаров
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        // Попытка добавить шестой товар
        basket.addProduct(new SimpleProduct("Шоколад", 80));

        // Печать корзины
        System.out.println("Содержимое корзины:");
        basket.printBasket();

        // Общая стоимость
        System.out.println("\nСтоимость корзины: "
                + basket.getTotalPrice());

        // Поиск товара
        System.out.println("Поиск хлеба: "
                + basket.containsProduct("Хлеб"));

        System.out.println("Поиск чая: "
                + basket.containsProduct("Чай"));

        // Очистка корзины
        basket.clearBasket();

        // Проверка пустой корзины
        System.out.println("\nПосле очистки:");
        basket.printBasket();

        System.out.println("Стоимость: "
                + basket.getTotalPrice());

        System.out.println("Поиск хлеба: "
                + basket.containsProduct("Хлеб"));
    }
}

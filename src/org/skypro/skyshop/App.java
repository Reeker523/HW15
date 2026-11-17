package org.skypro.skyshop;

import org.skypro.skyshop.product.*;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        // Создание товаров
        Product product1 = new SimpleProduct("Хлеб", 50);
        Product product2 = new SimpleProduct("Молоко", 90);
        Product product3 = new DiscountedProduct("Сыр", 250, 20);
        Product product4 = new DiscountedProduct("Яблоки", 120, 10);
        Product product5 = new FixPriceProduct("Сок");

        // Создание корзины
        ProductBasket basket = new ProductBasket();

        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        System.out.println("Корзина:");
        basket.printBasket();

        // Создание статей
        Article article1 = new Article(
                "Хлеб",
                "Полезная информация о хлебе"
        );

        Article article2 = new Article(
                "Молоко",
                "Виды и польза молока"
        );

        Article article3 = new Article(
                "Сыр",
                "Как правильно выбирать сыр"
        );

        // Создание поискового движка
        SearchEngine searchEngine = new SearchEngine(20);

        // Добавление товаров
        searchEngine.add(product1);
        searchEngine.add(product2);
        searchEngine.add(product3);
        searchEngine.add(product4);
        searchEngine.add(product5);

        // Добавление статей
        searchEngine.add(article1);
        searchEngine.add(article2);
        searchEngine.add(article3);

        // Поиск
        System.out.println("\nПоиск хлеба:");
        Searchable[] result1 = searchEngine.search("Хлеб");
        System.out.println(Arrays.toString(result1));

        System.out.println("\nПоиск молока:");
        Searchable[] result2 = searchEngine.search("Молоко");
        System.out.println(Arrays.toString(result2));

        System.out.println("\nПоиск сыра:");
        Searchable[] result3 = searchEngine.search("Сыр");
        System.out.println(Arrays.toString(result3));

        System.out.println("\nПоиск отсутствующего товара:");
        Searchable[] result4 = searchEngine.search("Телефон");
        System.out.println(Arrays.toString(result4));
    }
}

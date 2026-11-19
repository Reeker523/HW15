package org.skypro.skyshop;

import org.skypro.skyshop.product.*;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        // Создание товаров
        Product product1 = new SimpleProduct("Хлеб", 50);
        Product product2 = new SimpleProduct("Молоко", 90);
        Product product3 = new DiscountedProduct("Сыр", 250, 20);
        Product product4 = new DiscountedProduct("Яблоки", 120, 10);
        Product product5 = new FixPriceProduct("Сок");

        // Проверка неправильных данных
        try {
            new SimpleProduct("", 100);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new SimpleProduct("Шоколад", 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new DiscountedProduct("Торт", 500, 150);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new DiscountedProduct("Печенье", -100, 20);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Создание корзины
        ProductBasket basket = new ProductBasket();

        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        System.out.println("\nКорзина:");
        basket.printBasket();

        // Создание статей
        Article article1 = new Article(
                "Молоко",
                "Молоко полезно. Молоко содержит кальций. Молоко важно."
        );

        Article article2 = new Article(
                "Хлеб",
                "Хлеб является популярным продуктом."
        );

        // Создание поискового движка
        SearchEngine searchEngine = new SearchEngine(20);

        searchEngine.add(product1);
        searchEngine.add(product2);
        searchEngine.add(product3);
        searchEngine.add(product4);
        searchEngine.add(product5);

        searchEngine.add(article1);
        searchEngine.add(article2);

        // Обычный поиск
        System.out.println("\nОбычный поиск:");
        Searchable[] results = searchEngine.search("Молоко");
        System.out.println(Arrays.toString(results));

        // Поиск самого подходящего объекта
        System.out.println("\nСамый подходящий результат:");

        try {
            Searchable best = searchEngine.searchMostSuitable("Молоко");
            System.out.println(best.getStringRepresentation());
            System.out.println(best.getSearchTerm());

        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        // Поиск объекта, которого нет
        System.out.println("\nПоиск отсутствующего объекта:");

        try {
            Searchable best = searchEngine.searchMostSuitable("Телефон");
            System.out.println(best.getStringRepresentation());

        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}

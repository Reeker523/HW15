package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;

public class App {

    public static void main(String[] args) {

        // Создание товаров
        Product milk = new SimpleProduct("Молоко", 100);
        Product bread = new SimpleProduct("Хлеб", 50);
        Product phone = new DiscountedProduct("Телефон", 30000, 10);
        Product headphones = new FixPriceProduct("Наушники");

        // Создание корзины
        ProductBasket basket = new ProductBasket();

        basket.addProduct(milk);
        basket.addProduct(bread);
        basket.addProduct(phone);
        basket.addProduct(headphones);

        System.out.println("Корзина до удаления:");
        basket.printBasket();

        // Удаление существующего товара
        System.out.println("\nУдаление товара «Молоко»:");

        List<Product> removedProducts = basket.removeProduct("Молоко");

        if (removedProducts.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product product : removedProducts) {
                System.out.println("Удалён: " + product);
            }
        }

        System.out.println("\nКорзина после удаления:");
        basket.printBasket();

        // Удаление несуществующего товара
        System.out.println("\nУдаление товара «Компьютер»:");

        List<Product> notRemoved = basket.removeProduct("Компьютер");

        if (notRemoved.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product product : notRemoved) {
                System.out.println(product);
            }
        }

        System.out.println("\nКорзина после повторного удаления:");
        basket.printBasket();

        // Создание статей
        Article article1 = new Article(
                "Молоко",
                "Полезный молочный продукт"
        );

        Article article2 = new Article(
                "Хлеб",
                "Свежий хлеб из пекарни"
        );

        Article article3 = new Article(
                "Телефоны",
                "Обзор современных телефонов"
        );

        // Создание поисковой системы
        SearchEngine searchEngine = new SearchEngine();

        searchEngine.add(milk);
        searchEngine.add(bread);
        searchEngine.add(phone);
        searchEngine.add(headphones);

        searchEngine.add(article1);
        searchEngine.add(article2);
        searchEngine.add(article3);

        // Поиск всех результатов
        System.out.println("\nРезультаты поиска по запросу «Молоко»:");

        List<Searchable> results = searchEngine.search("Молоко");

        if (results.isEmpty()) {
            System.out.println("Ничего не найдено");
        } else {
            for (Searchable result : results) {
                System.out.println(result.getStringRepresentation());
            }
        }

        // Поиск наиболее подходящего результата
        System.out.println("\nСамый подходящий результат:");

        try {
            Searchable bestResult =
                    searchEngine.searchMostSuitable("Хлеб");

            System.out.println(bestResult.getStringRepresentation());

        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}

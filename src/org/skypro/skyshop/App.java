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
import java.util.Map;

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

        // Добавляем ещё один товар с таким же именем
        basket.addProduct(new SimpleProduct("Молоко", 120));

        System.out.println("Корзина:");
        basket.printBasket();

        // Проверка наличия товара
        System.out.println("\nЕсть ли молоко: "
                + basket.containsProduct("Молоко"));

        // Удаление товара
        System.out.println("\nУдаление молока:");

        List<Product> removedProducts =
                basket.removeProduct("Молоко");

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
        System.out.println("\nУдаление компьютера:");

        List<Product> notRemoved =
                basket.removeProduct("Компьютер");

        if (notRemoved.isEmpty()) {
            System.out.println("Список пуст");
        }

        // Создание статей
        Article article1 = new Article(
                "Молочные продукты",
                "Информация о молоке и других продуктах"
        );

        Article article2 = new Article(
                "Хлеб",
                "Информация о хлебе"
        );

        Article article3 = new Article(
                "Телефоны",
                "Обзор современных телефонов"
        );

        // Создание поискового движка
        SearchEngine searchEngine = new SearchEngine();

        searchEngine.add(milk);
        searchEngine.add(bread);
        searchEngine.add(phone);
        searchEngine.add(headphones);

        searchEngine.add(article1);
        searchEngine.add(article2);
        searchEngine.add(article3);

        // Поиск
        System.out.println("\nРезультаты поиска по запросу «Молоко»:");

        Map<String, Searchable> results =
                searchEngine.search("Молоко");

        if (results.isEmpty()) {
            System.out.println("Ничего не найдено");
        } else {
            for (Searchable result : results.values()) {
                System.out.println(
                        result.getStringRepresentation()
                );
            }
        }

        // Самый подходящий результат
        System.out.println("\nСамый подходящий результат:");

        try {
            Searchable bestResult =
                    searchEngine.searchMostSuitable("Хлеб");

            System.out.println(
                    bestResult.getStringRepresentation()
            );

        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}

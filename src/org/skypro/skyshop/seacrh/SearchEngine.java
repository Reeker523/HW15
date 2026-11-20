package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> searchableItems = new ArrayList<>();

    public void add(Searchable item) {
        if (item == null) {
            throw new IllegalArgumentException("Элемент не может быть null");
        }

        searchableItems.add(item);
    }

    public List<Searchable> search(String searchTerm) {
        List<Searchable> results = new ArrayList<>();

        if (searchTerm == null || searchTerm.isBlank()) {
            return results;
        }

        for (Searchable item : searchableItems) {
            if (item.getSearchTerm().toLowerCase()
                    .contains(searchTerm.toLowerCase())) {
                results.add(item);
            }
        }

        return results;
    }

    public Searchable searchMostSuitable(String searchTerm)
            throws BestResultNotFound {

        Searchable bestResult = null;
        int maxMatches = 0;

        if (searchTerm == null || searchTerm.isBlank()) {
            throw new BestResultNotFound("Поисковый запрос пуст");
        }

        for (Searchable item : searchableItems) {
            String text = item.getSearchTerm().toLowerCase();
            String query = searchTerm.toLowerCase();

            int matches = 0;
            int index = 0;

            while ((index = text.indexOf(query, index)) != -1) {
                matches++;
                index += query.length();
            }

            if (matches > maxMatches) {
                maxMatches = matches;
                bestResult = item;
            }
        }

        if (bestResult == null) {
            throw new BestResultNotFound("Подходящий результат не найден");
        }

        return bestResult;
    }
}

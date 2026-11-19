package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] searchableItems;

    public SearchEngine(int size) {
        searchableItems = new Searchable[size];
    }

    public void add(Searchable item) {
        for (int i = 0; i < searchableItems.length; i++) {
            if (searchableItems[i] == null) {
                searchableItems[i] = item;
                return;
            }
        }
    }

    public Searchable[] search(String searchTerm) {
        Searchable[] results = new Searchable[5];
        int count = 0;

        for (Searchable item : searchableItems) {
            if (item != null &&
                    item.getSearchTerm().contains(searchTerm)) {

                results[count] = item;
                count++;

                if (count == 5) {
                    break;
                }
            }
        }

        return results;
    }

    public Searchable searchMostSuitable(String search)
            throws BestResultNotFound {

        if (search == null || search.isBlank()) {
            throw new BestResultNotFound(
                    "Некорректный поисковый запрос: " + search
            );
        }

        Searchable bestResult = null;
        int maxCount = 0;

        for (Searchable item : searchableItems) {
            if (item == null || item.getSearchTerm() == null) {
                continue;
            }

            String text = item.getSearchTerm();
            int count = 0;
            int index = 0;

            int foundIndex = text.indexOf(search, index);

            while (foundIndex != -1) {
                count++;
                index = foundIndex + search.length();
                foundIndex = text.indexOf(search, index);
            }

            if (count > maxCount) {
                maxCount = count;
                bestResult = item;
            }
        }

        if (bestResult == null) {
            throw new BestResultNotFound(
                    "Не найден подходящий объект для запроса: " + search
            );
        }

        return bestResult;
    }
}

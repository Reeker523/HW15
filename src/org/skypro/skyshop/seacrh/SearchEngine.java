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
}

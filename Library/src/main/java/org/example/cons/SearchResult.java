package org.example.cons;

import java.util.LinkedHashMap;
import java.util.Map;

public class SearchResult {
    private Map<String, String> _searchResults;

    public SearchResult() {
        _searchResults = new LinkedHashMap<>();
    }

    public void put(String key, String status) {
        _searchResults.put(key, status);
    }
}

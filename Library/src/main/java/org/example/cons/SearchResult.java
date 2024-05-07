package org.example.cons;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchResult {
    private List<SearchRecord> _searchResults;

    public SearchResult() {
        _searchResults = new ArrayList<>();
    }

    public void add(String identifier, String title, String status) {
        _searchResults.add(new SearchRecord(identifier, title, status));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (SearchRecord r : _searchResults) {
            sb.append("\n").append(r);
        }
        return sb.toString();
    }
}

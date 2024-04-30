package org.example.cons;

import org.example.lib.Category;

import java.util.List;

public class SearchRequest {
    private final List<String> _mustContains;
    private final Category _sectionCategory;
    private final List<Category> _themeCategories;

    public SearchRequest(List<String> mustContains, Category sectionCategory, List<Category> themeCategories) {
        _mustContains = mustContains;
        _sectionCategory = sectionCategory;
        _themeCategories = themeCategories;
    }

    public List<String> getMustContains() {
        return _mustContains;
    }

    public Category getSectionCategory() {
        return _sectionCategory;
    }

    public List<Category> getThemeCategories() {
        return _themeCategories;
    }
}

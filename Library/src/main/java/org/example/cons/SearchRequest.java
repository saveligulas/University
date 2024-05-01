package org.example.cons;

import org.example.lib.Category;
import org.example.lib.Section;

import java.util.List;

public class SearchRequest {
    private final List<String> _mustContains;
    private final Section _section;
    private final List<Category> _themeCategories;

    public SearchRequest(List<String> mustContains, Section section, List<Category> themeCategories) {
        _mustContains = mustContains;
        _section = section;
        _themeCategories = themeCategories;
    }

    public List<String> getMustContains() {
        return _mustContains;
    }

    public Section getSectionCategory() {
        return _section;
    }

    public List<Category> getThemeCategories() {
        return _themeCategories;
    }
}

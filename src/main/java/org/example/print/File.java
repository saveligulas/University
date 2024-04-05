package org.example.print;

import org.example.ub1.my.MyCollection;

public record File(MyCollection<Page> pages, PageType pageType) {
}

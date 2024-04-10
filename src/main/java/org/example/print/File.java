package org.example.print;

import org.example.coll.MyCollection;

public record File(MyCollection<Page> pages, PageType pageType) {
}

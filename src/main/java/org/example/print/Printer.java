package org.example.print;

import org.example.coll.MyCollection;
import org.example.coll.tuple.Tuple;

public class Printer {
    private int _printedPages;
    private final MyCollection<Tuple<PageType, Integer>> _paperStock;

    Printer() {
        _printedPages = 0;
        _paperStock = new MyCollection<>();
        for (PageType pageType : PageType.values()) {
            Tuple<PageType, Integer> typeStock = new Tuple<>();
            typeStock.setFirst(pageType);
            typeStock.setSecond(100);
            _paperStock.add(typeStock);
        }
    }

    public void print(File file) {
        _printedPages += file.pages().size();
        Tuple<PageType, Integer> search = new Tuple<>();
        search.setFirst(file.pageType());
        search = _paperStock.get(search);
        search.setSecond(search.getSecond() - file.pages().size());

        //TODO: implement

    }

    public int getPrintedPages() {
        return _printedPages;
    }

    public int getStock(PageType pageType) {
        Tuple<PageType, Integer> search = new Tuple<>();
        search.setFirst(pageType);
        return _paperStock.get(search).getSecond();
    }
}

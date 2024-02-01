package org.lesson.projectwork.comparators;

import org.lesson.projectwork.model.Prodotto;

import java.util.Comparator;

public class ProdottoAcquistoComparator implements Comparator<Prodotto> {
    @Override
    public int compare(Prodotto o1, Prodotto o2) {
        return Integer.compare(o1.getAcquisto().size(), o2.getAcquisto().size());
    }


}

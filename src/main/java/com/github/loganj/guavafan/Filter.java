package com.github.loganj.guavafan;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import static com.github.loganj.guavafan.Pimps.pimpIterable;

final class Filter<T> extends AbstractPipeline<T,T> {

    final private Predicate<? super T> predicate;

    public Filter(Predicate<? super T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public PimpedIterable<T> apply(Iterable<T> tIterable) {
        return pimpIterable(Iterables.filter(tIterable, predicate));
    }
}

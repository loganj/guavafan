package com.github.loganj.guavafan;

import com.google.common.collect.Iterables;

import static com.github.loganj.guavafan.Pimps.pimpIterable;

final class Concat<T> extends AbstractPipeline<T,T> {

    final private Iterable<? extends T> right;

    public Concat(Iterable<? extends T> right) {
        this.right = right;
    }

    @Override
    public PimpedIterable<T> apply(Iterable<T> tIterable) {
        return pimpIterable(Iterables.concat(tIterable, right));
    }
}

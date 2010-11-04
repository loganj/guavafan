package com.github.loganj.guavafan;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.Collections;

import static com.github.loganj.guavafan.Pimps.pimpIterable;

abstract class AbstractPimpedIterable<T> implements PimpedIterable<T> {
    

    @Override
    public <D> PimpedIterable<D> map(Function<? super T, ? extends D> function) {
        return pimpIterable(Iterables.transform(this, function));
    }

    @Override
    public PimpedIterable<T> filter(Predicate<? super T> predicate) {
        return pimpIterable(Iterables.filter(this, predicate));
    }

    @Override
    public PimpedIterable<T> concat(Iterable<? extends T> b) {
        return pimpIterable(Iterables.concat(this, b));
    }

    @Override
    public <D> PimpedIterable<D> through(Function<? super Iterable<T>, ? extends Iterable<D>> pipeline) {
        return pimpIterable(pipeline.apply(this));
    }

    @Override
    public <S> PimpedIterable<S> flatMap(Function<? super T, ? extends PimpedIterable<S>> f) {
        for ( T t : this ) {
            return f.apply(t);
        }
        return pimpIterable(Collections.<S>emptySet());
    }

}

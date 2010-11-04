package com.github.loganj.guavafan;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public interface PimpedIterable<T> extends Iterable<T> {

    <D> PimpedIterable<D> map(Function<? super T, ? extends D> function);

    PimpedIterable<T> filter(Predicate<? super T> predicate);

    PimpedIterable<T> concat(java.lang.Iterable<? extends T> b);

    <D> PimpedIterable<D> through(Function<? super Iterable<T>, ? extends Iterable<D>> pipeline);

    <S> PimpedIterable<S> flatMap(Function<? super T, ? extends Iterable<S>> f);

}

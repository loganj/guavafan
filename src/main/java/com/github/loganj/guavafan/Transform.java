package com.github.loganj.guavafan;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import static com.github.loganj.guavafan.Pimps.pimpIterable;

final class Transform<F,T> extends AbstractPipeline<F,T> {

    final private Function<? super F, ? extends T> transformFunction;

    Transform(Function<? super F,? extends T> transformFunction) {
        this.transformFunction = transformFunction;
    }

    @Override
    public PimpedIterable<T> apply(Iterable<F> from) {
        return pimpIterable(Iterables.transform(from, transformFunction));
    }
}

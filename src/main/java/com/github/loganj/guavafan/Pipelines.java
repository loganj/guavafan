package com.github.loganj.guavafan;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import static com.github.loganj.guavafan.Pimps.pimpIterable;

public abstract class Pipelines {
    private Pipelines() {}

    public static <T> Pipeline<T,T> concat(Iterable<T> right) {
        return new Concat<T>(right);
    }

    public static <F,T> Pipeline<F,T> map(Function<? super F, ? extends T> mapFunction) {
        return new Transform<F,T>(mapFunction);
    }

    public static <T> Pipeline<T,T> filter(Predicate<? super T> predicate) {
        return new Filter<T>(predicate);
    }

    static <A,B> Pipeline<A,B> pipelineFrom(final Function<? super Iterable<A>, ? extends Iterable<B>> f) {
        return new AbstractPipeline<A,B>() {
            @Override
            public PimpedIterable<B> apply(Iterable<A> a) {
                return pimpIterable(f.apply(a));
            }
        };
    }
}

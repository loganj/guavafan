package com.github.loganj.guavafan;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;

import java.util.Iterator;

public abstract class Pimps {
    private Pimps() {}

    public static <T> PimpedPredicate<T> pimpPredicate(Predicate<T> p) {
        if ( p instanceof PimpedPredicate ) {
            return (PimpedPredicate<T>)p;
        }
        return new BasicPimpedPredicate<T>(p);
    }

    public static <F,T> PimpedFunction<F,T> pimpFunction(Function<F,T> f) {
        if ( f instanceof PimpedFunction ) {
            return (PimpedFunction<F,T>)f;
        }
        return new BasicPimpedFunction<F,T>(f);
    }

    public static <T> PimpedIterable<T> pimpIterable(Iterable<T> iterable) {
        if ( iterable instanceof PimpedIterable ) {
            return (PimpedIterable<T>)iterable;
        }
        return new BasicPimpedIterable<T>(iterable);
    }

    final private static class BasicPimpedIterable<T> extends AbstractPimpedIterable<T> {

        final private Iterable<T> wrapped;

        BasicPimpedIterable(Iterable<T> wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public Iterator<T> iterator() {
            return wrapped.iterator();
        }
    }

    final private static class BasicPimpedFunction<F,T> implements PimpedFunction<F,T> {
   
        final private Function<? super F, ? extends T> wrapped;

        BasicPimpedFunction(Function<? super F, ? extends T> wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public T apply(F f) {
            return wrapped.apply(f);
        }

        @Override
        public <B> PimpedFunction<F, B> andThen(Function<? super T, B> f) {
            return pimpFunction(Functions.compose(f, this));
        }
    }

    final private static class BasicPimpedPredicate<T> extends AbstractPimpedPredicate<T> {
        final private Predicate<T> p;
        BasicPimpedPredicate(Predicate<T> p) {
            this.p = p;
        }

        @Override
        public boolean apply(T t) {
            return p.apply(t);
        }
    }
}

package com.github.loganj.guavafan;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Java implementation of the Option monad.  But with pimping.
 *
 * thanks largely to http://eng.kaching.com/2010/05/better-option-for-java.html
 */

public abstract class Option<T> extends AbstractPimpedIterable<T> implements Serializable {

    private static abstract class ImmutableIterator<T>
     implements Iterator<T>, Serializable {
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    final private static class NothingIterator<T> extends ImmutableIterator<T> {
        public boolean hasNext() { return false; }

        public T next() {
            throw new NoSuchElementException();
        }
    }

    private static class Nothing<T> extends Option<T> {
        public Iterator<T> iterator() { return new NothingIterator<T>(); }
    }

    public T orNull() {
        for ( T t : this ) {
            return t;
        }
        return null;
    }

    public T or(T ifNothing) {
        for ( T t : this ) {
            return t;
        }
        return ifNothing;
    }

    private static Nothing<Object> nothing = new Nothing<Object>();

    private static class Some<T> extends Option<T> {
        private T value;

        Some(T value) {
            this.value = value;
        }

        public boolean equals(Object o) {
            return o instanceof Some<?> &&
              (((Some<?>) o).value).equals(value);
        }

        public int hashCode() {
            return value.hashCode();
        }

        public Iterator<T> iterator()
        {
            return new SomeIterator();
        }

        private class SomeIterator extends ImmutableIterator<T> {
            boolean hasNext = true;

            public boolean hasNext() {
                return hasNext;
            }

            public T next()
            {
                if (!hasNext) throw new NoSuchElementException();
                hasNext = false;
                return value;
            }
        }
    }

    public static <T> Option<T> maybe(T value) {
        if ( value != null ) {
            return some(value);
        }
        return nothing();
    }

    public static <T> Option<T> some(Option<T> value) {
        return value == null ? Option.<T> nothing() : value;
    }

    @SuppressWarnings("unchecked")
    public static <T> Option<T> some(T value) {
        return value == null              ? Option.<T> nothing() :
               value instanceof Option<?> ? (Option<T>) value    :
                                             new Some(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Option<T> nothing() {
        return (Nothing<T>) nothing;
    }
    
}

package com.github.loganj.guavafan;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import static com.github.loganj.guavafan.Pimps.pimpPredicate;

abstract class AbstractPimpedPredicate<T> implements PimpedPredicate<T> {

    @Override
    public PimpedPredicate<T> or(Predicate<T> p) {
        return pimpPredicate(Predicates.or(this, p));
    }

    @Override
    public PimpedPredicate<T> not() {
        return pimpPredicate(Predicates.not(this));
    }

    @Override
    public PimpedPredicate<T> and(Predicate<T> p) {
        return pimpPredicate(Predicates.and(this, p));
    }
}

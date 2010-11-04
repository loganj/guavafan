package com.github.loganj.guavafan;

import com.google.common.base.Predicate;

public interface PimpedPredicate<T> extends Predicate<T> {

    PimpedPredicate<T> and(Predicate<T> p);

    PimpedPredicate<T> or(Predicate<T> p);

    PimpedPredicate<T> not();
}

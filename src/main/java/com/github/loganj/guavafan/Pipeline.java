package com.github.loganj.guavafan;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public interface Pipeline<F,T> extends PimpedFunction<Iterable<F>, PimpedIterable<T>> {

    Pipeline<F,F> concat(Iterable<F> right);

    <C> Pipeline<F,C> transform(Function<? super T, ? extends C> function);

    Pipeline<F,F> filter(Predicate<? super F> predicate);

}

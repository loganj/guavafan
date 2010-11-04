package com.github.loganj.guavafan;

import com.google.common.base.Function;

public interface PimpedFunction<F,T> extends Function<F,T> {

    <B> PimpedFunction<F,B> andThen(Function<? super T, B> f);

}

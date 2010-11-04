package com.github.loganj.guavafan;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;

import static com.github.loganj.guavafan.Pimps.pimpFunction;
import static com.github.loganj.guavafan.Pipelines.pipelineFrom;
import static com.google.common.base.Functions.compose;

abstract class AbstractPipeline<F,T> implements Pipeline<F,T> {

    @Override
    public Pipeline<F,F> concat(Iterable<F> right) {
        return Pipelines.concat(right);
    }

    @Override
    public <C> Pipeline<F, C> transform(Function<? super T, ? extends C> transformFunction) {
        Pipeline<T,C> tp = Pipelines.map(transformFunction);
        Function<Iterable<F>,PimpedIterable<C>> f = compose(tp, this);
        return pipelineFrom(f);
    }

    @Override
    public Pipeline<F, F> filter(Predicate<? super F> predicate) {
        return Pipelines.filter(predicate);
    }
    
    @Override
    public <B> PimpedFunction<Iterable<F>, B> andThen(Function<? super PimpedIterable<T>, B> f) {
        return pimpFunction(Functions.compose(f, this));
    }

}

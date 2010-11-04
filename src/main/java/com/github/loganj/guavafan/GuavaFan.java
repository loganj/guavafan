package com.github.loganj.guavafan;

import com.google.common.base.*;
import com.google.common.collect.Lists;

import java.util.List;

import static com.github.loganj.guavafan.Pimps.pimpFunction;
import static com.github.loganj.guavafan.Pipelines.pipelineFrom;
import static java.lang.Double.parseDouble;

final class GuavaFan {
    private GuavaFan() {}

    public static void main(String[] args) {
        List<Integer> orig = Lists.newArrayList(1, 2, null, 3, -4, null, 5, null);
        Function<String,Double> parseDouble = new Function<String,Double>() {
            @Override
            public Double apply(String s) {
                return parseDouble(s);
            }
        };
        Function<Double,Integer> intValue = new Function<Double,Integer>() {
            @Override
            public Integer apply(Double s) {
                return s.intValue();
            }
        };

        Function<String,Integer> intValueOfString = pimpFunction(parseDouble).andThen(intValue);

        Function<? super Integer,String> iToS = Functions.toStringFunction();

        Pipeline<Integer, Double> toDoubleViaString =
                Pipelines.<Integer>filter(Predicates.notNull())
                .transform(Functions.toStringFunction())
                .transform(parseDouble);

        Iterable<Double> doubles = toDoubleViaString.apply(orig);

        Pipeline<Double,Integer> toIntegerViaString =
               Pipelines.<Double>filter(Predicates.notNull())
               .transform(intValue);

        PimpedIterable<Integer> backToIntegers = toIntegerViaString.apply(doubles);

        Pipeline<Integer,Integer> easier = pipelineFrom(toDoubleViaString.andThen(toIntegerViaString));

        System.out.println(Joiner.on(", ").join(easier.apply(orig)));


        Option<String> hellos = Option.some("hello");

    }
}

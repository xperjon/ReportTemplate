package com.blinfosoft.report.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 *
 * @author jel
 */
public class NullSummingCollector implements Collector<Optional<Double>, List<Double>, Optional<Double>> {

    public static Collector<Optional<Double>, List<Double>, Optional<Double>> sum() {
        return new NullSummingCollector();
    }

    @Override
    public Supplier<List<Double>> supplier() {
        return () -> new ArrayList<>();
    }

    @Override
    public BiConsumer<List<Double>, Optional<Double>> accumulator() {
        return (acc, value) -> {
            if (value.isPresent()) {
                acc.add(value.get());
            }
        };
    }

    @Override
    public BinaryOperator<List<Double>> combiner() {
        return null;
    }

    @Override
    public Function<List<Double>, Optional<Double>> finisher() {
        return acc -> acc.isEmpty() ? Optional.empty() : Optional.of(acc.stream().mapToDouble(v -> v).sum());
    }

    @Override
    public Set<Collector.Characteristics> characteristics() {
        return Collections.singleton(Collector.Characteristics.CONCURRENT);
    }
}

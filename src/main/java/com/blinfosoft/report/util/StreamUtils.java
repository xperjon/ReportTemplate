package com.blinfosoft.report.util;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author jel
 */
public class StreamUtils {

    public static IntStream reverseIntRange(Integer from, Integer to) {
        return IntStream.range(from, to).map(i -> to - i + from - 1);
    }
    
    public static <T> Stream<T> toStream(T t) {
        Optional<T> optional = Optional.ofNullable(t);
        return optional.map(value -> Stream.of(value)).orElseGet(() -> Stream.empty());
    }
}

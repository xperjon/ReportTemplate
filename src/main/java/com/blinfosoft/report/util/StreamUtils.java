package com.blinfosoft.report.util;

import java.util.stream.IntStream;

/**
 *
 * @author jel
 */
public class StreamUtils {

    public static IntStream reverseIntRange(Integer from, Integer to) {
        return IntStream.range(from, to).map(i -> to - i + from - 1);
    }
}

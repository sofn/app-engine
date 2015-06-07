package com.junesix.common.utils.collection;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-6-7 00:43.
 */
public class GlobalCollectionUtils {
    public static Joiner.MapJoiner mapJoiner = Joiner.on(',').withKeyValueSeparator("=");
    public static Joiner argsJoiner = Joiner.on('_');
    public static Joiner argsJoiner2 = Joiner.on(',');


    public static final Function<String, Long> stringToLongFunction = new Function<String, Long>() {
        @Override
        public Long apply(String input) {
            return NumberUtils.toLong(input);
        }
    };

    public static final Function<String, Integer> stringToIntFunction = new Function<String, Integer>() {
        @Override
        public Integer apply(String input) {
            return NumberUtils.toInt(input);
        }
    };


    public static final Function<Long, String> longToStringFunction = new Function<Long, String>() {
        @Override
        public String apply(Long input) {
            return input != null ? input.toString() : "";
        }
    };

    public static final Function<Integer, String> intToStringFunction = new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return input != null ? input.toString() : "";
        }
    };
}

package com.tms.api.consts;

import com.tms.api.AppProperties;

public class Const {
    public static final String REDIS_PREFIX_GLOBAL = "global" + AppProperties.getPropertyValue("config.redis-suffix");

    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
}

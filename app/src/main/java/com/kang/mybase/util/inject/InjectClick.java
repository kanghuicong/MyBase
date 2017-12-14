package com.kang.mybase.util.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by KangHuiCong on 2017/12/4.
 * E-Mail is 515849594@qq.com
 */

@Target(METHOD)
@Retention(RUNTIME)
public @interface InjectClick {
    int[] value();
}


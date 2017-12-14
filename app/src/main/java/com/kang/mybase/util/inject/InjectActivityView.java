package com.kang.mybase.util.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by KangHuiCong on 2017/12/4.
 * E-Mail is 515849594@qq.com
 */

@Target(TYPE)
@Retention(RUNTIME)
public @interface InjectActivityView {
    int value();
}

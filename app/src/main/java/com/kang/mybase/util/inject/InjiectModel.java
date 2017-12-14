package com.kang.mybase.util.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by KangHuiCong on 2017/12/14.
 * E-Mail is 515849594@qq.com
 */

@Retention(CLASS)
@Target(FIELD)
public @interface InjiectModel {
}

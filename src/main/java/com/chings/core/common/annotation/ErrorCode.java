package com.chings.core.common.annotation;

import com.chings.core.common.enums.ErrorEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorCode {

    ErrorEnum value() default ErrorEnum.UNKOWN_EXCEPTION;

}

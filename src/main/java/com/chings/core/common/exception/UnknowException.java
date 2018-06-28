package com.chings.core.common.exception;

import com.chings.core.common.annotation.ErrorCode;
import com.chings.core.common.enums.ErrorEnum;

/**
 * 未知异常
 */
@ErrorCode(ErrorEnum.UNKOWN_EXCEPTION)
public class UnknowException extends FastException {

}

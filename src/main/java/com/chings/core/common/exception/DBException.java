package com.chings.core.common.exception;

import com.chings.core.common.annotation.ErrorCode;
import com.chings.core.common.enums.ErrorEnum;

/**
 * 数据库异常
 */
@ErrorCode(ErrorEnum.DB_EXCEPTION)
public class DBException extends FastException {
}

package com.chings.core.common.exception;

import com.chings.core.common.enums.ErrorEnum;
import com.chings.core.common.annotation.ErrorCode;

/**
 * @Author Administrator
 * @Date 2018/6/22
 */
@ErrorCode(ErrorEnum.NOT_FOUND_EXCEPTION)
public class NotFoundException extends FastException {
}

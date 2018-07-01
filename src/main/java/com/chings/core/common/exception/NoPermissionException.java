package com.chings.core.common.exception;

import com.chings.core.common.annotation.ErrorCode;
import com.chings.core.common.enums.ErrorEnum;

/**
 * @Author Administrator
 * @Date 2018/6/22
 */
@ErrorCode(ErrorEnum.NO_PERMMISION_EXCEPTION)
public class NoPermissionException extends FastException {
}

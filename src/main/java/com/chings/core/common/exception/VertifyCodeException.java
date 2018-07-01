package com.chings.core.common.exception;

/**
 * @Author Administrator
 * @Date 2018/7/1
 */

import com.chings.core.common.annotation.ErrorCode;
import com.chings.core.common.enums.ErrorEnum;

@ErrorCode(ErrorEnum.VERTIFY_CODE_EXCEPTION)
public class VertifyCodeException extends LoginVertifyException{
}

package com.chings.core.common.exception;

/**
 * @Author Administrator
 * @Date 2018/7/1
 */

import com.chings.core.common.annotation.ErrorCode;
import com.chings.core.common.enums.ErrorEnum;

@ErrorCode(ErrorEnum.ACCOUNT_VERTIFY_FAILED_EXCEPTION)
public class AccountVertifyException extends LoginVertifyException{
}

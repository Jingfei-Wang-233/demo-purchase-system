package com.tw.capability.gtb.demopurchasesystem.support.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiError {
    Integer code;
    String error;
    String message;
}

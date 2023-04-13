package com.tms.api.consts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class EnumType {

    @Getter
    @AllArgsConstructor
    public enum DbStatusResp {
        SUCCESS(0),
        FAIL(1),
        ;

        private final int status;
    }

}

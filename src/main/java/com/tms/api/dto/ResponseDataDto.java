package com.tms.api.dto;

import com.tms.api.dto.information.FFMInfo;
import com.tms.api.dto.information.LMInfo;
import com.tms.api.dto.information.WarehouseInfo;
import lombok.*;

import java.util.Set;

@Data
public class ResponseDataDto {

    private Integer locationId;
    private String locationName;
    Set<FFMInfo> ffms;
    Set<LMInfo> lms;
    Set<WarehouseInfo> wh;

}

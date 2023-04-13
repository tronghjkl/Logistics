package com.tms.api.dto.information;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseInfo {
    private Integer whId;
    private String name;
    private String shortName;

}

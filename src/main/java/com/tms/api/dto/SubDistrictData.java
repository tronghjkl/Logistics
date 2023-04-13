package com.tms.api.dto;

import com.tms.api.dto.information.*;
import lombok.*;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;


@NoArgsConstructor
@Data
public class SubDistrictData {
    private SubDistrictInfo subDistrictInfo;
    private FFMInfo ffmInfo;
    private LMInfo lmInfo;

    private WarehouseInfo wh;

    public SubDistrictData(Integer subdtId, String subdtName, Integer ffmId, String ffmName, String ffmShortName,
                           Integer lmId, String lmName, String lmShortName, Integer whId, String whName, String whShortName) {
        this.subDistrictInfo = new SubDistrictInfo();
        this.ffmInfo = new FFMInfo();
        this.lmInfo = new LMInfo();
        this.wh = new WarehouseInfo();

        this.subDistrictInfo.setSubdtId(subdtId);
        this.subDistrictInfo.setName(subdtName);
        this.ffmInfo.setFfmId(ffmId);
        this.ffmInfo.setName(ffmName);
        this.ffmInfo.setShortName(ffmShortName);
        this.lmInfo.setLmId(lmId);
        this.lmInfo.setName(lmName);
        this.lmInfo.setShortName(lmShortName);
        this.wh.setWhId(whId);
        this.wh.setName(whName);
        this.wh.setShortName(whShortName);
    }

    public SubDistrictData(SubDistrictInfo subDistrictInfo,  FFMInfo ffmInfo, LMInfo lmInfo, WarehouseInfo wh) {
        this.subDistrictInfo = subDistrictInfo;
        this.ffmInfo = ffmInfo;
        this.lmInfo = lmInfo;
        this.wh = wh;
    }
}

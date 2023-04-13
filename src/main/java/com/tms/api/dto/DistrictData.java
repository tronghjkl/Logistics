package com.tms.api.dto;

import com.tms.api.dto.information.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class DistrictData {
    private DistrictInfo districtInfo;
    private FFMInfo ffmInfo;
    private LMInfo lmInfo;
    private WarehouseInfo wh;



    public DistrictData(Integer dtId, String dtName,  Integer ffmId, String ffmName,
                        String ffmShortName, Integer lmId, String lmName, String lmShortName, Integer whId, String whName, String whShortName) {
        this.districtInfo = new DistrictInfo();
        this.ffmInfo = new FFMInfo();
        this.lmInfo = new LMInfo();
        this.wh = new WarehouseInfo();

        this.districtInfo.setDtId(dtId);
        this.districtInfo.setName(dtName);
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

    public DistrictData(DistrictInfo districtInfo,  FFMInfo ffmInfo, LMInfo lmInfo, WarehouseInfo wh) {
        this.districtInfo = districtInfo;
        this.ffmInfo = ffmInfo;
        this.lmInfo = lmInfo;
        this.wh = wh;
    }

}

package com.tms.api.dto;

import com.tms.api.dto.information.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.SqlResultSetMapping;

@Getter
@Setter
@NoArgsConstructor
public class ProvinceData {
        private ProvinceInfo provinceInfo;
        private FFMInfo ffmInfo;
        private LMInfo lmInfo;
        private WarehouseInfo wh;

    public ProvinceData(Integer prvId, String prvName, Integer ffmId, String ffmName, String ffmShortName,
                        Integer lmId, String lmName, String lmShortName, Integer whId, String whName, String whShortName) {
        this.provinceInfo = new ProvinceInfo();
        this.ffmInfo = new FFMInfo();
        this.lmInfo = new LMInfo();
        this.wh = new WarehouseInfo();

        this.provinceInfo.setPrvId(prvId);
        this.provinceInfo.setName(prvName);
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

    public ProvinceData(ProvinceInfo provinceInfo,  FFMInfo ffmInfo, LMInfo lmInfo, WarehouseInfo wh) {
        this.provinceInfo = provinceInfo;
        this.ffmInfo = ffmInfo;
        this.lmInfo = lmInfo;
        this.wh = wh;
    }

}

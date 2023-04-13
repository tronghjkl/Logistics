package com.tms.api.convert;

import com.tms.api.dto.ResponseDataDto;
import com.tms.api.dto.SubDistrictData;
import com.tms.api.dto.information.FFMInfo;
import com.tms.api.dto.information.LMInfo;
import com.tms.api.dto.information.WarehouseInfo;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ConvertSubdistrict {

    public static Set<ResponseDataDto> convertData(List<SubDistrictData> subDistrictDto) {
        Set<ResponseDataDto> result = new HashSet<>();
        for (SubDistrictData rawData : subDistrictDto) {
            if (rawData.getSubDistrictInfo() == null || rawData.getSubDistrictInfo().getSubdtId() == null) {
                continue;
            }
            ResponseDataDto dataOutput3 = new ResponseDataDto();
            dataOutput3.setLocationId(rawData.getSubDistrictInfo().getSubdtId());
            dataOutput3.setLocationName(rawData.getSubDistrictInfo().getName());
            Set<FFMInfo> listFfm = new HashSet<>();
            subDistrictDto.forEach(dto1 -> {
                if (dto1.getSubDistrictInfo() != null
                        && rawData.getSubDistrictInfo() != null
                        && rawData.getSubDistrictInfo().getSubdtId() != null
                        && rawData.getSubDistrictInfo().getSubdtId().equals(dto1.getSubDistrictInfo().getSubdtId())
                        && dto1.getFfmInfo().getFfmId() != null) {
                    FFMInfo ffm = new FFMInfo();
                    ffm.setFfmId(dto1.getFfmInfo().getFfmId());
                    ffm.setName(dto1.getFfmInfo().getName());
                    ffm.setShortName(dto1.getFfmInfo().getShortName());
                    listFfm.add(ffm);
                }
            });
            Set<LMInfo> listLm = new HashSet<>();
            subDistrictDto.forEach(dto2 -> {
                if (dto2.getSubDistrictInfo() != null
                        && rawData.getSubDistrictInfo() != null
                        && rawData.getSubDistrictInfo().getSubdtId() != null
                        && rawData.getSubDistrictInfo().getSubdtId().equals(dto2.getSubDistrictInfo().getSubdtId())
                        && dto2.getLmInfo().getLmId() != null) {
                    LMInfo lm = new LMInfo();
                    lm.setLmId(dto2.getLmInfo().getLmId());
                    lm.setName(dto2.getLmInfo().getName());
                    lm.setShortName(dto2.getLmInfo().getShortName());
                    listLm.add(lm);
                }
            });

            Set<WarehouseInfo> listWh = new HashSet<>();
            subDistrictDto.forEach(dto2 -> {
                if (dto2.getSubDistrictInfo() != null
                        && rawData.getSubDistrictInfo() != null
                        && rawData.getSubDistrictInfo().getSubdtId() != null
                        && rawData.getSubDistrictInfo().getSubdtId().equals(dto2.getSubDistrictInfo().getSubdtId())
                        && dto2.getWh().getWhId() != null) {
                    WarehouseInfo wh = new WarehouseInfo();
                    wh.setWhId(dto2.getWh().getWhId());
                    wh.setName(dto2.getWh().getName());
                    wh.setShortName(dto2.getWh().getShortName());
                    listWh.add(wh);
                }
            });

            dataOutput3.setFfms(listFfm);
            dataOutput3.setLms(listLm);
            dataOutput3.setWh(listWh);
            result.add(dataOutput3);
        }
        return result;
    }
}

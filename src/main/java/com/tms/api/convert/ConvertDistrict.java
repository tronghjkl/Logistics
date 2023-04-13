package com.tms.api.convert;

import com.tms.api.dto.DistrictData;
import com.tms.api.dto.ResponseDataDto;
import com.tms.api.dto.information.FFMInfo;
import com.tms.api.dto.information.LMInfo;
import com.tms.api.dto.information.WarehouseInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConvertDistrict {


    public static Set<ResponseDataDto> convertData(List<DistrictData> districDto) {
        Set<ResponseDataDto> result = new HashSet<>();

        for (DistrictData rawData : districDto) {
            if (rawData.getDistrictInfo() == null || rawData.getDistrictInfo().getDtId() == null) {
                continue;
            }
            ResponseDataDto dataOutput2 = new ResponseDataDto();
            dataOutput2.setLocationId(rawData.getDistrictInfo().getDtId());
            dataOutput2.setLocationName(rawData.getDistrictInfo().getName());

            Set<FFMInfo> listFfm = new HashSet<>();
            districDto.forEach(dto1 -> {
                if (dto1.getDistrictInfo() != null
                        && rawData.getDistrictInfo() != null
                        && rawData.getDistrictInfo().getDtId() != null
                        && rawData.getDistrictInfo().getDtId().equals(dto1.getDistrictInfo().getDtId())
                        && dto1.getFfmInfo().getFfmId() != null) {
                    FFMInfo ffm = new FFMInfo();
                    ffm.setFfmId(dto1.getFfmInfo().getFfmId());
                    ffm.setName(dto1.getFfmInfo().getName());
                    ffm.setShortName(dto1.getFfmInfo().getShortName());
                    listFfm.add(ffm);
                }
            });
            Set<LMInfo> listLm = new HashSet<>();
            districDto.forEach(dto2 -> {
                if (dto2.getDistrictInfo() != null
                        && rawData.getDistrictInfo() != null
                        && rawData.getDistrictInfo().getDtId() != null
                        && rawData.getDistrictInfo().getDtId().equals(dto2.getDistrictInfo().getDtId())
                        && dto2.getLmInfo().getLmId() != null) {
                    LMInfo lm = new LMInfo();
                    lm.setLmId(dto2.getLmInfo().getLmId());
                    lm.setName(dto2.getLmInfo().getName());
                    lm.setShortName(dto2.getLmInfo().getShortName());
                    listLm.add(lm);
                }
            });

            Set<WarehouseInfo> listWh = new HashSet<>();
            districDto.forEach(dto2 -> {
                if (dto2.getDistrictInfo() != null
                        && rawData.getDistrictInfo() != null
                        && rawData.getDistrictInfo().getDtId() != null
                        && rawData.getDistrictInfo().getDtId().equals(dto2.getDistrictInfo().getDtId())
                        && dto2.getWh().getWhId() != null) {
                    WarehouseInfo wh = new WarehouseInfo();
                    wh.setWhId(dto2.getWh().getWhId());
                    wh.setName(dto2.getWh().getName());
                    wh.setShortName(dto2.getWh().getShortName());
                    listWh.add(wh);
                }
            });

            dataOutput2.setFfms(listFfm);
            dataOutput2.setLms(listLm);
            dataOutput2.setWh(listWh);
            result.add(dataOutput2);
        }
        return result;
    }
}

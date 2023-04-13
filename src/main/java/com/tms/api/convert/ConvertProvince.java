package com.tms.api.convert;

import com.tms.api.dto.ProvinceData;
import com.tms.api.dto.ResponseDataDto;
import com.tms.api.dto.information.FFMInfo;
import com.tms.api.dto.information.LMInfo;
import com.tms.api.dto.information.WarehouseInfo;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConvertProvince {

    public static Set<ResponseDataDto> convertData(List<ProvinceData> provinceDataList) {
        Set<ResponseDataDto> result = new HashSet<>();
        for (ProvinceData data : provinceDataList) {
            if (data.getProvinceInfo() == null || data.getProvinceInfo().getPrvId() == null) {
                continue;
            }
            ResponseDataDto responseDataDto = new ResponseDataDto();
            responseDataDto.setLocationId(data.getProvinceInfo().getPrvId());
            responseDataDto.setLocationName(data.getProvinceInfo().getName());

            Set<FFMInfo> listFfm = new HashSet<>();
            provinceDataList.forEach(data1 -> {
                if (data1.getProvinceInfo() != null
                        && data1.getFfmInfo().getFfmId() != null
                        && data.getProvinceInfo() != null
                        && data.getProvinceInfo().getPrvId() != null
                        && data.getProvinceInfo().getPrvId().equals(data1.getProvinceInfo().getPrvId())
                ) {
                    FFMInfo ffmInfo = new FFMInfo();
                    ffmInfo.setFfmId(data1.getFfmInfo().getFfmId());
                    ffmInfo.setName(data1.getFfmInfo().getName());
                    ffmInfo.setShortName(data1.getFfmInfo().getShortName());
                    listFfm.add(ffmInfo);
                }
            });

            Set<LMInfo> listLm = new HashSet<>();
            provinceDataList.forEach(data2 -> {
                if (data2.getProvinceInfo() != null
                        && data2.getLmInfo().getLmId() != null
                        && data.getProvinceInfo() != null
                        && data.getProvinceInfo().getPrvId() != null
                        && data.getProvinceInfo().getPrvId().equals(data2.getProvinceInfo().getPrvId())
                ) {
                    LMInfo lmInfo = new LMInfo();
                    lmInfo.setLmId(data2.getLmInfo().getLmId());
                    lmInfo.setName(data2.getLmInfo().getName());
                    lmInfo.setShortName(data2.getLmInfo().getShortName());
                    listLm.add(lmInfo);
                }
            });

            Set<WarehouseInfo> listWh = new HashSet<>();
            provinceDataList.forEach(data3 -> {
                if (data3.getProvinceInfo() != null
                        && data3.getWh().getWhId() != null
                        && data.getProvinceInfo() != null
                        && data.getProvinceInfo().getPrvId() != null
                        && data.getProvinceInfo().getPrvId().equals(data3.getProvinceInfo().getPrvId())
                ) {
                    WarehouseInfo wh = new WarehouseInfo();
                    wh.setWhId(data3.getWh().getWhId());
                    wh.setName(data3.getWh().getName());
                    wh.setShortName(data3.getWh().getShortName());
                    listWh.add(wh);
                }
            });
            responseDataDto.setFfms(listFfm);
            responseDataDto.setLms(listLm);
            responseDataDto.setWh(listWh);
            result.add(responseDataDto);
        }
        return result;
    }


}

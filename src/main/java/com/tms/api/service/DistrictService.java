package com.tms.api.service;

import com.tms.api.dto.DistrictData;
import com.tms.api.dto.ResponseDataDto;
import com.tms.api.response.ResponseData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface DistrictService {
    List<DistrictData> getListDistrictInProvinceByLevel1(Integer provinceId);

    List<DistrictData> getListDistrictInProvinceByLevel2(Integer provinceId);

    List<DistrictData> getListDistrictInProvinceByLevel3(Integer provinceId);

    ResponseData<Set<ResponseDataDto>> getDistrict(Integer level,Integer provinceId);
}

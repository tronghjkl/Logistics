package com.tms.api.service;

import com.tms.api.dto.DistrictData;
import com.tms.api.dto.ResponseDataDto;
import com.tms.api.dto.SubDistrictData;
import com.tms.api.response.ResponseData;

import java.util.List;
import java.util.Set;

public interface SubDistrictService {
    List<SubDistrictData> getListSubDistrictInDistrictByLevel1(Integer districtId);

    List<SubDistrictData> getListSubDistrictInDistrictByLevel2(Integer districtId);

    List<SubDistrictData> getListSubDistrictInDistrictByLevel3(Integer districtId);

    ResponseData<Set<ResponseDataDto>> getSubDistrict(Integer level, Integer districtId);
}

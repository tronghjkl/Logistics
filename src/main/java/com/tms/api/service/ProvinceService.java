package com.tms.api.service;

import com.tms.api.dto.ProvinceData;
import com.tms.api.dto.ResponseDataDto;
import com.tms.api.response.ResponseData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public interface ProvinceService {
    List<ProvinceData> getListProvinceByLevel1();

    List<ProvinceData> getListProvinceByLevel2();

    List<ProvinceData> getListProvinceByLevel3();

    ResponseData<Set<ResponseDataDto>> getProvince(Integer level);
}

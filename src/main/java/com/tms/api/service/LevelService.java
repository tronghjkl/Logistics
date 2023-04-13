package com.tms.api.service;

import com.tms.api.dto.ResponseDataDto;
import com.tms.api.response.ResponseData;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface LevelService {
    ResponseData<Set<ResponseDataDto>> getProvince(Integer level);

    ResponseData<Set<ResponseDataDto>> getDistrict(Integer provinceId, Integer level);

    ResponseData<Set<ResponseDataDto>> getSubDistrict(Integer districtId, Integer level);
}

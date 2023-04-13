package com.tms.api.controller;

import com.tms.api.dto.ResponseDataDto;
import com.tms.api.response.ResponseData;
import com.tms.api.service.DistrictService;
import com.tms.api.service.LevelService;
import com.tms.api.service.ProvinceService;
import com.tms.api.service.SubDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("/levelv2")
@RestController
@Validated
public class LevelControllerV2 {
    @Autowired
    ProvinceService provinceService;
    @Autowired
    DistrictService districtService;

    @Autowired
    SubDistrictService subDistrictService;

    @GetMapping("/getProvince")
    public ResponseData<Set<ResponseDataDto>> getProvince(@RequestParam Integer level) {
        return provinceService.getProvince(level);
    }

    @GetMapping("/getDistrict")
    public ResponseData<Set<ResponseDataDto>> getDistrict(@RequestParam Integer level, @RequestParam Integer locationId) {
        return districtService.getDistrict(level, locationId);
    }

    @GetMapping("/getSubDistrict")
    public ResponseData<Set<ResponseDataDto>> getSubDistrict(@RequestParam Integer level, @RequestParam Integer locationId) {
        return subDistrictService.getSubDistrict(level, locationId);
    }
}

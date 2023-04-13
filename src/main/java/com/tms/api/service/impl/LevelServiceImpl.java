package com.tms.api.service.impl;

import com.tms.api.convert.ConvertDistrict;
import com.tms.api.convert.ConvertProvince;
import com.tms.api.convert.ConvertSubdistrict;
import com.tms.api.dto.DistrictData;
import com.tms.api.dto.ProvinceData;
import com.tms.api.dto.ResponseDataDto;
import com.tms.api.dto.SubDistrictData;
import com.tms.api.repository.DistrictRepository;
import com.tms.api.repository.ProvinceRepository;
import com.tms.api.repository.SubdistrictRepository;
import com.tms.api.response.ResponseData;
import com.tms.api.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    SubdistrictRepository subdistrictRepository;

    Set<ResponseDataDto> list;



    @Override
    public ResponseData<Set<ResponseDataDto>> getProvince(Integer level) {
        if (!CollectionUtils.isEmpty(list)) {
            list.clear();
        }
        List<ProvinceData> provinceList;
        switch (level) {
            case 1:
                provinceList = provinceRepository.getListProvinceByLevel1();
                list = ConvertProvince.convertData(provinceList);
                return new ResponseData<>("success", list);
            case 2:
                provinceList = provinceRepository.getListProvinceByLevel2();
                list = ConvertProvince.convertData(provinceList);
                return new ResponseData<>("success", list);
            case 3:
                provinceList = provinceRepository.getListProvinceByLevel3();
                list = ConvertProvince.convertData(provinceList);
                return new ResponseData<>("success", list);

        }
        return new ResponseData<>("please check validated or level");
    }


    @Override
    public ResponseData<Set<ResponseDataDto>> getDistrict( Integer level,Integer provinceId) {

        if (!CollectionUtils.isEmpty(list)) {
            list.clear();
        }
        List<DistrictData> districtData;
        switch (level) {
            case 1:
                districtData = districtRepository.getDistinctByLevel1(provinceId);
                list = ConvertDistrict.convertData(districtData);
                return new ResponseData<>("success", list);
            case 2:
                districtData = districtRepository.getDistinctByLevel2(provinceId);
                list = ConvertDistrict.convertData(districtData);
                return new ResponseData<>("success", list);
            case 3:
                districtData = districtRepository.getDistinctByLevel3(provinceId);
                list = ConvertDistrict.convertData(districtData);
                return new ResponseData<>("success", list);

        }
        return new ResponseData<>("please check validated or level");
    }

    @Override

    public ResponseData<Set<ResponseDataDto>> getSubDistrict( Integer level,Integer districtId) {
        if (!CollectionUtils.isEmpty(list)) {
            list.clear();
        }
        List<SubDistrictData> subDistrictList;

        switch (level) {
            case 1:
                subDistrictList = subdistrictRepository.getSubDistrictByLevel1(districtId);
                list = ConvertSubdistrict.convertData(subDistrictList);
                return new ResponseData<>("success", list);
            case 2:
                subDistrictList = subdistrictRepository.getSubDistrictByLevel2(districtId);
                list = ConvertSubdistrict.convertData(subDistrictList);
                return new ResponseData<>("success", list);
            case 3:
                subDistrictList = subdistrictRepository.getSubDistrictByLevel3(districtId);
                list = ConvertSubdistrict.convertData(subDistrictList);
                return new ResponseData<>("success", list);
        }
        return new ResponseData<>("please check validated or level");
    }


}

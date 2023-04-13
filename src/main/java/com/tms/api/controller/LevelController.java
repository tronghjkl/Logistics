package com.tms.api.controller;

import com.tms.api.dto.ResponseDataDto;
import com.tms.api.response.ResponseData;
import com.tms.api.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RequestMapping("/level")
@RestController
@Validated
public class LevelController {
    @Autowired
    LevelService levelService;

    @GetMapping("/getProvince")
    public ResponseData<Set<ResponseDataDto>> getProvince(@RequestParam Integer level) {
        return levelService.getProvince(level);
    }

    @GetMapping("/getDistrict")
    public ResponseData<Set<ResponseDataDto>> getDistrict(@RequestParam Integer level, @RequestParam Integer locationId) {
        return levelService.getDistrict(level, locationId);
    }

    @GetMapping("/getSubDistrict")
    public ResponseData<Set<ResponseDataDto>> getSubDistrict(@RequestParam Integer level, @RequestParam Integer locationId) {
        return levelService.getSubDistrict(level, locationId);
    }
}

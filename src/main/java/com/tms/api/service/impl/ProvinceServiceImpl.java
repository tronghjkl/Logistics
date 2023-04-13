package com.tms.api.service.impl;

import com.tms.api.convert.ConvertProvince;
import com.tms.api.dto.ProvinceData;
import com.tms.api.dto.ResponseDataDto;
import com.tms.api.dto.information.*;
import com.tms.api.response.ResponseData;
import com.tms.api.service.ProvinceService;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

import static com.tms.api.consts.Const.*;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    Set<ResponseDataDto> responseDataDtos;
    private final JdbcTemplate jdbcTemplate;

    public ProvinceServiceImpl(@Qualifier("firstJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProvinceData> getListProvinceByLevel1() {

        String sql = "select distinct prv.prv_id as prvId, prv.name as prvName, " +
                "ffm.pn_id as ffmId, ffm.name as ffmName, ffm.shortname as ffmShortName, " +
                "lm.pn_id as lmId, lm.name as lmName, lm.shortname as lmShortName, " +
                "wh.warehouse_id as whId, wh.warehouse_name as whName, wh.warehouse_shortname as whShortName "
                + "from cf_default_delivery df "
                + "left join bp_partner ffm on ffm.pn_id = df.partner_id "
                + "left join bp_partner lm on lm.pn_id = df.lastmile_id "
                + "join lc_province prv on prv.prv_id = df.province_id "
                + "left join bp_warehouse wh on wh.warehouse_id = df.warehouse_id "
                + "order by prv.prv_id";

        return getProvinceData(sql);

    }


    @Override
    public List<ProvinceData> getListProvinceByLevel2() {

        String sql = "select distinct prv.prv_id as prvId, prv.name as prvName, " +
                "ffm.pn_id as ffmId, ffm.name as ffmName, ffm.shortname as ffmShortName, " +
                "lm.pn_id as lmId, lm.name as lmName, lm.shortname as lmShortName, " +
                "wh.warehouse_id as whId, wh.warehouse_name as whName, wh.warehouse_shortname as whShortName "
                + "from cf_default_delivery df "
                + "join lc_district d on d.dt_id = df.province_id "
                + "join lc_province prv on prv.prv_id = d.prv_id "
                + "left join bp_partner ffm on ffm.pn_id = df.partner_id "
                + "left join bp_partner lm on lm.pn_id = df.lastmile_id "
                + "left join bp_warehouse wh on wh.warehouse_id = df.warehouse_id "
                + "order by prv.prv_id";


        return getProvinceData(sql);
    }

    @Override
    public List<ProvinceData> getListProvinceByLevel3() {

        String sql = "select distinct prv.prv_id as prvId, prv.name as prvName, " +
                "ffm.pn_id as ffmId, ffm.name as ffmName, ffm.shortname as ffmShortName, " +
                "lm.pn_id as lmId, lm.name as lmName, lm.shortname as lmShortName, " +
                "wh.warehouse_id as whId, wh.warehouse_name as whName, wh.warehouse_shortname as whShortName " +
                "from cf_default_delivery df " +
                "left join lc_subdistrict sdt on sdt.sdt_id = df.province_id  " +
                "left join bp_partner ffm on ffm.pn_id = df.partner_id " +
                "left join bp_partner lm on lm.pn_id = df.lastmile_id  " +
                "left join bp_warehouse wh on wh.warehouse_id = df.warehouse_id " +
                "join lc_district d on d.dt_id = sdt.dt_id " +
                "join lc_province prv on prv.prv_id = d.prv_id  " +
                "order by prv.prv_id";


        return getProvinceData(sql);
    }

    @Override
    public ResponseData<Set<ResponseDataDto>> getProvince(Integer level) {
        if (!CollectionUtils.isEmpty(responseDataDtos)) {
            responseDataDtos.clear();
        }
        List<ProvinceData> provinceList;
        switch (level) {
            case LEVEL_1:
                provinceList = getListProvinceByLevel1();
                responseDataDtos = ConvertProvince.convertData(provinceList);
                return new ResponseData<>("success", responseDataDtos);
            case LEVEL_2:
                provinceList = getListProvinceByLevel2();
                responseDataDtos = ConvertProvince.convertData(provinceList);
                return new ResponseData<>("success", responseDataDtos);
            case LEVEL_3:
                provinceList = getListProvinceByLevel3();
                responseDataDtos = ConvertProvince.convertData(provinceList);
                return new ResponseData<>("success", responseDataDtos);

        }
        return new ResponseData<>("please check validated or level");
    }

    private List<ProvinceData> getProvinceData(String sql) {
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {

            // Xử lý dữ liệu trả về
            Integer prvId = (resultSet.getObject("prvId", Integer.class));
            String prvName = (resultSet.getObject("prvName", String.class));
            ProvinceInfo provinceInfo = new ProvinceInfo(prvId, prvName);

            Integer ffmId = (resultSet.getObject("ffmId", Integer.class));
            String ffmName = (resultSet.getObject("ffmName", String.class));
            String ffmShortName = (resultSet.getObject("ffmShortName", String.class));
            FFMInfo ffmInfo = new FFMInfo(ffmId, ffmName, ffmShortName);

            Integer lmId = (resultSet.getObject("lmId", Integer.class));
            String lmName = (resultSet.getObject("lmName", String.class));
            String lmShortName = (resultSet.getObject("lmShortName", String.class));
            LMInfo lmInfo = new LMInfo(lmId, lmName, lmShortName);

            Integer whId = (resultSet.getObject("whId", Integer.class));
            String whName = (resultSet.getObject("whName", String.class));
            String whShortName = (resultSet.getObject("whShortName", String.class));
            WarehouseInfo warehouseInfo = new WarehouseInfo(whId, whName, whShortName);

            return new ProvinceData(provinceInfo, ffmInfo, lmInfo, warehouseInfo);
        });
    }

}

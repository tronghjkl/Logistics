package com.tms.api.service.impl;

import com.tms.api.convert.ConvertDistrict;
import com.tms.api.dto.DistrictData;
import com.tms.api.dto.ResponseDataDto;
import com.tms.api.dto.information.*;
import com.tms.api.response.ResponseData;
import com.tms.api.service.DistrictService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Set;

import static com.tms.api.consts.Const.*;

@Service
public class DistrictServiceImpl implements DistrictService {

    Set<ResponseDataDto> responseDataDtos;
    private final JdbcTemplate jdbcTemplate;

    public DistrictServiceImpl(@Qualifier("firstJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DistrictData> getListDistrictInProvinceByLevel1(Integer provinceId) {
        String sql = "select distinct d.dt_id as dtId, d.name as dtName, " +
                "ffm.pn_id as ffmId, ffm.name as ffmName, ffm.shortname as ffmShortName, " +
                "lm.pn_id as lmId, lm.name as lmName, lm.shortname as lmShortName, " +
                "wh.warehouse_id as whId, wh.warehouse_name as whName, wh.warehouse_shortname as whShortName " +
                "from cf_default_delivery df " +
                "join lc_province prv on prv.prv_id = df.province_id  " +
                "left join bp_partner ffm on ffm.pn_id = df.partner_id " +
                "left join bp_partner lm on lm.pn_id = df.lastmile_id  " +
                "join lc_district d on d.prv_id = prv.prv_id " +
                "left join bp_warehouse wh on wh.warehouse_id = df.warehouse_id " +
                "where prv.prv_id = ? " +
                "order by d.dt_id";

        return getDistrictData(sql, provinceId);
    }

    @Override
    public List<DistrictData> getListDistrictInProvinceByLevel2(Integer provinceId) {
        String sql = "select distinct d.dt_id as dtId, d.name as dtName, " +
                "ffm.pn_id as ffmId, ffm.name as ffmName, ffm.shortname as ffmShortName, " +
                "lm.pn_id as lmId, lm.name as lmName, lm.shortname as lmShortName, " +
                "wh.warehouse_id as whId, wh.warehouse_name as whName, wh.warehouse_shortname as whShortName " +
                "from cf_default_delivery df " +
                "join lc_district d on d.dt_id = df.province_id  " +
                "join lc_province prv on prv.prv_id = d.prv_id " +
                "left join bp_partner ffm on ffm.pn_id = df.partner_id " +
                "left join bp_partner lm on lm.pn_id = df.lastmile_id  " +
                "left join bp_warehouse wh on wh.warehouse_id = df.warehouse_id " +
                "where prv.prv_id = ? " +
                "order by d.dt_id";

        return getDistrictData(sql, provinceId);
    }

    @Override
    public List<DistrictData> getListDistrictInProvinceByLevel3(Integer provinceId) {
        String sql = "select distinct d.dt_id as dtId, d.name as dtName, " +
                "ffm.pn_id as ffmId, ffm.name as ffmName, ffm.shortname as ffmShortName, " +
                "lm.pn_id as lmId, lm.name as lmName, lm.shortname as lmShortName, " +
                "wh.warehouse_id as whId, wh.warehouse_name as whName, wh.warehouse_shortname as whShortName " +
                "from cf_default_delivery df " +
                "join lc_subdistrict sdt on sdt.sdt_id = df.province_id  " +
                "join lc_district d on d.dt_id = sdt.dt_id " +
                "join lc_province prv on prv.prv_id = d.prv_id " +
                "left join bp_partner ffm on ffm.pn_id = df.partner_id " +
                "left join bp_partner lm on lm.pn_id = df.lastmile_id  " +
                "left join bp_warehouse wh on wh.warehouse_id = df.warehouse_id " +
                "where prv.prv_id = ? " +
                "order by d.dt_id";

        return getDistrictData(sql, provinceId);
    }

    @Override
    public ResponseData<Set<ResponseDataDto>> getDistrict(Integer level, Integer provinceId) {
        if (!CollectionUtils.isEmpty(responseDataDtos)) {
            responseDataDtos.clear();
        }

        List<DistrictData> districtData;
        switch (level) {
            case LEVEL_1:
                districtData = getListDistrictInProvinceByLevel1(provinceId);
                responseDataDtos = ConvertDistrict.convertData(districtData);
                return new ResponseData<>("success", responseDataDtos);
            case LEVEL_2:
                districtData = getListDistrictInProvinceByLevel2(provinceId);
                responseDataDtos = ConvertDistrict.convertData(districtData);
                return new ResponseData<>("success", responseDataDtos);
            case LEVEL_3:
                districtData = getListDistrictInProvinceByLevel3(provinceId);
                responseDataDtos = ConvertDistrict.convertData(districtData);
                return new ResponseData<>("success", responseDataDtos);

        }
        return new ResponseData<>("please check validated or level");
    }

    private List<DistrictData> getDistrictData(String sql, Integer provinceId) {
        return jdbcTemplate.query(sql, new Object[]{provinceId}, (resultSet, rowNum) -> {

            // Xử lý dữ liệu trả về
            Integer prvId = (resultSet.getObject("dtId", Integer.class));
            String prvName = (resultSet.getObject("dtName", String.class));
            DistrictInfo districtInfo = new DistrictInfo(prvId, prvName);

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

            return new DistrictData(districtInfo, ffmInfo, lmInfo, warehouseInfo);
        });
    }
}

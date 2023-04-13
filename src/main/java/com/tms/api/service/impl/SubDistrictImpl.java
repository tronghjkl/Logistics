package com.tms.api.service.impl;

import com.tms.api.convert.ConvertSubdistrict;
import com.tms.api.dto.ResponseDataDto;
import com.tms.api.dto.SubDistrictData;
import com.tms.api.dto.information.*;
import com.tms.api.response.ResponseData;
import com.tms.api.service.SubDistrictService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

import static com.tms.api.consts.Const.*;

@Service
public class SubDistrictImpl implements SubDistrictService {
    Set<ResponseDataDto> responseDataDtos;
    private final JdbcTemplate jdbcTemplate;

    public SubDistrictImpl(@Qualifier("firstJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SubDistrictData> getListSubDistrictInDistrictByLevel1(Integer districtId) {
        String sql = "select distinct sdt.sdt_id as sdtId, sdt.name as sdtName, " +
                "ffm.pn_id as ffmId, ffm.name as ffmName, ffm.shortname as ffmShortName, " +
                "lm.pn_id as lmId, lm.name as lmName, lm.shortname as lmShortName, " +
                "wh.warehouse_id as whId, wh.warehouse_name as whName, wh.warehouse_shortname as whShortName " +
                "from cf_default_delivery df "+
                "join lc_province prv on prv.prv_id= df.province_id " +
                "join lc_district dt on dt.prv_id= prv.prv_id " +
                "join lc_subdistrict sdt on sdt.dt_id = dt.dt_id " +
                "left join bp_partner ffm on ffm.pn_id = df.partner_id " +
                "left join bp_partner lm on lm.pn_id = df.lastmile_id  " +
                "left join bp_warehouse wh on wh.warehouse_id = df.warehouse_id " +
                "where dt.dt_id = ? " +
                "order by sdt.sdt_id";



        return getSubDistrictData(sql, districtId);
    }

    @Override
    public List<SubDistrictData> getListSubDistrictInDistrictByLevel2(Integer districtId) {
        String sql = "select distinct sdt.sdt_id as sdtId, sdt.name as sdtName, " +
                "ffm.pn_id as ffmId, ffm.name as ffmName, ffm.shortname as ffmShortName, " +
                "lm.pn_id as lmId, lm.name as lmName, lm.shortname as lmShortName, " +
                "wh.warehouse_id as whId, wh.warehouse_name as whName, wh.warehouse_shortname as whShortName " +
                "from cf_default_delivery df "+
                "join lc_district dt on dt.dt_id = df.province_id  " +
                "join lc_subdistrict sdt on sdt.dt_id = dt.dt_id " +
                "left join bp_partner ffm on ffm.pn_id = df.partner_id " +
                "left join bp_partner lm on lm.pn_id = df.lastmile_id  " +
                "left join bp_warehouse wh on wh.warehouse_id = df.warehouse_id " +
                "where dt.dt_id = ? " +
                "order by sdt.sdt_id";

        return getSubDistrictData(sql, districtId);
    }

    @Override
    public List<SubDistrictData> getListSubDistrictInDistrictByLevel3(Integer districtId) {
        String sql = "select distinct sdt.sdt_id as sdtId, sdt.name as sdtName, " +
                "ffm.pn_id as ffmId, ffm.name as ffmName, ffm.shortname as ffmShortName, " +
                "lm.pn_id as lmId, lm.name as lmName, lm.shortname as lmShortName, " +
                "wh.warehouse_id as whId, wh.warehouse_name as whName, wh.warehouse_shortname as whShortName " +
                "from cf_default_delivery df " +
                "join lc_subdistrict sdt on sdt.sdt_id = df.province_id  " +
                "join lc_district dt on dt.dt_id = sdt.dt_id " +
                "left join bp_partner ffm on ffm.pn_id = df.partner_id " +
                "left join bp_partner lm on lm.pn_id = df.lastmile_id  " +
                "left join bp_warehouse wh on wh.warehouse_id = df.warehouse_id " +
                "where dt.dt_id = ? " +
                "order by sdt.sdt_id";

        return getSubDistrictData(sql, districtId);
    }

    @Override
    public ResponseData<Set<ResponseDataDto>> getSubDistrict(Integer level, Integer districtId) {
        if (!CollectionUtils.isEmpty(responseDataDtos)) {
            responseDataDtos.clear();
        }

        List<SubDistrictData> subDistrictData;
        switch (level) {
            case LEVEL_1:
                subDistrictData = getListSubDistrictInDistrictByLevel1(districtId);
                responseDataDtos = ConvertSubdistrict.convertData(subDistrictData);
                return new ResponseData<>("success", responseDataDtos);
            case LEVEL_2:
                subDistrictData = getListSubDistrictInDistrictByLevel2(districtId);
                responseDataDtos = ConvertSubdistrict.convertData(subDistrictData);
                return new ResponseData<>("success", responseDataDtos);
            case LEVEL_3:
                subDistrictData = getListSubDistrictInDistrictByLevel3(districtId);
                responseDataDtos = ConvertSubdistrict.convertData(subDistrictData);
                return new ResponseData<>("success", responseDataDtos);

        }
        return new ResponseData<>("please check validated or level");
    }

    private List<SubDistrictData> getSubDistrictData(String sql, Integer districtId) {
        return jdbcTemplate.query(sql, new Object[]{districtId}, (resultSet, rowNum) -> {

            // Xử lý dữ liệu trả về
            Integer sdtId = (resultSet.getObject("sdtId", Integer.class));
            String sdtName = (resultSet.getObject("sdtName", String.class));
            SubDistrictInfo subdistrictInfo = new SubDistrictInfo(sdtId, sdtName);

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

            return new SubDistrictData(subdistrictInfo, ffmInfo, lmInfo, warehouseInfo);
        });
    }
}

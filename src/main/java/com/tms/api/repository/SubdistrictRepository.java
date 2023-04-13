package com.tms.api.repository;

import com.tms.api.dto.SubDistrictData;
import com.tms.api.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface SubdistrictRepository extends JpaRepository<District, Integer> {
    @Query(" select distinct new com.tms.api.dto.SubDistrictData(sdt.id, sdt.name, " +
            " ffm.id, ffm.name, ffm.shortName,lm.id, lm.name, lm.shortName , wh.warehouseId,wh.warehouseName, wh.warehouseShortName)" +
            " from DefaultDelivery df " +
            "join Province prv on prv.id= df.provinceId " +
            "join District dt on dt.provinceId= prv.id " +
            " left join Subdistrict sdt on sdt.dtId = dt.id " +
            " left join Partner ffm on ffm.id = df.partnerId " +
            " left join Partner lm on lm.id = df.lastmileId " +
            "left join Warehouse wh on wh.warehouseId = df.wareHouseId " +
            " where dt.id = ?1" +
            " order by sdt.id ")
    List<SubDistrictData> getSubDistrictByLevel1(Integer districtId);

    @Query(" select distinct new com.tms.api.dto.SubDistrictData(sdt.id, sdt.name, ffm.id, " +
            "ffm.name, ffm.shortName,lm.id, lm.name, lm.shortName, wh.warehouseId,wh.warehouseName, wh.warehouseShortName )" +
            "from DefaultDelivery df " +
            "join District dt on dt.id= df.provinceId " +
            "join Subdistrict sdt on sdt.dtId = dt.id " +
            " left join Partner ffm on ffm.id = df.partnerId " +
            " left join Partner lm on lm.id = df.lastmileId " +
            "left join Warehouse wh on wh.warehouseId = df.wareHouseId " +
            "where dt.id = ?1 " +
            "order by sdt.id ")
    List<SubDistrictData> getSubDistrictByLevel2(Integer districtId);

    @Query(" select distinct new com.tms.api.dto.SubDistrictData(sdt.id, sdt.name, ffm.id, ffm.name, ffm.shortName," +
            " lm.id, lm.name, lm.shortName , wh.warehouseId,wh.warehouseName, wh.warehouseShortName)" +
            " from DefaultDelivery df " +
            " join Subdistrict sdt on df.provinceId = sdt.id " +
            " join District dt on dt.id= sdt.dtId" +
            " left join Partner ffm on ffm.id = df.partnerId " +
            " left join Partner lm on lm.id = df.lastmileId " +
            "left join Warehouse wh on wh.warehouseId = df.wareHouseId " +
            " where dt.id = ?1" +
            " order by sdt.id ")
    List<SubDistrictData> getSubDistrictByLevel3(Integer districtId);
}

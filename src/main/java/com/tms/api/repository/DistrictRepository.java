package com.tms.api.repository;

import com.tms.api.dto.DistrictData;
import com.tms.api.entity.District;
import com.tms.api.entity.Subdistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<Subdistrict, Integer> {
    @Query("select distinct new com.tms.api.dto.DistrictData(dt.id, dt.name, ffm.id, ffm.name, " +
            "ffm.shortName, lm.id, lm.name, lm.shortName, wh.warehouseId,wh.warehouseName, wh.warehouseShortName) " +
            "from DefaultDelivery df " +
            "join Province prv on prv.id = df.provinceId " +
            "join District dt  on dt.provinceId = prv.id " +
            "left join Partner ffm on ffm.id = df.partnerId " +
            "left join Partner lm on lm.id = df.lastmileId " +
            "left join Warehouse wh on wh.warehouseId = df.wareHouseId " +
            "where prv.id= ?1 " +
            "order by dt.id")
    List<DistrictData> getDistinctByLevel1(Integer provinceId);

    @Query("select distinct new com.tms.api.dto.DistrictData(dt.id, dt.name,ffm.id, ffm.name, ffm.shortName," +
            "lm.id, lm.name, lm.shortName, wh.warehouseId,wh.warehouseName, wh.warehouseShortName )" +
            "from DefaultDelivery df " +
            "join District dt on dt.id = df.provinceId " +
            "join Province prv on prv.id = dt.provinceId " +
            "left join Partner ffm on ffm.id = df.partnerId " +
            "left join Partner lm on lm.id = df.lastmileId " +
            "left join Warehouse wh on wh.warehouseId = df.wareHouseId " +
            "where prv.id= ?1 " +
            "order by dt.id ")
    List<DistrictData> getDistinctByLevel2(Integer provinceId);

    @Query("select distinct new com.tms.api.dto.DistrictData(dt.id, dt.name,ffm.id, ffm.name, ffm.shortName," +
            "lm.id, lm.name, lm.shortName, wh.warehouseId,wh.warehouseName, wh.warehouseShortName) " +
            "from DefaultDelivery df " +
            "join Subdistrict sdt on sdt.id = df.provinceId " +
            "join District dt on dt.id =sdt.dtId " +
            "join Province prv on prv.id = dt.provinceId " +
            "left join Partner ffm on ffm.id = df.partnerId " +
            "left join Partner lm on lm.id = df.lastmileId " +
            "left join Warehouse wh on wh.warehouseId = df.wareHouseId " +
            "where dt.provinceId = ?1 " +
            "order by dt.id ")
    List<DistrictData> getDistinctByLevel3(Integer provinceId);
}

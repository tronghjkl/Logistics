package com.tms.api.repository;

import com.tms.api.dto.ProvinceData;
import com.tms.api.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("select distinct new com.tms.api.dto.ProvinceData(prv.id, prv.name, ffm.id, ffm.name, ffm.shortName," +
            " lm.id, lm.name, lm.shortName,wh.warehouseId, wh.warehouseName, wh.warehouseShortName) " +
            "from DefaultDelivery df " +
            "join Province prv on prv.id = df.provinceId " +
            "left join Partner ffm on ffm.id = df.partnerId " +
            "left join Partner lm on lm.id = df.lastmileId " +
            "left join Warehouse wh on wh.warehouseId = df.wareHouseId " +
            "order by prv.id")
    List<ProvinceData> getListProvinceByLevel1();


    @Query("select distinct new com.tms.api.dto.ProvinceData(prv.id, prv.name, ffm.id, ffm.name, ffm.shortName," +
            "lm.id, lm.name, lm.shortName,wh.warehouseId, wh.warehouseName, wh.warehouseShortName)" +
            "from DefaultDelivery df " +
            "join District dt on df.provinceId = dt.id " +
            "join Province prv on prv.id = dt.provinceId " +
            "left join Partner ffm on df.partnerId = ffm.id " +
            "left join Partner  lm on lm.id = df.lastmileId " +
            "left join Warehouse wh on wh.warehouseId = df.wareHouseId " +
            "order by prv.id")
    List<ProvinceData> getListProvinceByLevel2();

    @Query("select distinct new com.tms.api.dto.ProvinceData(prv.id, prv.name, ffm.id, ffm.name, ffm.shortName," +
            "lm.id, lm.name, lm.shortName,wh.warehouseId, wh.warehouseName, wh.warehouseShortName)" +
            "from DefaultDelivery df " +
            "join Subdistrict sdt on df.provinceId = sdt.id " +
            "join District dt on dt.id = sdt.dtId " +
            "join Province prv on prv.id = dt.provinceId " +
            "left join Partner ffm on df.partnerId = ffm.id " +
            "left join Partner  lm on lm.id = df.lastmileId " +
            "left join Warehouse wh on wh.warehouseId = df.wareHouseId " +
            "order by prv.id")
    List<ProvinceData> getListProvinceByLevel3();
}

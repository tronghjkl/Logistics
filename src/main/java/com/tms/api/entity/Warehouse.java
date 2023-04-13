package com.tms.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bp_warehouse")
@AllArgsConstructor
@Setter
@Getter
public class Warehouse {

    @Column(name = "org_id")
    private Integer orgId;
    @Id
    @Column(name = "warehouse_id")
    private Integer warehouseId;
    @Column(name = "partner_id")
    private Integer partnerId;
    @Column(name = "warehouse_name")
    private String warehouseName;
    @Column(name = "warehouse_shortname")
    private String warehouseShortName;
    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "contact_phone")
    private String contactPhone;
    @Column(name = "address")
    private String address;
    @Column(name = "full_address")
    private String fullAddress;
    @Column(name = "wards_id")
    private Integer wardsId;

    @Column(name = "wards_code")
    private Integer wardsCode;

    @Column(name = "wards_name")
    private Integer wardsName;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "district_code")
    private Integer districtCode;

    @Column(name = "district_name")
    private Integer districtName;

    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "province_code")
    private Integer provinceCode;

    @Column(name = "province_name")
    private Integer provinceName;

    @Column(name = "email")
    private Integer email;

    @Column(name = "is_edit_wh")
    private Integer isEditWh;

    @Column(name = "ismain")
    private Integer isMain;

    @Column(name = "latitude")
    private Integer latitude;

    @Column(name = "longitude")
    private Integer longitude;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "createby")
    private Integer createBy;

    @Column(name = "createdate")
    private Integer createDate;

    @Column(name = "modifyby")
    private Integer modifyBy;

    @Column(name = "modifydate")
    private Integer modifyDate;

    @Column(name = "wh_code_inpartner")
    private Integer whCodeInpartner;

    public Warehouse() {

    }
}

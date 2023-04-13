package com.tms.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "lc_district")
public class District {
    @Id
    @Column(name = "dt_id")
    private Integer id;

    @Column(name = "prv_id")
    private Integer provinceId;

    @Column(name = "name")
    private String name;

    @Column(name = "shortname")
    private String shortName;

    @Column(name = "dcsr")
    private String dcsr;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private Integer status;

    public District() {

    }
}

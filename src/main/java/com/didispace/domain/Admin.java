package com.didispace.domain;

/**
 * Created by liuhaiyang on 2017/3/8.
 */

import org.springframework.data.annotation.Id;

/**
 * 测试复杂的mongo查询
 */
public class Admin {
    @Id
    private String adminId;
    private String name;
    private Integer sex;
    private String address;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
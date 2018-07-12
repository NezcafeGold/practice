package ru.bellintegrator.practice.office.view;


import io.swagger.annotations.ApiModelProperty;

public class OfficeView {

    @ApiModelProperty(hidden = true)
    public String id;

    public String name;

    public int address;

    public String phone;

    public boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";address:" +address +";phone:" + phone + ";isActive:" + isActive + "}";
    }
}

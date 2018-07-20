package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Организация")
public class OrganizationView {

    @ApiModelProperty(hidden = true, value = "Первичный ключ id")
    public String id;

    @ApiModelProperty(value = "Название организации")
    public String name;

    @ApiModelProperty(value = "Полное название организации")
    public String fullName;

    @ApiModelProperty(value = "ИНН")
    public String inn;

    @ApiModelProperty(value = "КПП")
    public String kpp;

    @ApiModelProperty(value = "Адрес")
    public String address;

    @ApiModelProperty(value = "Телефон")
    public String phone;

    @ApiModelProperty(value = "Активность")
    public boolean isActive;

    @ApiModelProperty(value = "Переопределенный toString()")
    @Override
    public String toString() {
        return "{id:" + id +
                ";name:" + name +
                ";fullName:" + fullName +
                ";inn:" + inn +
                ";kpp:" + kpp +
                ";address:" + address +
                ";phone:" + phone +
                ";isActive:" + isActive +
                "}";
    }
}

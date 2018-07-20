package ru.bellintegrator.practice.office.view;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Офис")
public class OfficeView {

    @ApiModelProperty(hidden = true, value = "Первичный ключ id")
    public String id;

    @ApiModelProperty(value = "Название офиса")
    public String name;

    @ApiModelProperty(value = "Адрес")
    public String address;

    @ApiModelProperty(value = "Телефон")
    public String phone;

    @ApiModelProperty(value = "Активность")
    public boolean isActive;

    @ApiModelProperty(value = "Переопределенный toString()")
    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";address:" +address +";phone:" + phone + ";isActive:" + isActive + "}";
    }
}

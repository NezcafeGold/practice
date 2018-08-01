package ru.bellintegrator.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Организация")
public class OrganizationView {

    @ApiModelProperty(hidden = true, value = "Первичный ключ id")
    public Long id;

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
    public Boolean isActive;

    @ApiModelProperty(value = "Служебное поле Hibernate")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer version;

    public OrganizationView() {
    }

    public OrganizationView(Long id, String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

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

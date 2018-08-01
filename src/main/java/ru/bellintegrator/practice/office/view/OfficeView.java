package ru.bellintegrator.practice.office.view;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Офис")
public class OfficeView {

    @ApiModelProperty(hidden = true, value = "Первичный ключ id")
    public Long id;

    @ApiModelProperty(value = "Название офиса")
    public String name;

    @ApiModelProperty(value = "Адрес")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String address;

    @ApiModelProperty(value = "Телефон")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String phone;

    @ApiModelProperty(value = "Активность")
    public Boolean isActive;

    @ApiModelProperty(value = "id организации")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long orgId;

    public OfficeView() {
    }

    public OfficeView(Long id, String name, String address, String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    @ApiModelProperty(value = "Переопределенный toString()")
    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";address:" +address +";phone:" + phone + ";isActive:" + isActive + "}";
    }
}

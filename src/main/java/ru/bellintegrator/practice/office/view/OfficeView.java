package ru.bellintegrator.practice.office.view;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Офис")
public class OfficeView {

    @ApiModelProperty(hidden = true, value = "Первичный ключ id")
    @JsonProperty
    public Long id;

    @ApiModelProperty(value = "Название офиса")
    @JsonProperty
    public String name;

    @ApiModelProperty(value = "Адрес")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String address;

    @ApiModelProperty(value = "Телефон")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String phone;

    @ApiModelProperty(value = "Активность")
    @JsonProperty
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

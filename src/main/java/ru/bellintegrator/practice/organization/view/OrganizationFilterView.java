package ru.bellintegrator.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@ApiModel(value = "Вид организации после фильтра")
public class OrganizationFilterView {

    @ApiModelProperty(hidden = true, value = "Первичный ключ id")
    public Long id;

    @ApiModelProperty(value = "Название организации")
    public String name;

    @ApiModelProperty(value = "Активность")
    public Boolean isActive;

    public OrganizationFilterView() {
    }

    public OrganizationFilterView(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "Переопределенный toString()")
    @Override
    public String toString() {
        return "{id:" + id +
                ";name:" + name +
                ";isActive:" + isActive +
                "}";
    }
}

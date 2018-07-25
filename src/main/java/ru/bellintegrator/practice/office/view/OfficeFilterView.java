package ru.bellintegrator.practice.office.view;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Вид офиса для фильтра")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OfficeFilterView {

    @ApiModelProperty(hidden = true, value = "Первичный ключ id")
    public Long id;

    @ApiModelProperty(value = "Название офиса")
    public String name;

    @ApiModelProperty(value = "Активность")
    public Boolean isActive;

    @ApiModelProperty(value = "Переопределенный toString()")
    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";isActive:" + isActive + "}";
    }

}

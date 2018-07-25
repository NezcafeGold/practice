package ru.bellintegrator.practice.dictionary.country.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Страна")
public class CountryView {

    @ApiModelProperty(value = "Название страны")
    public String name;

    @ApiModelProperty(value = "Код страны")
    public String code;

    @ApiModelProperty(value = "Переопределенный toString()")
    @Override
    public String toString() {
        return "{name:" + name + ";code:" + code + "}";
    }
}

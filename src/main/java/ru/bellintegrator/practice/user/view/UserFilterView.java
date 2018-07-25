package ru.bellintegrator.practice.user.view;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Пользователь")
public class UserFilterView {

    @ApiModelProperty(hidden = true, value = "Первичный ключ id")
    public Long id;

    @ApiModelProperty(value = "Имя")
    public String firstName;

    @ApiModelProperty(value = "Фамилия")
    public String secondName;

    @ApiModelProperty(value = "Отчество")
    public String middleName;

    @ApiModelProperty(value = "Должность")
    public String position;

    @ApiModelProperty(value = "Переопределенный toString()")
    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ";firstName:" + firstName +
                ";secondName:" + secondName +
                ";middleName:" + middleName +
                ";position:" + position +
                "}";
    }
}

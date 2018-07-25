package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


@ApiModel(value = "Пользователь")
public class UserView {

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

    @ApiModelProperty(value = "Телефон")
    public String phone;

    @ApiModelProperty(value = "Код документа")
    public String docCode;

    @ApiModelProperty(value = "Название документа")
    public String docName;

    @ApiModelProperty(value = "Номер документа")
    public Long docNumber;

    @ApiModelProperty(value = "Дата выдачи документа")
    public Date docDate;

    @ApiModelProperty(value = "Страна гражданства")
    public String citizenshipName;

    @ApiModelProperty(value = "Код гражданства")
    public String citizenshipCode;

    @ApiModelProperty(value = "Идентификация")
    public Boolean isIdentified;

    @ApiModelProperty(value = "id офиса")
    public Long officeId;

    @ApiModelProperty(value = "Переопределенный toString()")
    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ";firstName:" + firstName +
                ";secondName:" + secondName +
                ";middleName:" + middleName +
                ";position:" + position +
                ";phone:" + phone +
                ";docCode:" + docCode +
                ";docName:" + docName +
                ";docNumber:" + docNumber +
                ";docDate:" + docDate +
                ";citizenshipName:" + citizenshipName +
                ";citizenshipCode:" + citizenshipCode +
                ";isIdentified:" + isIdentified +
                "}";
    }
}

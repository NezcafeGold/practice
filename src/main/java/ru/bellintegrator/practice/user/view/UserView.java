package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Пользователь
 */
public class UserView {

    /**
     * Первичный ключ id
     */
    @ApiModelProperty(hidden = true)
    public String id;

    /**
     * Имя
     */
    public String firstName;

    /**
     * Фамилия
     */
    public String secondName;

    /**
     * Отчество
     */
    public String middleName;

    /**
     * Должность
     */
    public String position;

    /**
     * Телефон
     */
    public String phone;

    /**
     * Название документа
     */
    public String docName;

    /**
     * Номер документа
     */
    public Long docNumber;

    /**
     * Дата выдачи документа
     */
    public Date docDate;

    /**
     * Страна гражданства
     */
    public String citizenshipName;

    /**
     * Код гражданства
     */
    public int citizenshipCode;

    /**
     * Идентификация
     */
    public boolean isIdentified;

    /**
     * Переопределенный toString()
     */
    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ";firstName:" + firstName +
                ";secondName:" + secondName +
                ";middleName:" + middleName +
                ";position:" + position +
                ";phone:" + phone +
                ";docName:" + docName +
                ";docNumber:" + docNumber +
                ";docDate:" + docDate +
                ";citizenshipName:" + citizenshipName +
                ";citizenshipCode:" + citizenshipCode +
                ";isIdentified:" + isIdentified +
                "}";
    }
}

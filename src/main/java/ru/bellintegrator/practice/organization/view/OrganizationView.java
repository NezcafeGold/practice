package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;

/**
 * Организация
 */
public class OrganizationView {

    /**
     * Первичный ключ
     */
    @ApiModelProperty(hidden = true)
    public String id;

    /**
     * Название организации
     */
    public String name;

    /**
     * Полное название организации
     */
    public String fullName;

    /**
     * ИНН
     */
    public String inn;

    /**
     * КПП
     */
    public String kpp;

    /**
     * Адрес
     */
    public String address;

    /**
     * Телефон
     */
    public String phone;

    /**
     * Активность
     */
    public boolean isActive;

    /**
     * Переопределенный toString()
     */
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

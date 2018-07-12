package ru.bellintegrator.practice.office.view;


import io.swagger.annotations.ApiModelProperty;

/**
 * Офис
 */
public class OfficeView {

    /**
     * Первичный ключ
     */
    @ApiModelProperty(hidden = true)
    public String id;

    /**
     * Название офиса
     */
    public String name;

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
        return "{id:" + id + ";name:" + name + ";address:" +address +";phone:" + phone + ";isActive:" + isActive + "}";
    }
}

package ru.bellintegrator.practice.dictionary.docType.view;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Документ")
public class DocTypeView {

    @ApiModelProperty(value = "Название документа")
    public String name;

    @ApiModelProperty(value = "Код документа")
    public int code;

    @ApiModelProperty(value = "Переопределенный toString()")
    @Override
    public String toString() {
        return "{name:" + name + ";code:" + code + "}";
    }
}

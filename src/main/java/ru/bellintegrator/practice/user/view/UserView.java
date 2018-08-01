package ru.bellintegrator.practice.user.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;


@ApiModel(value = "Пользователь")
public class UserView {

    @ApiModelProperty(value = "Первичный ключ id")
    public Long id;

    @ApiModelProperty(value = "Имя")
    public String firstName;

    @ApiModelProperty(value = "Фамилия")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String secondName;

    @ApiModelProperty(value = "Отчество")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String middleName;

    @ApiModelProperty(value = "Должность")
    public String position;

    @ApiModelProperty(value = "Телефон")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String phone;

    @ApiModelProperty(value = "Код документа")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String docCode;

    @ApiModelProperty(value = "Название документа")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String docName;

    @ApiModelProperty(value = "Номер документа")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String docNumber;

    @ApiModelProperty(value = "Дата выдачи документа")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String docDate;

    @ApiModelProperty(value = "Страна гражданства")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String citizenshipName;

    @ApiModelProperty(value = "Код гражданства")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String citizenshipCode;

    @ApiModelProperty(value = "Идентификация")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Boolean isIdentified;

    @ApiModelProperty(value = "id офиса")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long officeId;

    public UserView() {
    }

    public UserView(Long id, String firstName, String secondName, String middleName, String position, String phone,
                    String docCode, String docName, String docNumber, String docDate, String citizenshipCode,
                    Boolean isIdentified) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.docCode = docCode;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipCode = citizenshipCode;
        this.isIdentified = isIdentified;
    }

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

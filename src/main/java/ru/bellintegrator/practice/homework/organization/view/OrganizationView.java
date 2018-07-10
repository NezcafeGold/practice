package ru.bellintegrator.practice.homework.organization.view;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationView {

    @ApiModelProperty(hidden = true)
    public String id;

    String name;

    String inn;

    boolean isActive;
}

package ru.bellintegrator.practice.homework.organization.controller;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.homework.organization.model.Organization;
import ru.bellintegrator.practice.homework.organization.service.OrganizationService;
import ru.bellintegrator.practice.homework.organization.view.OrganizationView;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "addOrganization", nickname = "addOrganization", httpMethod = "POST")
    @GetMapping(value = "/organization/list")
    public List<Organization> addorganization(@RequestBody OrganizationView organization) {
        return organizationService.add(organization);
    }

    @ApiOperation(value = "getOrganizationById", nickname = "getOrganizationById", httpMethod = "GET")
    @GetMapping(value = "/organization/{id}")
    public Organization getOrganizationById(@PathVariable int id) {
        return organizationService.getOrganizationById(id);
    }

    @ApiOperation(value = "updateOrganization", nickname = "updateOrganization", httpMethod = "POST")
    @GetMapping(value = "/organization/update")
    public String updateOrganization(@RequestBody OrganizationView organization) {
        return organizationService.updateOrganization(organization);
    }

    @ApiOperation(value = "saveOrganization", nickname = "saveOrganization", httpMethod = "POST")
    @GetMapping(value = "/organization/save")
    public String saveOrganization(@RequestBody OrganizationView organization) {
        return organizationService.saveOrganization(organization);
    }


}

package ru.bellintegrator.practice.organization.controller;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "filterOrganization", nickname = "filterOrganization", httpMethod = "POST")
    @PostMapping(value = "/organization/list")
    public String filterOrganization(@RequestBody OrganizationView organization) {
        return organizationService.filterOrganization(organization);
    }

    @ApiOperation(value = "getOrganizationById", nickname = "getOrganizationById", httpMethod = "GET")
    @GetMapping(value = "/organization/{id}")
    public Organization getOrganizationById(@PathVariable int id) {
        return organizationService.getOrganizationById(id);
    }

    @ApiOperation(value = "updateOrganization", nickname = "updateOrganization", httpMethod = "POST")
    @PostMapping(value = "/organization/update")
    public void updateOrganization(@RequestBody OrganizationView organization) {
        organizationService.updateOrganization(organization);
    }

    @ApiOperation(value = "saveOrganization", nickname = "saveOrganization", httpMethod = "POST")
    @PostMapping(value = "/organization/save")
    public void saveOrganization(@RequestBody OrganizationView organization) {
        organizationService.saveOrganization(organization);
    }

}

package ru.bellintegrator.practice.homework.office.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.homework.office.model.Office;
import ru.bellintegrator.practice.homework.office.service.OfficeService;
import ru.bellintegrator.practice.homework.office.view.OfficeView;
import ru.bellintegrator.practice.homework.organization.view.OrganizationView;
import ru.bellintegrator.practice.homework.user.service.UserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class OfficeController {


    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "addOfficeById", nickname = "addOfficeById", httpMethod = "POST")
    @GetMapping(value = "/office/list/{orgId}")
    public String addOfficeById(@RequestBody OfficeView office, @PathVariable int orgId) {
        return officeService.add(orgId, office);
    }

    @ApiOperation(value = "getOfficeById", nickname = "getOfficeById", httpMethod = "GET")
    @GetMapping(value = "/office/{id}")
    public Office getOfficeById(@PathVariable int id) {
        return officeService.getOfficeById(id);

    }

    @ApiOperation(value = "updateOffice", nickname = "updateOffice", httpMethod = "POST")
    @GetMapping(value = "/office/update")
    public String updateOffice(@RequestBody OfficeView office) {
        return officeService.updateOffice(office);

    }

    @ApiOperation(value = "saveOrganization", nickname = "saveOrganization", httpMethod = "POST")
    @GetMapping(value = "/office/save")
    public String saveOffice(@RequestBody OfficeView office) {
        return officeService.saveOffice(office);
    }

}

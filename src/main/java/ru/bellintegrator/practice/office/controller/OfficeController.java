package ru.bellintegrator.practice.office.controller;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "filterOffice", nickname = "filterOffice", httpMethod = "POST")
    @PostMapping(value = "/office/list")
    public Office filterOffice(@RequestBody OfficeView office) {
        return officeService.filterOffice(office);
    }

    @ApiOperation(value = "getOfficeById", nickname = "getOfficeById", httpMethod = "GET")
    @GetMapping(value = "/office/{id}")
    public Office getOfficeById(@PathVariable int id) {
        return officeService.getOfficeById(id);
    }

    @ApiOperation(value = "updateOffice", nickname = "updateOffice", httpMethod = "POST")
    @PostMapping(value = "/office/update")
    public void updateOffice(@RequestBody OfficeView office) {
        officeService.updateOffice(office);
    }

    @ApiOperation(value = "saveOffice", nickname = "saveOffice", httpMethod = "POST")
    @PostMapping(value = "/office/save")
    public void saveOffice(@RequestBody OfficeView office) {
        officeService.saveOffice(office);
    }

}

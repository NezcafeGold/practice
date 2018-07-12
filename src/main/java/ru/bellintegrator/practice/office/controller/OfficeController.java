package ru.bellintegrator.practice.office.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @ApiOperation(value = "addOffice", nickname = "addOffice", httpMethod = "POST")
    @PostMapping(value = "/office/list/{orgId}")
    public String addOffice(@RequestBody OfficeView office, @PathVariable int orgId) {
        return officeService.addOffice(orgId, office);
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

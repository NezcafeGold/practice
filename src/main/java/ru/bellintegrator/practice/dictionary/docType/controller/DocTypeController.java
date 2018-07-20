package ru.bellintegrator.practice.dictionary.docType.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dictionary.docType.service.DocTypeService;
import ru.bellintegrator.practice.dictionary.docType.view.DocTypeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class DocTypeController {

    private final DocTypeService docTypeService;

    @Autowired
    public DocTypeController(DocTypeService docsService) {
        this.docTypeService = docsService;
    }

    @ApiOperation(value = "getDocTypes", nickname = "getDocTypes", httpMethod = "GET")
    @PostMapping(value = "/docs")
    public List<DocTypeView> getDocTypes() {
        return docTypeService.getDocTypes();
    }
}

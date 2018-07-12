package ru.bellintegrator.practice.doc.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.doc.service.DocService;
import ru.bellintegrator.practice.doc.view.DocView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class DocController {

    private final DocService docsService;

    @Autowired
    public DocController(DocService docsService) {
        this.docsService = docsService;
    }

    @ApiOperation(value = "updateDocs", nickname = "updateDocs", httpMethod = "POST")
    @PostMapping(value = "/docs")
    public void updateDocs(@RequestBody DocView docsView) {
        docsService.updateDoc(docsView);
    }
}

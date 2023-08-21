package org.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.web.model.CsvData;
import org.web.service.WebDataExtractService;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;


@Controller
public class WebDataExtractController {

    private final WebDataExtractService webDataExtractService;

    public WebDataExtractController(WebDataExtractService webDataExtractService) {
        this.webDataExtractService = webDataExtractService;
    }

    @GetMapping("/extract")
    public String extractData(Model model) throws IOException {

       Collection<CsvData> csvDataCollection =this.webDataExtractService.validateAndExtractData();
       model.addAttribute("csvDataList", csvDataCollection);
       return "csv-template";

    }
}

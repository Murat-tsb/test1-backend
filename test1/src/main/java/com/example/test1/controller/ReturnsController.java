package com.example.test1.controller;

import com.example.test1.dto.ReturnDTO;
import com.example.test1.model.Returns;
import com.example.test1.service.ReturnsService;
import org.apache.coyote.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/returns")
public class ReturnsController {
    private final ReturnsService returnsService;

    public ReturnsController(ReturnsService returnsService) {
        this.returnsService = returnsService;
    }

    @PostMapping("/")
    public void create (@RequestBody Returns returns){
        returnsService.create(returns);
    }

    @GetMapping("/")
    public ResponseEntity<List<Returns>> getAll(){
           return new ResponseEntity<>(returnsService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-date-company")
    public ResponseEntity<List<ReturnDTO>> getByDateAndCompany(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate,
                                                               @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate,
                                                               @RequestParam(defaultValue = "null")String companyName){

        LocalDate trueEndDate = LocalDate.now();
        if (Objects.nonNull(endDate)){
            trueEndDate = endDate;}

        String validCompanyName = (companyName != null && !companyName.isEmpty()) ? companyName : null;
        return new ResponseEntity<>(returnsService.getByDateAndCompany(startDate, trueEndDate, validCompanyName), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        returnsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

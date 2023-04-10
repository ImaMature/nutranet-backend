package com.nutranet.Controller;

import com.nutranet.Service.FoodDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    FoodDataService foodDataService;

    @GetMapping ("/api/hello")
    public String test(){
        return "hi, hello!!";
    }

    @GetMapping ({"","/"})
    public ResponseEntity<?> Login(){
        if(foodDataService.getSavedData().isEmpty()){
            foodDataService.saveNewData();
        };
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }




}

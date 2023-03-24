package com.nutranet.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {



    @GetMapping ("/api/hello")
    public String test(){
        return "hi, hello!!";
    }

    @GetMapping ({"","/"})
    public ResponseEntity<?> Login(){
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }
}

package com.nutranet.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping ({"","/"})
    public String main(){
        return "/api/hello";
    }

    @GetMapping ("/api/hello")
    public String test(){
        return "hi, hello!!";
    }
}

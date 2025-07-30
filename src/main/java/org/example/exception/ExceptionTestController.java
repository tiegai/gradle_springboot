package org.example.exception;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionTestController {


    @RequestMapping("/modelMap")
    public String home(ModelMap modelMap) {
        return modelMap.toString();
    }

//或者 通过@ModelAttribute获取

    @RequestMapping("/modelAttribute")
    public String home(@ModelAttribute("author") String author) {
        return author;
    }

    @RequestMapping("/exception")
    public String exception() throws Exception {
        throw new Exception("400");
    }

    @RequestMapping("/myException")
    public String myException() {
        throw new MyException("500", "my exception");
    }


}

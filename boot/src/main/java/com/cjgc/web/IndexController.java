package com.cjgc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shiny
 * @since 2016/9/2 15:30
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "Hello springBoot";
    }

}

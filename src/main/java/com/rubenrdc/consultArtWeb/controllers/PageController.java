package com.rubenrdc.consultArtWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Ruben
 */
@Controller
public class PageController {
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}

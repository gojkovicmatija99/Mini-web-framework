package app.impl;

import framework.annotations.spring.Autowired;
import framework.annotations.spring.Controller;

@Controller
public class CustomController {

    @Autowired(verbose = false)
    CustomService serviceTest;
}

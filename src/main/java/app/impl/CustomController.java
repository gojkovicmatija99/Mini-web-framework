package app.impl;

import framework.annotations.spring.Autowired;
import framework.annotations.spring.Controller;

@Controller
public class CustomController {

    @Autowired(verbose = true)
    CustomService serviceTest;
}

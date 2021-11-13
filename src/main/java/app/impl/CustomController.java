package app.impl;

import framework.annotations.http.GET;
import framework.annotations.http.Path;
import framework.annotations.spring.Autowired;
import framework.annotations.spring.Controller;

@Controller
public class CustomController {

    @Autowired(verbose = true)
    CustomService serviceTest;

    @GET
    @Path("app/getMethod")
    public String getMethod() {
        return serviceTest.getResource();
    }
}

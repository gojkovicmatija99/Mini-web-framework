package app;

import framework.IoC.DependencyInjectionEngine;
import app.impl.CustomController;
import framework.exceptions.DataAccessException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, DataAccessException {
        DependencyInjectionEngine dependencyInjectionEngine = new DependencyInjectionEngine("app");
        CustomController controller = (CustomController) dependencyInjectionEngine.inject(CustomController.class);
    }


}

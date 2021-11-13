package app.impl;

import app.Repository;
import framework.annotations.spring.Component;
import framework.annotations.spring.Qualifier;

@Qualifier("customRepository")
@Component
public class CustomRepository implements Repository {

    @Override
    public String get() {
        System.out.println("Simulating DB get");
        return "This resource is fetched from DB";
    }

    @Override
    public boolean update() {
        System.out.println("Simulating DB update");
        return true;
    }

    @Override
    public boolean delete() {
        System.out.println("Simulating DB delete");
        return true;
    }
}

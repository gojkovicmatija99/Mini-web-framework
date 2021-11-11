package app.impl;

import app.Repository;
import framework.annotations.spring.Component;
import framework.annotations.spring.Qualifier;

@Qualifier("customRepository")
@Component
public class CustomRepository implements Repository {

    @Override
    public String get() {
        return null;
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public boolean delete() {
        return true;
    }
}

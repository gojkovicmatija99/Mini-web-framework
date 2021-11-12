package app.impl;

import app.Repository;
import framework.annotations.spring.Autowired;
import framework.annotations.spring.Qualifier;
import framework.annotations.spring.Service;

@Service
public class CustomService {

    @Qualifier("customRepository")
    @Autowired(verbose = true)
    private Repository repository;

    public String getResource() {
        return repository.get();
    }

    public boolean updateResource() {
        return repository.update();
    }

    public boolean deleteResource() {
        return repository.delete();
    }
}

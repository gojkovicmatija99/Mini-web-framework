package framework.IoC;

import framework.annotations.spring.Bean;
import framework.annotations.spring.Component;
import framework.annotations.spring.Controller;
import framework.annotations.spring.Service;
import framework.exceptions.DataAccessException;

import java.util.Map;

public class DependencyInjectionEngine {

    private final DependencyContainer dependencyContainer;

    private final BeanFactory beanFactory;

    public DependencyInjectionEngine(String packageName) {
        this.beanFactory = new BeanFactory(this);
        Map<String, Class> qualifiers = QualifierLoader.findAllClassesWithQualifier(packageName);
        this.dependencyContainer = new DependencyContainer(qualifiers);
    }

    public Object inject(Class clazz) throws InstantiationException, IllegalAccessException, DataAccessException {
        if (isClassInjectable(clazz)) {
            return getOrCreteBean(clazz);
        } else {
            throw new DataAccessException("Class can't be injected: " + clazz.getName());
        }
    }

    private boolean isClassInjectable(Class clazz) {
        return clazz.isAnnotationPresent(Component.class) ||
            clazz.isAnnotationPresent(Service.class) ||
            clazz.isAnnotationPresent(Controller.class) ||
            clazz.isAnnotationPresent(Bean.class);
    }

    private Object getOrCreteBean(Class clazz) throws InstantiationException, IllegalAccessException, DataAccessException {
        if (dependencyContainer.contains(clazz)) {
            return dependencyContainer.getBean(clazz);
        } else {
            return beanFactory.createBean(clazz);
        }
    }

    public DependencyContainer getDependencyContainer() {
        return dependencyContainer;
    }
}
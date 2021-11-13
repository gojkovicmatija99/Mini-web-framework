package framework;

import framework.IoC.BeanFactory;
import framework.IoC.DependencyContainer;
import framework.IoC.DependencyInjectionEngine;
import framework.IoC.DiscoveryUtil;
import framework.exceptions.DataAccessException;

import java.util.List;
import java.util.Map;

public class Framework {

    private static Framework instance;

    private DependencyInjectionEngine dependencyInjectionEngine;

    private DependencyContainer dependencyContainer;

    private BeanFactory beanFactory;

    private Framework() {

    }

    public static Framework getInstance() {
        if (instance == null) {
            instance = new Framework();
        }
        return instance;
    }

    public void init(String packageName) {
        try {
            this.beanFactory = new BeanFactory();
            this.dependencyInjectionEngine = new DependencyInjectionEngine();
            Map<String, Class> qualifiers = DiscoveryUtil.discoverQualifiers(packageName);
            this.dependencyContainer = new DependencyContainer(qualifiers);
            registerAllControllers(packageName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerAllControllers(String packageName) throws DataAccessException, InstantiationException, IllegalAccessException {
        List<Class> controllers = DiscoveryUtil.discoverControllers(packageName);
        for(Class clazz:controllers) {
            dependencyInjectionEngine.inject(clazz);
        }
    }


    public DependencyContainer getDependencyContainer() {
        return dependencyContainer;
    }

    public DependencyInjectionEngine getDependencyInjectionEngine() {
        return dependencyInjectionEngine;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}

package framework.IoC;

import framework.annotations.spring.Qualifier;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QualifierLoader {

    public static Map<String, Class> findAllClassesWithQualifier(String packageName) {
        List<Class> classes = findAllClassesUsingClassLoader(packageName);
        return classes.stream()
                      .filter(clazz -> clazz.isAnnotationPresent(Qualifier.class))
                      .collect(Collectors.toMap(clazz -> ((Qualifier) clazz.getAnnotation(Qualifier.class)).value(), Class::getClass));
    }

    private static List<Class> findAllClassesUsingClassLoader(String packageName) {
        List<Class> classes = new ArrayList();
        InputStream stream = ClassLoader.getSystemClassLoader()
                                        .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        reader.lines().forEach(curr -> {
                                   if (curr.endsWith(".class")) {
                                       classes.add(getClass(curr, packageName));
                                   } else {
                                       classes.addAll(findAllClassesUsingClassLoader(packageName + "." + curr));
                                   }
                               }
        );
        return classes;
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                                     + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}

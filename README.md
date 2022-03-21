# Mini-web-framework
## Overview
The task is to create a web framework modeled like JAX-rs / Spring. The framework needs to be based on controllers that contain methods for processing incoming requests and creating JSON responses. In addition, it is necessary to support dependency injection at the controller level.
It is expected that a programmer can define available routes in his application by annotating controller methods with an @Path annotation that requires a required parameter - path. In addition, it is necessary to provide the developer with a way to define an HTTP method that corresponds to the previously mentioned route - @GET or @POST. For example, if we have a controller method labeled @Path (“/ test”) and @GET, this means that when a client makes a GET HTTP request to a path / test, it will run the specified controller method which will be in charge of returning a response to the client.
When instantiating a controller, it is necessary to initialize its attributes using dependency injection.

Feature:
- Bean factory, Dependency Container and Dependency Injection Engine
- Recursive dependency injection
- Framework based on HTTP and REST, can be tested via Postman or browser
- Implemented custom annotations like @Bean, @Controller, @Service, @Repository, @Autowire, @Qualifier, @POST and @GET

## Requirements
### Route registration
It is necessary to create an annotation @Controller which can annotate the class (controller) and which will be a sign that this class contains methods that handle client requests. The controller method will represent an action that is tied to one route, and it is necessary to specify the HTTP method and the route for which that action (method) will be performed. In order to achieve this, two annotations are needed that will describe this method of the controller in more detail:
@ GET / @ POST - Annotation indicating which possible HTTP methods will be one of the conditions for activating the controller method. These annotations are without arguments.
@Path an annotation that receives only one argument - the path to which the controller method will be activated.
 After that, it is necessary to go through all the methods of these classes and based on the annotations @ GET / @ POST and @Path, globally map the route (as a combination of HTTP method and path) to the controller and the method that will be activated when calling that route. It is necessary to ensure that the framework instantiates controllers only once, and that it already has a ready instance of controllers for each new request.
### Dependency injection
The idea is to achieve inversion of control (IOC) starting from the controller attribute. The logic that will initialize the controllers and all their dependencies needs to be separated into a separate class - DI Engine.
Dependency initialization will occur on controller attributes annotated with @Autowired. Also, there is a possibility that the dependency itself has attributes (other dependencies) annotated with @Autowired. In that case, we will have to solve them recursively - starting from the bottom of the tree.
We have two instances of initializing attributes annotated with @Autowired.

#### Dependency Container
When initializing a dependency, the specific type to be instantiated may not be known because the interface was used. Dependency Container is a structure in which we will be able to register a specific implementation (class) for an interface.
The Dependency Container throws an exception if some type is not specified, and is required from the container.

#### DI Engine
DI Engine is in charge of using reflection to initialize all annotated dependencies recursively starting from the controller.
DI Engine is in charge of having a list of all instances annotated with @Bean (scope = "singleton") or @Service. To initialize them only once during application execution and use them with each injection.
DI Engine is in charge of first injecting during the initialization of a class, ie. initializes all its attributes using recursion.
DI Engine is in charge of consulting the Dependency Container, every time it encounters an interface instead of a specific class. From the container, it uses Qualifier to get a class that represents the implementation and uses it to initialize the attribute itself.

## Example
An user wants to a webstore and he starts by implementing features for product and order. When he creates the controller, service and repository and starts the app, the framework will scan all his created classes, inject dependencies and register routes.

![alt text](https://github.com/gojkovicmatija99/Mini-web-framework/blob/master/demo.png)

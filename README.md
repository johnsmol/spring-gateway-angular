# spring-gateway-angular

Implementation of an Angular app served behind Spring Cloud Gateway


## How to run

The current configuration of the projects is based on the presence of two entries in your pc hosts file:
- ```gateway 127.0.0.1```
- ```frontend 127.0.0.1```

Once everything is up and running you can visit ```http://gateway:8000``` to access the Angular frontend.
The web app only display two basic buttons to trigger http calls to the backend: one GET and one POST.


### Backend

You can simply run both the gateway and the resource service from you IDE. No configuration required.
Spring Cloud Gateway will run on port 8000.
Resource service will run on port 8080.


### Frontend

Run ```npm start``` command.
This will start the application with host ```frontend``` and port ```4200```.
It also will load the ```proxy.conf.json``` necessary to reach the backend without CORS blocking the requests.

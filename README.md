To build the services: clone service-shuffle and service-log reporsitories and make sure they are in one project


Open terminal and run in each project directory (service-shuffle/ and service-log/): mvn clean install


To run the services: 


Start service-log first


This service listens on port 8081 (as per application.yml):


cd service-log


mvn spring-boot:run


Then start service-shuffle


This service listens on port 8080 and needs service-log running:


cd ../service-shuffle


mvn spring-boot:run


Test the API


Send a POST request to service-shuffle like this:


curl -X POST http://localhost:8080/shuffle -H "Content-Type: application/json" -d '{"number":5}'


you’ll get a shuffled array as a response, and in the service-log console, you’ll see a log like this:



[LOG SERVICE] Request received: {number=5}

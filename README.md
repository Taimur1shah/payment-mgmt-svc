# payment-mgmt-svc
Repository for Performing fee transaction and generating Receipt microservice.

## Techonology Stack
1. Java 17
2. Spring boot 3.2.5
3. Gradle
4. gradle-7.5
5. H2 db
6. Openapitools 
7. openapi: 3.0.3

## Steps to run the application
1. This microservice need to be run third after Student and Fee Services are up 
2.Compile the Project using gradle build by running command in cmd

   ./gradlew clean build

   API first approach has been used ,so OpenAPI tool will create the OrderApi controller interface stub
   payment-api --> build -->classes --> com.skiply.payment.api --> PaymentApi
2. Run the application , it will start on port 8082
3. Import the Postman collection and execute the curl requests
4. H2 db console can be connected in browser
   JDBC URL : jdbc:h2:mem:paymentdb
   userName : sa
5. OpenAPI Spec file is located at
   payment-mgmt-svc\payment-api\src\main\resources/payment-openapi-spec-file.yaml
6. Performance, Scalability, and Reliability Targets
      health checks and readiness
      http://localhost:8082/actuator/metrics
      http://localhost:8082/actuator/health/readiness
      http://localhost:8082/actuator/health/liveness

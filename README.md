# payment-mgmt-svc
Repository for Payment of Fee and generating Receipt microservice.

Steps
1. This microservice need to be run third
2. Compile the Project using gradle build
   API first approach has been used ,so OpenAPI tool will create the OrderApi controller interface stub
   payment-api --> build -->classes --> com.skiply.order.api --> FeeApi
3. Run the application , it will start on port 8082
4. Import the Postman collection and execute the curl requests
5. H2 db console can be connected in browser
   JDBC URL : jdbc:h2:mem:paymentdb
   userName : sa
6. OpenAPI Spec file is located at
   payment-mgmt-svc\payment-api\src\main\resources/payment-openapi-spec-file.yaml

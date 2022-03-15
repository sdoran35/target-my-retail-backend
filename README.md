# myRetail REST API Case Study

## Software Stack
* [Java 1.8](https://java.com/en/download/)
* [MongoDB 5.0.6](https://www.mongodb.com/)
* [Spring Boot 2.6.4](https://projects.spring.io/spring-boot/) with [Tomcat](https://tomcat.apache.org/) Embedded
* [JUnit 4](http://junit.org/junit4/)
* [Maven 3.5.0](https://maven.apache.org/)
* [Git](https://git-scm.com/)

## MYRETAIL APP
MyRetail App is configured to run as a Spring Boot application. It is configured to run at http://localhost:8080/v1/api/products/

### Hosted Access

The App is hosted at https://floating-woodland-99915.herokuapp.com/

This uses:
* Heroku Java/Tomcat 
* Papertail for logging
* Connection to a MongoDB Atlas cluster


## To start the application

### Setup

#### 1. Verify Java
Open your terminal/command prompt and enter the following command
```
java -version
```
If your java version is 1.8 or higher, then continue below. Otherwise update it to the most recent JDK

Verify your version again.
```
java -version
```
#### 2. Clone the project from Git.
If you do not have git installed, [click here.](https://git-scm.com/downloads) 

If you're unsure, open your terminal and type in
```
git --version
```

Once git is installed, clone the project from git

In your terminal, run the following command
```
$ git clone https://github.com/sdoran35/target-my-retail-backend
```
This will clone the repo to your local machine

#### 3. Install MongoDB
This step is not needed if using the Heroku hosted version

If you don't have mongoDB installed, refer [here for windows](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/) and [here for mac](https://treehouse.github.io/installation-guides/mac/mongo-mac.html)

Once installed, enter the following command in your terminal
```
mongod
```
This will start the Mongo server.

Open up a new terminal and type the following command
```
mongo
```

Once Mongo has started, create a new database with the following command
```
use demo
```

And then enter the following command to add an entry in the database.

This will also create a new Mongo collection called "Product"
```
db.getCollection('Product').save({ "productId": 13860428, "title": "The Big Lebowski (Blu-ray)", "price": "35.99", "currencyCode": "USD" })
```

#### 4. Build Project using Maven
This step not needed if using the Heroku hosted version

If you do not have maven installed, you can install it [here.](https://maven.apache.org/download.cgi)

If you're unsure, enter this command in your terminal
```
mvn -version
```

Once maven is installed, build the project. (Make sure you are in the cloned folder)
```
mvn clean install -U
```

### Run the application
This step not needed if using the heroku hosted version.

After maven has finished building and was successful, you'll now have a target folder in your project folder.
```
cd target
```
Run the following command to start the application
```
java -jar myretail-0.0.1-SNAPSHOT.jar
```

The application has now started.

#### 1. Run the RESTful services
Add [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en) extension for Chrome or any other REST Client

Launch Postman, or other REST Client

1) This REST Service aggregates price information from MongoDB and product Title information from the external Target RedSky API and provides a JSON Response of the product information.
```
  GET http://localhost:8080/products/13860428
  Content-Type: application/json
```

**Response:**
```
{
	"id": 13860428,
	"title": "The Big Lebowski (Blu-ray)",
	"productPrice": {
		"price": 69.87,
		"CurrencyCode": "USD"
	}
}
```

2) This Rest Service is used to update the price of an existing product in MongoDB
```
PUT http://localhost:8080/products/13860428
Content-Type: application/json
```
**Request:**
```
   {
	"id": 13860428,
	"title": "The Big Lebowski (Blu-ray)",
	"productPrice": {
		"price": "11.99",
		"currencyCode": "USD"
	}
}
```
We have now updated the price of the product, and saved our changes to the MongoDB collection.

**Response:**
```
{
	"id": 13860428,
	"title": "The Big Lebowski (Blu-ray)",
	"productPrice": {
		"price": 11.99,
		"currencyCode": "USD"
	}
}
```

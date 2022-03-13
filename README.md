# myRetail RESTful service Case Assessment

## Software Stack
* [Java 1.8](https://java.com/en/download/)
* [MongoDB 3.4.6](https://www.mongodb.com/)
* [Spring Boot](https://projects.spring.io/spring-boot/) with [Tomcat](https://tomcat.apache.org/) Embedded
* [JUnit 4](http://junit.org/junit4/)
* [Maven 3.5.0](https://maven.apache.org/)
* [Git](https://git-scm.com/)

## MYRETAIL APP
MyRetail App is configured to run as a Spring Boot application. It is configured to run at http://localhost:8080/product/

### Hosted Access

The App is hosted at https://floating-woodland-99915.herokuapp.com/
This uses:
* Heroku Java/Tomcat 
* Papertail for logging
* Connection to a MongoDB Atlas cluster


### To start the application

### 1. Verify Java
Open your terminal/command prompt and enter the following command
```
java -version
```
If your java version is 1.8 or higher, then continue below. Otherwise update it to the most recent JDK


Verify your verison again.
```
java -version
```
### 2. Clone the project from Git.
If you do not have git installed, [click here.](https://git-scm.com/downloads) If you're unsure, open your terminal/command prompt and type in
```
git --version
```

If on a mac using homebrew, you can type the following command to install git
```
brew install git
```

Once git is installed, clone the project from git

In terminal/command prompt, run-
```
$ git clone https://github.com/tkloetzk/target
```
This will install the directory on your home folder


### 3. Install MongoDB
This step is not needed if using the Heroku hosted version

If you don't have mongoDB installed, refer [here for windows](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/) and [here for mac](https://treehouse.github.io/installation-guides/mac/mongo-mac.html)

Once installed, enter the following command in your terminal/command prompt
```
mongod
```
This will start the Mongo server.

Open up a new terminal/command prompt and type the following command
```
mongo
```

Once Mongo has started, create a new database with the following command
```
use demo
```

And then enter the following command to add an entry in the database
```
db.getCollection('Product').save({ "productId": 13860428, "title": "The Big Lebowski (Blu-ray)", "price": "69.87", "currencyCode": "USD" })
```

### 4. Build Project using Maven
This step not needed if using the heroku hosted verison

If you do not have maven installed, you can install it [here.](https://maven.apache.org/download.cgi)

If you're unsure, enter this command in your terminal/command prompt
```
mvn -version
```

Once maven is installed, build the project. (Make sure you are in the folder (ex: /User/Folder/Target))
```
mvn clean install -U
```

### 5. Run the application
This step not needed if using the heroku hosted version.

After maven has finished building and was successful, you'll now have a target folder in your project folder. Navigate inside this folder using terminal/command prompt
```
cd target
```
Run the following command to start the application
```
java -jar myretail-0.0.1-SNAPSHOT.jar
```

The application has now started.

### 6. Run the RESTful services
Add [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en) extension for Chrome or use your favorite API Rest Client

Launch Postman (or your own rest client)

1) This Rest Service aggregates price information from MongoDB and product Title from external Target API and provides a JSON Response.
```
  GET   http://localhost:8080/products/13860428
  Content-Type: application/json
```

Response
```
{
	"id": 13860428,
	"name": "The Big Lebowski (Blu-ray)",
	"current_price": {
		"value": 69.87,
		"currency_code": "USD"
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
	"name": "The Big Lebowski (Blu-ray)",
	"product_price": {
		"value": "11.99",
		"currency_code": "USD"
	}
}
```
The product price has now been updated.

Response
```
{
	"id": 13860428,
	"name": "The Big Lebowski (Blu-ray)",
	"current_price": {
		"value": 11.99,
		"currency_code": "USD"
	}
}
```

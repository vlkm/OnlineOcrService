## Online OCR Service project JAVA with Spring, (Online Image File Upload and Convert to Text Example)

This article shows you how to create Spring Boot Online Service for uploading an Image, for converting to text.


## Steps to Setup

**1. Clone the repository** 

```bash
git clone https://github.com/vlkm/OnlineOcrService.git
```

**2. Run the app using maven**

```bash
open your terminal then type this command :
goto dir "cd OnlineOcrService"
\onlineocrservice> mvn clean spring-boot:run
```
**3. Open your browser:**

That's it! The application can be accessed at `http://localhost:8180`.

**.. to specify port:**
open `src/main/resources/application.properties` file and change the property `server.port=8180` to any other you want to open.


You may also package the application in the form of a jar and then run the jar file like so -

```bash
mvn clean package
java -jar target/OnlineOcrService-0.0.1-SNAPSHOT.war 

```

Tools used :

1. Spring Boot 1.5
2. Spring 4
3. Tesseract 4
4. Maven
5. Tomcat
6. Thymeleaf

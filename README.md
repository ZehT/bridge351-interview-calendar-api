# Bridge351 Interview Calendar API
Project for a possible interview with the current coding team!

## About the Project
The Interview Calendar API consists in a single API of two services:
- User Service
- Slot Service

There are 3 Users that are "shipped" with in memory database:
- Carl: As Candidate
- David and Ines: As Interviewers

They also have some Slots already available.

More Users and Slots can be add using the API.

## Database
The Database is fairly simple, and described as below:
> ![DB Structure](https://github.com/ZehT/bridge351-interview-calendar-api/blob/main/src/main/resources/db/db_structure.png?raw=true)

## Executing the Project
### Pre-Requirements
Before checking out the project, be sure to have:
- Java 11 (11.0.12)
- GIT (command line or a UI as SourceTree for example)
- The IDE that you love
- Maven (3.8.4) or the one bundled with or IDE (double-check the version ;) )

### Optional
- Docker Windows / Linux

### How to Run
First things first: clone the project. Use command line or your Git UI.
- ```git clone https://github.com/ZehT/bridge351-interview-calendar-api.git ```

Since the project is already configured to be executed in a Maven profile, all you have to do is run the project. But first make sure you have the 8080 port not in use. After that you can run as:

#### IDE
Import the project in your loved IDE, update dependencies with ``` mvn clean install ``` in the terminal or with the help of the IDE's UI, and run the InterviewCalendarApiApplication class

#### Command Line
Run through command line in the root directory where you've check out the project: ``` mvn spring-boot:run ```

#### Docker
Docker is the optional way. You must have it installed in your machine. If you have, build the image and run:

- build: ```docker build -t interview-calendar-api .```
- run: ```docker run -p 8080:8080 interview-calendar-api```  
 
_Note: check Useful Links bellow to see the API url's._

## Useful Links
- swagger: <code> http://localhost:8080/interview-calendar-api/swagger-ui.html </code>
- h2: <code> http://localhost:8080/interview-calendar-api/h2 </code>

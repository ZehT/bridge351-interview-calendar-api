# Bridge351 Interview Calendar API
Project for a possible interview with the current coding team!

## About the Project
The Interview Calendar API consists in single API of two services:
- User Service
- Slot Service

There is 3 users that are "shipped" with in memory database:
- Carl: As Candidate
- David and Ines: As Interviewers

More Users can be add using the POST defined in User Service

## Data Base
The Data Base is fairly simple, and described as below:
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

Since the project is already configured to execute in a default Maven profile, all you have to do is run the project. But first make sure you have the 8080 port not in use first. After that you can:

#### IDE
Import the project in your loved IDE and then running the InterviewCalendarApiApplication class

#### Command Line
Run through command line in the root directory where you've check out the project: ``` mvn spring-boot:run ```

#### Docker
Docker is the optional way, you must have it installed in your machine. If you have, you build the image and run:

- build: ```docker build -t interview-calendar-api .```
- run: ```docker run -p 8080:8080 interview-calendar-api```  
 
_Note: check Useful Links bellow to see the API url's._

## Useful Links
- swagger: <code> http://localhost:8080/interview-calendar-api/swagger-ui.html </code>
- h2: <code> http://localhost:8080/interview-calendar-api/h2 </code>

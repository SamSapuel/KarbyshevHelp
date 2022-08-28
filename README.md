# KarbyshevHelp
<b>Welcome to the KarbyshevHelp project page!</b> 
<br>Project customer - computer repair company Karbyshev Help, that needed to update its backend.</br>
## Our team
Dmitriy Shevchenko &
Andrew Shevtsov
<br>Every team member was responsible for almost same issues.</br>
## Technology stack
<br>We use <b>microservice architecture</b> as the main architecture in our application. Microservices communicate with each other using http requests</br>
<br>[x] Database: MongoDB</br>
<br>[x] Messaging: Kafka</br>
<br>[x] Deployment occurs through Docker</br>
## How to run
- First of all you need to run docker containers by using command "docker compose up docker-compose.yaml"
- Building a Spring Boot project with Maven "mvn clean install -U"
- You need to run every module of our application. You can do it in your IDE or in command line by using "mvn spring-boot:run"

<br>After all of this steps you have access to database on "localhost:8081" with logpass "rootuser:rootpass"</br>


You can test all the functionality in postman or in a simmilar program

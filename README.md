# MusicCart
# Introduction
* This project is based on a web application that provides a user-friendly interface, provide secure login and registrations to users with Javascript validation and JavaMail API.
The Java Mail API is for user authentication where the server sends an OTP to the user's email and verifies the OTP and also checks whether the mail id is present in the database
or not. And once the user credentials are verified it directs to the MusicCart home page which consists of list of songs which users can listen to and also add it to their playlist.
* The session of the user is maintained using HTTP Cookies. So, when the user logsout the cookies are destroyed.
# Dependencies
* Java, MySQL or OracleDB, JavaScript
# Usage
* The project is compatible with JavaSE-1.8 or above and makes use of javax.mail and javax.mail.internet packages from JavaMail API.
* You need to explicitly download and add servlet-api.jar, mysql-connector-java.jar, mail.jar and activation.jar files to the path if you use Eclipse IDE.
* No need of explicit Deployment Descriptor file(web.xml) as Eclipse handles it internally if you do not create it at the time of project creation.
* MusicCart.html is the index file i.e., welcome file for the project.
* As this project is not using realtime database like firebase, you need to create a database and add user table and cart table.
* The user table maintains details of users registered to the website and the cart table cosists of the user's playlist.
* Web Server that is being used is Apache Tomcat Web Server

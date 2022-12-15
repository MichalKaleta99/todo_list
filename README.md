# To-do list application

Simple to-do list application which I believe every junior programmer has in his portfolio right now.

# How to run?

Since my application uses MySQL database before running it you have to create the database first.

You can do it with docker command:

> docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 mysql:8-oracle

or simply create an DB with parameters like those: (feel free to configure them at your wish in ** application.properties ** file)
> url = localhost:3306/todos

> username = todos-user

> password = dummytodos

> DB = todos

# Techstack

Spring Framework 6,
Spring Boot 3,
Hibernate,
MySQL,
CSS Bootstrap

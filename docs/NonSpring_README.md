# NonSpringApplication

```
$ mvn clean
$ mvn verify -DskipTests=true
$ java -jar target/NonSpringApplication-jar-with-dependencies.jar
    or
$ mvn exec:java
```

docker利用時
```
$ sudo docker build -t tokuo-sand:0.1 ./
$ sudo docker run --name tokuo-sand --rm -it -p 8080:80 tokuo-sand:0.1
```

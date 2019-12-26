FROM centos:7

RUN yum update -y \
  && yum install -y java-1.8.0-openjdk-devel java-1.8.0-openjdk \
  && yum install -y maven \
  && mkdir -p ./tokuo-sand-non-spring

ADD tokuo-sand-non-spring/ ./tokuo-sand-non-spring

RUN mvn -f ./tokuo-sand-non-spring/pom.xml verify
#ENTRYPOINT ["ls"]
#CMD ["ls"]

FROM centos:7

RUN yum update -y \
  && yum install -y java-1.8.0-openjdk-devel java-1.8.0-openjdk \
  && curl -OL https://archive.apache.org/dist/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.tar.gz \
  && tar -xzvf apache-maven-3.6.1-bin.tar.gz \
  && mv apache-maven-3.6.1 /opt/ \
  # 以下でも問題ないようにも思えるが、/opt用の設定ファイルは/etc/optが利用されていたりするので、mavenの配置場所は大人しく公式に通り/opt配下にする
  # && mv apache-maven-3.6.1 /usr/local/
  && mkdir -p ./tokuo-sand-non-spring

ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk
ENV M2_HOME /opt/apache-maven-3.6.1
# ENV M2_HOME /usr/local/apache-maven-3.6.1
ENV PATH ${PATH}:${M2_HOME}/bin

ADD tokuo-sand-non-spring/ ./tokuo-sand-non-spring

RUN mvn -f ./tokuo-sand-non-spring/pom.xml verify
#ENTRYPOINT ["ls"]
#CMD ["ls"]

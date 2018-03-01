FROM ubuntu:16.04

ENV GLASSFISH_PKG=latest-glassfish.zip \
    GLASSFISH_URL=https://download.oracle.com/glassfish/5.0/nightly/latest-glassfish.zip \
    GLASSFISH_HOME=/glassfish5 \
    PATH=$PATH:/glassfish5/bin \
    JAVA_HOME=/usr/lib/jvm/java-openjdk

RUN apt-get -y update && apt-get -y install unzip openjdk-8-jdk curl && \
    curl -O $GLASSFISH_URL && \
    unzip -o $GLASSFISH_PKG && \
    rm -f $GLASSFISH_PKG && \
    apt-get -y remove unzip && \
    apt-get -y clean

ENV user=glassfish \
    group=glassfish_grp 

RUN addgroup ${group} && \
    adduser --disabled-login --system ${user} && \
    usermod -a -G ${group} ${user} && \
    chown -R ${user}:${group} ${GLASSFISH_HOME} && \
    chmod -R 777 ${GLASSFISH_HOME}

COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

USER glassfish

ENTRYPOINT ["/docker-entrypoint.sh"]

EXPOSE 4848 8080 8181

CMD ["asadmin", "start-domain", "-v"]

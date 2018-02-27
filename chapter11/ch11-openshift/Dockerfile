# FROM oracle/glassfish:nightly

# ENV GLASSFISH_ARCHIVE glassfish5
# ENV DOMAIN_NAME domain1
# ENV INSTALL_DIR /
# ENV GLASSFISH_ROOT ${INSTALL_DIR}/${GLASSFISH_ARCHIVE}
# ENV GLASSFISH_HOME ${INSTALL_DIR}/${GLASSFISH_ARCHIVE}/glassfish
# 
# ENV DEPLOYMENT_DIR ${GLASSFISH_HOME}/domains/${DOMAIN_NAME}/autodeploy
# 
# ENV user=glassfish \
#     group=glassfish \
#     uid=1001 \
#     gid=501
# 
# RUN mkdir -p /home/${user}
# 
# RUN groupadd --system ${group} -g ${gid} && \
#     adduser -u ${uid} -g ${gid} -r ${user} && \    
#     chown -R ${user}:${user} ${GLASSFISH_ROOT} && \
#     chown -R ${user}:${user} /home/${user} && \    
#     chmod -R 775 ${GLASSFISH_ROOT}
# 
# RUN usermod -g wheel ${user}    
# 
# USER ${uid}
# 
# COPY app.war ${DEPLOYMENT_DIR}
# 
# 
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

COPY docker-entrypoint.sh entrypoint.sh
ENTRYPOINT ["entrypoint.sh"]

# Ports being exposed
EXPOSE 4848 8080 8181

# Start asadmin console and the domain
CMD ["asadmin", "start-domain", "-v"]
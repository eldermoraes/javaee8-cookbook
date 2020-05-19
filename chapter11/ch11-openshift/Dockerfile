FROM eldermoraes/gf-jakartaee-jdk8

ENV DEPLOYMENT_DIR ${GLASSFISH_HOME}/glassfish/domains/domain1/autodeploy/

COPY app.war ${DEPLOYMENT_DIR}

USER root

RUN chown -R glassfish:glassfish_grp ${DEPLOYMENT_DIR}/app.war \
    && chmod -R 777 ${DEPLOYMENT_DIR}/app.war

USER glassfish
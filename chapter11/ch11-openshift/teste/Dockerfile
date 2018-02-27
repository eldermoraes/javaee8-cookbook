FROM eldermoraes/gf-javaee-cookbook-os

ENV GLASSFISH_ARCHIVE glassfish5
ENV DOMAIN_NAME domain1
ENV INSTALL_DIR /
ENV GLASSFISH_ROOT ${INSTALL_DIR}/${GLASSFISH_ARCHIVE}
ENV GLASSFISH_HOME ${INSTALL_DIR}/${GLASSFISH_ARCHIVE}/glassfish

ENV DEPLOYMENT_DIR ${GLASSFISH_HOME}/domains/${DOMAIN_NAME}/autodeploy

ENV user=glassfish \
    group=glassfish \
    uid=1001 \
    gid=501

RUN addgroup --system ${group} --gid ${gid} && \
    adduser -u ${uid} --gid ${gid} --disabled-login --system ${user} && \    
    chown -R ${user}:${user} ${GLASSFISH_ROOT} && \
    chmod -R 775 ${GLASSFISH_ROOT} 	

USER ${uid} 

COPY app.war ${DEPLOYMENT_DIR}

FROM openjdk:8-jdk

ENV GLASSFISH_HOME /usr/local/glassfish
ENV PATH ${GLASSFISH_HOME}/bin:$PATH
ENV GLASSFISH_PKG glassfish-5.1.0.zip
ENV GLASSFISH_URL https://www.eclipse.org/downloads/download.php?file=/glassfish/glassfish-5.1.0.zip&r=1

RUN mkdir -p ${GLASSFISH_HOME}

WORKDIR ${GLASSFISH_HOME}

RUN set -x \
	&& curl -fSL ${GLASSFISH_URL} -o ${GLASSFISH_PKG} \
    && unzip -o $GLASSFISH_PKG \
    && rm -f $GLASSFISH_PKG \
	&& mv glassfish5/* ${GLASSFISH_HOME} \
	&& rm -Rf glassfish5

RUN addgroup glassfish_grp \
    && adduser --system glassfish \
    && usermod -G glassfish_grp glassfish \
    && chown -R glassfish:glassfish_grp ${GLASSFISH_HOME} \
    && chmod -R 777 ${GLASSFISH_HOME}

COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

USER glassfish

ENTRYPOINT ["/docker-entrypoint.sh"]

EXPOSE 4848 8080 8181
CMD ["asadmin", "start-domain", "-v"]

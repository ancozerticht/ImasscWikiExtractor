FROM        openjdk:8-jdk

ENV         GLASSFISH_HOME    /usr/local/glassfish5
ENV         PATH              $GLASSFISH_HOME/bin:$PATH

RUN         apt-get update && \
            apt-get install -y curl unzip zip inotify-tools && \
            rm -rf /var/lib/apt/lists/*

RUN         curl -L -o /tmp/glassfish-5.1.0.zip http://ftp.jaist.ac.jp/pub/eclipse/glassfish/glassfish-5.1.0.zip && \
            unzip /tmp/glassfish-5.1.0.zip -d /usr/local && \
            rm -f /tmp/glassfish-5.1.0.zip

RUN         zip -d $GLASSFISH_HOME/glassfish/modules/endorsed/grizzly-npn-bootstrap.jar sun/security/ssl/*

RUN         keytool -importkeystore -noprompt \
                -srckeystore $JAVA_HOME/jre/lib/security/cacerts \
                -destkeystore $GLASSFISH_HOME/glassfish/domains/domain1/config/cacerts.jks \
                -srcstorepass changeit -deststorepass changeit

EXPOSE      8080 8181 4848

WORKDIR     $GLASSFISH_HOME

COPY        ./build/libs/imassc-wiki-extractor-1.0.war /tmp/imassc-wiki-extractor-1.0.war
RUN         asadmin start-domain --debug=true domain1 && \
            asadmin deploy --force=true /tmp/imassc-wiki-extractor-1.0.war && \
            asadmin stop-domain domain1 && \
            rm -f /tmp/imassc-wiki-extractor-1.0.war

CMD         ["asadmin", "start-domain", "--verbose=true", "--debug=true", "domain1"]

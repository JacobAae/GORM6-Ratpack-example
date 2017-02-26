FROM postgres

MAINTAINER Jacob Mikkelsen <jacob.aae@gmail.com>

ENV DB_NAME database
ENV DB_USER admin
ENV DB_PASS password

ADD setup-database.sql /docker-entrypoint-initdb.d/
RUN chmod 755 /docker-entrypoint-initdb.d/setup-database.sql
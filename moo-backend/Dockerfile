FROM pandeiro/lein:latest

EXPOSE 8080

RUN mkdir /project
WORKDIR /project

COPY ./project.clj ./
COPY ./src/ ./src/
COPY ./resources/ ./resources/

RUN lein deps

ENTRYPOINT ["/usr/bin/lein"]
CMD ["run"]

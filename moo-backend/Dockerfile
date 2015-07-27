FROM pandeiro/lein:latest

EXPOSE 8080

RUN mkdir /project
WORKDIR /project

COPY project.clj ./
COPY src ./
COPY resources ./

RUN lein deps

ENTRYPOINT ["/usr/bin/lein"]
CMD ["run"]

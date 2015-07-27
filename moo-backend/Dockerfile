FROM pandeiro/lein:latest

EXPOSE 8080

RUN mkdir /project
WORKDIR /project

ADD project.clj .
ADD src .
ADD resources .

RUN lein run

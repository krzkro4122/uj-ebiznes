FROM ubuntu:22.04

ENV TZ=Europe/Warsaw

EXPOSE 9000

COPY echo-go-gorm .

RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install -y vim git curl golang sqlite3

RUN go mod download

CMD [ "go", "run", "server.go"]
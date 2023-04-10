FROM ubuntu:22.04

ENV TZ=Europe/Warsaw

RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install -y vim git curl unzip zip zlib1g-dev build-essential

RUN apt-get install -y coreutils tar
RUN curl -s https://ngrok-agent.s3.amazonaws.com/ngrok.asc | tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null && echo "deb https://ngrok-agent.s3.amazonaws.com buster main" | tee /etc/apt/sources.list.d/ngrok.list && apt update && apt install ngrok

CMD [ "sh", "-c", "ngrok http 9000" ]

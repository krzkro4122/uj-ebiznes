FROM ubuntu:22.04

ENV TZ=Europe/Warsaw

RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install -y vim git curl unzip zip zlib1g-dev build-essential

RUN curl -O https://www.python.org/ftp/python/3.8.0/Python-3.8.0.tgz \
    && tar -zxvf Python-3.8.0.tgz \
    && cd Python-3.8.0 \
    && ./configure --enable-optimizations \
    && make \
    && make install

RUN apt-get install -y openjdk-8-jdk

WORKDIR /home/kkrol
RUN mkdir ebiznes
WORKDIR /home/kkrol/ebiznes

VOLUME [ "/home/kkrol/ebiznes" ]

COPY . /home/kkrol/ebiznes/

RUN curl -s https://get.sdkman.io | bash
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
    sdk install java 8.0.302-open && \
    sdk install kotlin && \
    sdk install gradle"

RUN ./gradlew clean build
RUN ./gradlew run

CMD [ "./gradlew", "run" ]

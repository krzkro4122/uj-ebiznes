# docker

The `Dockerfile` contains instructions that produce an image that:

- is based on `ubuntu:22.04`
- has packages like :
  - python3.8
  - java1.8
  - sdkman with gradle, kotlin and java
- Builds and runs a gradle/kotlin `Hello-world!` project that has JDBC as a dependency

The repo also contains the aforementioned `gradle project` and a simple `docker compose` configuration file.

Docker image's public URL: https://hub.docker.com/r/layor/ebiznes-docker

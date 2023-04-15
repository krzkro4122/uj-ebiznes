# docker

The `Dockerfile` contains instructions that produce an image that:

- is based on `ubuntu:22.04`
- has packages like:
  - `python3.8`
  - `java1.8`
  - sdkman with `gradle`, `kotlin` and `java`
- Builds and runs a gradle/kotlin `Hello-world!` project that has JDBC as a dependency
- Can be started with a simple command `docker-compose up` thanks to a simple `docker compose` configuration file.

The end of the docker-compose command output should look along the line of this:
![image](https://user-images.githubusercontent.com/75375838/230917847-9d296903-6659-4002-aff3-38eaccf533fa.png)

Docker image's public URL: https://hub.docker.com/r/layor/ebiznes-docker</br>
![image](https://user-images.githubusercontent.com/75375838/230918261-4570f68a-581b-480d-9347-ceb471bf3437.png)

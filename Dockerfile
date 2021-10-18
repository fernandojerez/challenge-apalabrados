FROM azul/zulu-openjdk-alpine:11-jre as build_stage
WORKDIR /app
COPY ./ /app/
RUN /app/gradlew build --stacktrace
RUN /app/gradlew installDist

FROM azul/zulu-openjdk-alpine:16-jre
WORKDIR /apalabrados
COPY --from=build_stage /app/build/install/apalabrados .
ENTRYPOINT [ "/apalabrados/bin/apalabrados" ]

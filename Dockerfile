FROM eclipse-temurin:21-jdk

RUN apt-get update && apt-get install -y \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY java/ /app/java/

RUN javac java/*.java

CMD ["java", "-cp", "java", "main"]

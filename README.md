# test-stub-spring-boot

Spring Boot Test Stub. 
Simple. 

## Get the program

```bash
curl -fsSL "$(
    curl -s "https://api.github.com/repos/ivasilyev/test-stub-spring-boot/releases/latest" \
    | grep -E '\"browser_download_url\":.*\.jar' \
    | sed -E 's/.*"([^"]+)".*/\1/' 
)" -o "test-stub-spring-boot.jar"
```

## Run the program

```bash
JAVA_HOME="/path/to/java/home" \
"/path/to/java/bin" \
    -Xms1024m \
    -Xmx2048m \
    -jar "test-stub-spring-boot.jar"
```

## Access test endpoint

```bash
curl "http://hostname:10000/test/endpoint"
```

## Change response delay time (in milliseconds)

```bash
curl "http://hostname:10000/delay/test/500"
```

## Access program monitoring metrics

```bash
curl "http://hostname:10000/actuator/prometheus"
```

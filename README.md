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

## Access test endpoint Web UI

```text
http://hostname:10000/endpoint/ui
```

## Access test endpoint via REST API

```bash
curl "http://hostname:10000/endpoint"
```

## Change response delay time (in milliseconds)

```bash
curl \
    --request POST \
    --data-binary '{"delayMs":555}' \
    --header 'Content-Type: application/json' \
    "http://hostname:10000/endpoint/api/set-delay"
```

## Access program monitoring metrics

```bash
curl "http://hostname:10000/actuator/prometheus"
```

## Bonus: Recommended Grafana dashboards

* [JVM (Micrometer)](https://grafana.com/grafana/dashboards/4701-jvm-micrometer/ "JVM (Micrometer)")
* [Node Exporter Full](https://grafana.com/grafana/dashboards/1860-node-exporter-full/ "Node Exporter Full")

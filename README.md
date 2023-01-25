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

## Try the interactive mode (e.g. for test script correlation)

### Get summands

```shell script
curl -i -X GET http://hostname:10000/interactive
```
```text
HTTP/1.1 200
Content-Type: application/json
Content-Length: 74
Date: Wed, 25 Jan 2023 09:00:41 GMT

{
  "summands" : {
    "summand_1" : 453,
    "summand_2" : 785
  }
}
```

### Post sum

```shell script
curl -i -X POST http://hostname:10000/interactive -H "Content-Type: application/json" -d '{"sum": 1238}'
```
```text
HTTP/1.1 200
Content-Type: application/json
Content-Length: 55
Date: Wed, 25 Jan 2023 09:00:57 GMT

{
  "answer" : {
    "evaluation" : "correct"
  }
}
```

### Try again to ensure that values have changed

```shell script
curl -i -X POST http://hostname:10000/interactive -H "Content-Type: application/json" -d '{"sum": 1238}'
```
```text
HTTP/1.1 400
Content-Type: application/json
Content-Length: 53
Date: Wed, 25 Jan 2023 09:01:01 GMT
Connection: close

{
  "answer" : {
    "evaluation" : "wrong"
  }
}
```

## Access program monitoring metrics

```bash
curl "http://hostname:10000/actuator/prometheus"
```

## Bonus: Recommended Grafana dashboards

* [JVM (Micrometer)](https://grafana.com/grafana/dashboards/4701-jvm-micrometer/ "JVM (Micrometer)")
* [Node Exporter Full](https://grafana.com/grafana/dashboards/1860-node-exporter-full/ "Node Exporter Full")

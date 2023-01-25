package org.test.spring_boot_stub.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.concurrent.ThreadLocalRandom;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class InteractiveController {
    private static final Logger LOGGER = Logger.getLogger(EndPointController.class.getName());
    private static final SimpleDateFormat DATE_FORMAT =  new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    private String rUID;
    private List<Integer> summands;
    private int sum = 0;

    private void roll() {
        this.summands = new LinkedList<Integer>(Arrays.asList(
            ThreadLocalRandom.current().nextInt(1000),
            ThreadLocalRandom.current().nextInt(1000)
        ));
        this.sum = this.summands.stream().mapToInt(Integer::valueOf).sum();
        this.rUID = UUID.randomUUID().toString();
    }

    public InteractiveController() {
        this.roll();
    }
    // curl -i -X GET http://localhost:10000/interactive
    @RequestMapping(
        value = "/interactive",
        produces = "application/json",
        method = RequestMethod.GET
    )
    public String interactiveGetEndPoint() {
        Date requestReceiveDate = new Date();
        LOGGER.fine(String.format(
            "Request received from '%s' at %s",
            this.rUID,
            DATE_FORMAT.format(requestReceiveDate)
        ));
        Date immediateResponseDate = new Date();

        int delta = (int) (immediateResponseDate.getTime() - requestReceiveDate.getTime());
        if (delta < DelayController.endPointDelay) {
            try {
                Thread.sleep(DelayController.endPointDelay - delta);
            } catch (InterruptedException e) {
                LOGGER.info(String.format("Error: %s", e.getMessage()));
            }
        }

        Date delayedResponseDate = new Date();
        LOGGER.info(String.format(
                "Response sent from '%s' at %s",
                this.rUID,
                DATE_FORMAT.format(delayedResponseDate)
        ));
        LOGGER.info(String.format(
                "Response time for '%s': %s ms",
                this.rUID,
                delayedResponseDate.getTime() - requestReceiveDate.getTime()
        ));

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();

        ObjectNode summandNode = mapper.createObjectNode();
        int counter = 0;
        for (int summand : this.summands) {
            summandNode.put(String.format("summand_%d", ++counter), summand);
        }
        root.set("summands", summandNode);

        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        } catch (Exception e) {
            return "";
        }
    }

    // curl -i -X POST http://localhost:10000/interactive -H "Content-Type: application/json" -d '{"sum": 0}'
    @RequestMapping(
        value = "/interactive",
        produces = "application/json",
        method = RequestMethod.POST
    )
    public ResponseEntity<String> interactivePostEndPoint(
        @RequestBody String body
    ) {
        Date requestReceiveDate = new Date();
        LOGGER.fine(String.format(
                "Request received from '%s' at %s",
                this.rUID,
                DATE_FORMAT.format(requestReceiveDate)
        ));
        Date immediateResponseDate = new Date();

        int delta = (int) (immediateResponseDate.getTime() - requestReceiveDate.getTime());
        if (delta < DelayController.endPointDelay) {
            try {
                Thread.sleep(DelayController.endPointDelay - delta);
            } catch (InterruptedException e) {
                LOGGER.info(String.format("Error: %s", e.getMessage()));
            }
        }

        Date delayedResponseDate = new Date();
        LOGGER.info(String.format(
                "Response sent from '%s' at %s",
                this.rUID,
                DATE_FORMAT.format(delayedResponseDate)
        ));
        LOGGER.info(String.format(
                "Response time for '%s': %s ms",
                this.rUID,
                delayedResponseDate.getTime() - requestReceiveDate.getTime()
        ));

        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        try {
            map = mapper.readValue(body, HashMap.class);
        } catch (JsonProcessingException e) {
            LOGGER.warning(
                String.format("Unable to parse request body: '%s'", body)
            );
        }

        for (Object objectName : map.keySet()) {
            System.out.println(objectName+ ":"+ map.get(objectName));
        }

        boolean isCorrectAnswer = map.get("sum") != null && map.get("sum").equals(this.sum);
        String evaluatedAnswer = isCorrectAnswer ? "correct" : "wrong";
        LOGGER.info(String.format("Got %s answer", evaluatedAnswer));

        ObjectNode root = mapper.createObjectNode();
        ObjectNode evaluationNode = mapper.createObjectNode();
        evaluationNode.put("evaluation", evaluatedAnswer);
        root.set("answer", evaluationNode);

        String outBody;
        try {
            outBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        } catch (Exception e) {
            outBody = "";
        }

        HttpStatus outStatus = isCorrectAnswer ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        this.roll();
        return ResponseEntity.status(outStatus).body(outBody);
    }
}


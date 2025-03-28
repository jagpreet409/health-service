package com.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient_events.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient" ,groupId = "analytics-service")
    public void consumeEvent(byte [] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            log.info("Received patient-service Event : [PatientId={} , Patient-name={}, PatientEmail={}]"
                    ,patientEvent.getPatientId(),
                    patientEvent.getName(),
                    patientEvent.getEmail());
            //Add any logic if you want to process the event

        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing Kafka consumer analytics event :{}" + e.getMessage());
        }
    }
}

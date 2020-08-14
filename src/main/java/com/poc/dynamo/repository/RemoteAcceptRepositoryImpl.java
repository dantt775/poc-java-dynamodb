package com.poc.dynamo.repository;

import com.amazonaws.services.dynamodbv2.document.*;

import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.poc.dynamo.model.RemoteAcceptRequest;
import com.poc.dynamo.util.DynamoConstants;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class RemoteAcceptRepositoryImpl {


    private DynamoDBRepository dynamoDBRepository;

    public RemoteAcceptRepositoryImpl(DynamoDBRepository dynamoDBRepository) {
        this.dynamoDBRepository = dynamoDBRepository;
    }




    public void saveRemoteAccept(RemoteAcceptRequest model){
        Item remoteAccept = (new Item()).withPrimaryKey(DynamoConstants.TABLE_FIELD_SOLICITATIONID, model.getSolicitationId())
                .withString(DynamoConstants.TABLE_FIELD_ORIGIN, model.getOrigin())
                .withString(DynamoConstants.TABLE_FIELD_CPF, model.getCpf())
                .withString(DynamoConstants.TABLE_FIELD_NAME, model.getName())
                .withString(DynamoConstants.TABLE_FIELD_EMAIL, model.getEmail())
                .withStringSet(DynamoConstants.TABLE_FIELD_CNPJ, model.getCnpj())
                .withString(DynamoConstants.TABLE_FIELD_COMPANYNAME, model.getCompanyName())
                .withNumber(DynamoConstants.TABLE_FIELD_TTL, getEpochTime(model.getTtl()));

        PutItemOutcome dynamoResponse = dynamoDBRepository.save(DynamoConstants.TABLE_NAME, remoteAccept);
    }




    private long getEpochTime(LocalDate date){
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo"); // or: ZoneId.of("Europe/Oslo");
        return date.atStartOfDay(zoneId).toEpochSecond();
    }

    public Item find(Integer id){
       Item item =  this.dynamoDBRepository.findItem(DynamoConstants.TABLE_NAME, new GetItemSpec().withPrimaryKey(DynamoConstants.TABLE_FIELD_SOLICITATIONID, id));
       return item;
    }
}

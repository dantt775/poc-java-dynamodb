package com.poc.dynamo.service;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.poc.dynamo.model.RemoteAcceptRequest;
import com.poc.dynamo.repository.RemoteAcceptRepositoryImpl;
import com.poc.dynamo.util.DynamoConstants;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


@Service
public class ProposalService {

    public RemoteAcceptRepositoryImpl repository;

    public ProposalService(RemoteAcceptRepositoryImpl repository){
        this.repository=repository;
    }

    public void save(RemoteAcceptRequest request){
        this.repository.saveRemoteAccept(request);
    }

    public RemoteAcceptRequest find(Integer proposta){
        Item item = this.repository.find(proposta);
        return RemoteAcceptRequest.builder()
                .cnpj(item.getStringSet(DynamoConstants.TABLE_FIELD_CNPJ).toString())
                .companyName(item.getString(DynamoConstants.TABLE_FIELD_COMPANYNAME))
                .name(item.getString(DynamoConstants.TABLE_FIELD_NAME))
                .cpf(item.getString(DynamoConstants.TABLE_FIELD_CPF))
                .email(item.getString(DynamoConstants.TABLE_FIELD_EMAIL))
                .origin(item.getString(DynamoConstants.TABLE_FIELD_ORIGIN))
                .solicitationId(Long.valueOf(item.getString(DynamoConstants.TABLE_FIELD_SOLICITATIONID)))
                .ttl(epochToLocalDate(item.getString(DynamoConstants.TABLE_FIELD_TTL)))
                .build();

    }


    private LocalDate epochToLocalDate(String epoch){
        return Instant.ofEpochMilli(Long.valueOf(epoch)).atZone(ZoneId.systemDefault()).toLocalDate();
    }

}

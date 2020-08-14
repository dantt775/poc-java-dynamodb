package com.poc.dynamo.model;


import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;

@Builder
@Data
public class RemoteAcceptRequest {


    private Long solicitationId;
    private String origin;
    private String cpf;
    private String name;
    private String email;
    private String cnpj;
    private String companyName;
    private LocalDate ttl;

}


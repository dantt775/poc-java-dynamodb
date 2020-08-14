package com.poc.dynamo.controller;

import com.poc.dynamo.model.RemoteAcceptRequest;
import com.poc.dynamo.service.ProposalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("proposal")
public class ProposalController {

    private ProposalService proposalService;

    public ProposalController(ProposalService proposalService){
        this.proposalService=proposalService;
    }
    @PostMapping
    public ResponseEntity<Map<String, String>> createProposal(@RequestBody RemoteAcceptRequest request){
        Map<String, String> response = new HashMap<>();
        proposalService.save(request);
        response.put("status","created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("/{id}")
    public ResponseEntity<RemoteAcceptRequest> getProposal(@PathVariable Integer id){
        return ResponseEntity.ok(proposalService.find(id));

    }

    @GetMapping("/{id}/{cpf}")
    public void getProposalByIdAndCpf(){


    }
}

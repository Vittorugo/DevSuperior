package com.example.trabalhofinalcap01.resources;

import com.example.trabalhofinalcap01.dto.ClientDto;
import com.example.trabalhofinalcap01.entities.Client;
import com.example.trabalhofinalcap01.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "sizePerPage", defaultValue = "10") Integer sizePerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderby,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        PageRequest pageRequest = PageRequest.of(page, sizePerPage);
        Page<ClientDto> clients = service.findAll(pageRequest);
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}

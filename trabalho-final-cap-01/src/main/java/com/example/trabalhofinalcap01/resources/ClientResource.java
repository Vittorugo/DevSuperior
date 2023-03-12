package com.example.trabalhofinalcap01.resources;

import com.example.trabalhofinalcap01.dto.ClientDto;
import com.example.trabalhofinalcap01.entities.Client;
import com.example.trabalhofinalcap01.mapper.ClientMapper;
import com.example.trabalhofinalcap01.repositories.ClientRepository;
import com.example.trabalhofinalcap01.services.ClientService;
import com.example.trabalhofinalcap01.services.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;
    private final ClientMapper clientMapper;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "sizePerPage", defaultValue = "10") Integer sizePerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderby,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        PageRequest pageRequest = PageRequest.of(page, sizePerPage);
        Page<Client> clients = service.findAll(pageRequest);
        return ResponseEntity.ok().body(clients.map(client -> clientMapper.toRepresentation(client)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientMapper.toRepresentation(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ClientDto> insert(@RequestBody ClientDto dto) {
        Client newClient = service.insert(clientMapper.toDomain(dto));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newClient.getId())
                .toUri();
        return ResponseEntity.created(uri).body(clientMapper.toRepresentation(newClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto dto, @PathVariable Long id) {
        Client updateClient = service.update(dto, id);
        return ResponseEntity.ok().body(clientMapper.toRepresentation(updateClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
}

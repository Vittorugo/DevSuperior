package com.example.trabalhofinalcap01.resources;

import com.example.trabalhofinalcap01.dto.ClientDto;
import com.example.trabalhofinalcap01.mapper.ClientMapper;
import com.example.trabalhofinalcap01.repositories.ClientRepository;
import com.example.trabalhofinalcap01.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
            @RequestParam(name = "linesPerPage", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderby,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderby);
        Page<ClientDto> clients = service.findAll(pageRequest);
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDto> insert(@RequestBody ClientDto dto) {
        ClientDto newClient = service.insert(clientMapper.toDomain(dto));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newClient.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto dto, @PathVariable Long id) {
        ClientDto updateClient = service.update(dto, id);
        return ResponseEntity.ok().body(updateClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
}

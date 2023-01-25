package com.example.trabalhofinalcap01.services;

import com.example.trabalhofinalcap01.dto.ClientDto;
import com.example.trabalhofinalcap01.entities.Client;
import com.example.trabalhofinalcap01.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Page<ClientDto> findAll(PageRequest pageRequest) {
        Page<Client> clients = clientRepository.findAll(pageRequest);
        return clients.map(ClientDto::new);
    }
}

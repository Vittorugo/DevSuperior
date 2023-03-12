package com.example.trabalhofinalcap01.services;

import com.example.trabalhofinalcap01.dto.ClientDto;
import com.example.trabalhofinalcap01.entities.Client;
import com.example.trabalhofinalcap01.repositories.ClientRepository;
import com.example.trabalhofinalcap01.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Page<Client> findAll(PageRequest pageRequest) {
        Page<Client> clients = clientRepository.findAll(pageRequest);
        return clients;
    }

    @Transactional
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client Not Found"));
    }

    @Transactional
    public Client insert(Client client) {
        return clientRepository.save(client);
    }

    public Client update(ClientDto dto, Long id) {
        try {
            Client updateClient = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found for given id!"));
            updateClientAtributs(updateClient, dto);
            clientRepository.save(updateClient);
            return updateClient;
        } catch (jakarta.persistence.EntityNotFoundException e) {
            throw new EntityNotFoundException("Error updating Client!");
        }
    }

    private void updateClientAtributs(Client client, ClientDto dto) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setChildren(dto.getChildren());
        client.setBirthDate(dto.getBirthDate());
    }

    public String delete(Long id) {
        try {
            clientRepository.deleteById(id);
            return "Client deleted successfully";
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Client Not Found for given id");
        }
    }
}

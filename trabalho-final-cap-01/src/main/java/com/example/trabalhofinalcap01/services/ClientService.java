package com.example.trabalhofinalcap01.services;

import com.example.trabalhofinalcap01.dto.ClientDto;
import com.example.trabalhofinalcap01.entities.Client;
import com.example.trabalhofinalcap01.mapper.ClientMapper;
import com.example.trabalhofinalcap01.repositories.ClientRepository;
import com.example.trabalhofinalcap01.services.exceptions.DataBaseException;
import com.example.trabalhofinalcap01.services.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private final ClientMapper mapper;

    @Transactional
    public Page<ClientDto> findAll(PageRequest pageRequest) {
        Page<ClientDto> clients = clientRepository.findAll(pageRequest).map(mapper::toRepresentation);
        return clients;
    }

    @Transactional
    public ClientDto findById(Long id) {
        return mapper.toRepresentation(clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client Not Found")));
    }

    @Transactional
    public ClientDto insert(Client client) {
        return mapper.toRepresentation(clientRepository.save(client));
    }

    public ClientDto update(ClientDto dto, Long id) {
        try {
            Client updateClient = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found for given id!"));
            updateClientAtributs(updateClient, dto);
            return mapper.toRepresentation(clientRepository.save(updateClient));
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
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException("Category ID Not Found");
        } catch (DataIntegrityViolationException exception) {
            throw new DataBaseException("Integrity violation");
        }
    }
}

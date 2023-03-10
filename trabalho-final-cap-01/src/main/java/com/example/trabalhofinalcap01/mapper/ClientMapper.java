package com.example.trabalhofinalcap01.mapper;

import com.example.trabalhofinalcap01.dto.ClientDto;
import com.example.trabalhofinalcap01.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toRepresentation(Client client);
    Client toDomain(ClientDto dto);
}

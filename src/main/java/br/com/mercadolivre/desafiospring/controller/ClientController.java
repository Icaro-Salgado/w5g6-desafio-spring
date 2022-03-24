package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.request.ClientDTO;
import br.com.mercadolivre.desafiospring.models.Client;
import br.com.mercadolivre.desafiospring.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("clients/")
    public ResponseEntity<List<Client>> listClients() throws IOException {
        return ResponseEntity.ok(clientService.listClients());
    }

    @PostMapping("clients/")
    public ResponseEntity<List<ClientDTO>> addClient(@RequestBody List<ClientDTO> clientsDtoToAdd) throws IOException {
        List<Client> clientsToAdd = clientsDtoToAdd.stream().map(ClientDTO::dtoToModel).collect(Collectors.toList());

        List<Client> addedClients = clientService.addClients(clientsToAdd);
        List<ClientDTO> addedClientsDTO = addedClients.stream().map(ClientDTO::modelToDTO).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(addedClientsDTO);
    }
}

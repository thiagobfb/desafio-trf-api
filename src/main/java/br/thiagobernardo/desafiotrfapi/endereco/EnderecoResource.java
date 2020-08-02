package br.thiagobernardo.desafiotrfapi.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

    @Autowired
    private EnderecoIntegrationService enderecoIntegrationService;

    @GetMapping(value = "/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoDTO> buscarPorCep(@PathVariable("cep") String cep) {
        return ResponseEntity.ok().body(enderecoIntegrationService.buscarEnderecoPorCEP(cep));
    }
}

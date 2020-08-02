package br.thiagobernardo.desafiotrfapi.endereco;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EnderecoIntegratioServiceImpl implements EnderecoIntegrationService {

    private static final String URI = "https://viacep.com.br/ws/";

    public EnderecoDTO buscarEnderecoPorCEP(String cep) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(URI  + cep + "/json", EnderecoDTO.class);
    }
}

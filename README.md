# desafio-trf-api
API do desafio do TRF

Para cadastrar no Postman:
```json
{
    "cnpj": "21295642000135",
    "nome": "Empresa 1",
    "tipoEmpresa": "MATRIZ",
    "razaoSocial": "Empresa 1 Ltda",
    "contato": "Contato 1",
    "email": "contato1@mail.com",
    "cep": "25020010",
    "estado": "RJ",
    "bairro": "25 de Agosto",
    "cidade": "Duque de Caxias",
    "logradouro": "Rua Tauá, 49",
    "complemento": "Apto. 1301",
    "matriz": null
}
```
```json
{
    "cnpj": "15625373000125",
    "nome": "Empresa 1 Filial",
    "tipoEmpresa": "FILIAL",
    "razaoSocial": "Empresa 1 Ltda",
    "contato": "Contato 1",
    "email": "contato1@mail.com",
    "cep": "60224996",
    "estado": "RJ",
    "bairro": "25 de Agosto",
    "cidade": "Duque de Caxias",
    "logradouro": "Rua 3, 419",
    "complemento": null,
    "matriz": {
        "id": 1,
        "cnpj": "21295642000135",
        "nome": "Empresa 1",
        "tipoEmpresa": "MATRIZ",
        "razaoSocial": "Empresa 1 Ltda",
        "contato": "Contato 1",
        "email": "contato1@mail.com",
        "cep": "25020010",
        "estado": "RJ",
        "bairro": "25 de Agosto",
        "cidade": "Duque de Caxias",
        "logradouro": "Rua Tauá, 49",
        "complemento": "Apto. 1301",
        "matriz": null
    }
}
```

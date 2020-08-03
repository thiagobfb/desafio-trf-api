package br.thiagobernardo.desafiotrfapi.empresa;

import org.springframework.data.domain.Page;

import java.util.List;

public interface EmpresaService {
    Page<Empresa> buscar(Integer page, Integer size, String cnpj, String nome, TipoEmpresaEnum tipoEmpresa);

    List<Empresa> buscarMatrizes();

    Empresa buscarPorId(Long id);

    Empresa salvar(Empresa e);

    Empresa atualizar(Long id, Empresa e);

    void excluir(Long id);
}

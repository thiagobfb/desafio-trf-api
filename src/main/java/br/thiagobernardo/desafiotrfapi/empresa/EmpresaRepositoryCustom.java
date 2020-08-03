package br.thiagobernardo.desafiotrfapi.empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface EmpresaRepositoryCustom {

    Page<Empresa> findByCnpjAndNomeAndTipoEmpresa(Pageable pageable, @Param("cnpj") String cnpj,
                                                  @Param("nome") String nome, @Param("tipoEmpresa") TipoEmpresaEnum tipoEmpresa);
}

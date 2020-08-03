package br.thiagobernardo.desafiotrfapi.empresa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long>, EmpresaRepositoryCustom {

    List<Empresa> findByTipoEmpresa(TipoEmpresaEnum tipo);
}

package br.thiagobernardo.desafiotrfapi.empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
    @Query("SELECT e FROM Empresa e WHERE (:cnpj IS NULL OR e.cnpj LIKE %:cnpj%) " +
            "AND (:nome IS NULL OR e.nome LIKE %:nome%) AND (:tipoEmpresa IS NULL OR e.tipoEmpresa = :tipoEmpresa)")
    Page<Empresa> findByCnpjAndNomeAndTipoEmpresa(Pageable pageable, @Param("cnpj") String cnpj,
                                                  @Param("nome") String nome, @Param("tipoEmpresa") TipoEmpresaEnum tipoEmpresa);
}

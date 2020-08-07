package br.thiagobernardo.desafiotrfapi.empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
@Transactional(readOnly = true)
public class EmpresaRepositoryImpl implements EmpresaRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<Empresa> findByCnpjAndNomeAndTipoEmpresa(Pageable pageable, String cnpj, String nome, TipoEmpresaEnum tipoEmpresa) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Empresa> cq = cb.createQuery(Empresa.class);
        Root<Empresa> iRoot = cq.from(Empresa.class);
        List<Predicate> predicates = new ArrayList<>();

        if (nonNull(cnpj)) {
            predicates.add(cb.like(iRoot.get("cnpj"), "%" + cnpj + "%"));
        }

        if (nonNull(nome)) {
            predicates.add(cb.like(iRoot.get("nome"), "%" + nome + "%"));
        }

        if (nonNull(tipoEmpresa)) {
            predicates.add(cb.equal(iRoot.get("tipoEmpresa"), tipoEmpresa));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<Empresa> query = entityManager.createQuery(cq);
        return new PageImpl<>(query.getResultList(), pageable, query.getResultList().size());
    }
}

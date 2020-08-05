package br.thiagobernardo.desafiotrfapi.empresa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class EmpresaRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmpresaRepository empresaRepository;

    @BeforeEach
    public void setUp() {
        Empresa e1 = new Empresa(null, "15640091000105", "Empresa 1",
                TipoEmpresaEnum.MATRIZ, "Empresa 1", "Contato 1",
                "contato1@mail.com", "24111980", "Rio de Janeiro",
                "Bairro 1", "Rio de Janeiro", "Rua 1, 36",
                null, null);

        Empresa e2 = new Empresa(null, "44820439000136", "Empresa 1 Filial",
                TipoEmpresaEnum.FILIAL, "Empresa 1", "Contato 1",
                "contato1@mail.com", "21013980", "São Paulo",
                "Bairro 1", "Osasco", "Rua C, 134",
                "Sala 103", e1);

        Empresa e3 = new Empresa(null, "43920308000168", "Empresa 1 Filial 2",
                TipoEmpresaEnum.FILIAL, "Empresa 1", "Contato 3",
                "contato3@mail.com", "46315000", "Distrito Federal",
                "Bairro 1", "Brasília", "SCS Bloco C",
                "Sala 120", e1);
        entityManager.persist(e1);
        entityManager.persist(e2);
        entityManager.persist(e3);
        entityManager.flush();
    }

    @Test
    public void testBuscarPorCnpjNomeTipoEmpresaRetoraTodos() {
        PageRequest pageRequest = PageRequest.of(1, 5);
        Page<Empresa> resultado = empresaRepository.findByCnpjAndNomeAndTipoEmpresa(pageRequest,null, null, null);

        assertNotNull(resultado.getContent());
        assertEquals(resultado.getContent().size(), 3);
    }

    @Test
    public void testBuscarPorCnpjNomeTipoEmpresaPesquisaFiliais() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Empresa> resultado = empresaRepository.findByCnpjAndNomeAndTipoEmpresa(pageRequest,null, null, TipoEmpresaEnum.FILIAL);

        assertNotNull(resultado.getContent());
        assertEquals(resultado.getContent().size(), 2);
    }

    @Test
    public void testPesquisarPorTipo() {
        List<Empresa> resultado = empresaRepository.findByTipoEmpresa(TipoEmpresaEnum.MATRIZ);

        assertNotNull(resultado);
        assertEquals(resultado.size(), 1);
    }
}

package br.thiagobernardo.desafiotrfapi.empresa;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmpresaServiceTest {

    @TestConfiguration
    static class EmpresaServiceImplTestContextConfiguration {

        @Bean
        public EmpresaService employeeService() {
            return new EmpresaServiceImpl();
        }
    }

    @MockBean
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSalvar() {
        Empresa e1 = new Empresa(null, "15640091000105", "Empresa 1",
                TipoEmpresaEnum.MATRIZ, "Empresa 1", "Contato 1",
                "contato1@mail.com", "24111980", "Rio de Janeiro",
                "Bairro 1", "Rio de Janeiro", "Rua 1, 36",
                null, null);
        when(empresaRepository.save(e1)).thenReturn(e1);
        Empresa resultado = empresaService.salvar(e1);
        assertEquals(e1, resultado);
    }


    @Test
    public void testBuscar() {
        Empresa e1 = new Empresa(1L, "15640091000105", "Empresa 1",
                TipoEmpresaEnum.MATRIZ, "Empresa 1", "Contato 1",
                "contato1@mail.com", "24111980", "Rio de Janeiro",
                "Bairro 1", "Rio de Janeiro", "Rua 1, 36",
                null, null);

        Empresa e2 = new Empresa(2L, "44820439000136", "Empresa 1 Filial",
                TipoEmpresaEnum.FILIAL, "Empresa 1", "Contato 1",
                "contato1@mail.com", "21013980", "São Paulo",
                "Bairro 1", "Osasco", "Rua C, 134",
                "Sala 103", e1);

        Empresa e3 = new Empresa(3L, "43920308000168", "Empresa 1 Filial 2",
                TipoEmpresaEnum.FILIAL, "Empresa 1", "Contato 3",
                "contato3@mail.com", "46315000", "Distrito Federal",
                "Bairro 1", "Brasília", "SCS Bloco C",
                "Sala 120", e1);

        List<Empresa> empresas = new ArrayList<>();
        empresas.add(e1);
        empresas.add(e2);
        empresas.add(e3);
        Page<Empresa> page = new PageImpl<>(empresas);
        when(empresaRepository.findByCnpjAndNomeAndTipoEmpresa(PageRequest.of(0, 5),
                null, null, null)).thenReturn(page);
        Page<Empresa> result = empresaService.buscar(0, 5, null, null, null);
        assertIterableEquals(empresas, result.getContent());
    }
}

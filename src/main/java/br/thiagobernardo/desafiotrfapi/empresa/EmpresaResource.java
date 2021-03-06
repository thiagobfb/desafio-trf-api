package br.thiagobernardo.desafiotrfapi.empresa;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaResource {

    private final EmpresaService empresaService;

    public EmpresaResource(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Empresa>> listarEmpresas(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "cnpj", required = false) String cnpj,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "tipoEmpresa", required = false) String tipoEmpresa) {
        return ResponseEntity.ok().body(empresaService.buscar(page, size, cnpj, nome, TipoEmpresaEnum.toEnum(tipoEmpresa)));
    }

    @GetMapping(value = "/matrizes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Empresa>> listarMatrizes() {
        return ResponseEntity.ok().body(empresaService.buscarMatrizes());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empresa> buscarEmpresa(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(empresaService.buscarPorId(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empresa> inserirEmpresa(@Valid @RequestBody Empresa empresa) {
        return ResponseEntity.ok().body(empresaService.salvar(empresa));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empresa> alterarEmpresa(@PathVariable("id") Long id, @Valid @RequestBody Empresa empresa) {
        return ResponseEntity.ok().body(empresaService.atualizar(id, empresa));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> excluirEmpresa(@PathVariable("id") Long id) {
        empresaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

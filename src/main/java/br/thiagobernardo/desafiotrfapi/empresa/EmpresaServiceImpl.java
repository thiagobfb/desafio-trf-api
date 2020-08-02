package br.thiagobernardo.desafiotrfapi.empresa;

import br.thiagobernardo.desafiotrfapi.exceptions.DataIntegrityException;
import br.thiagobernardo.desafiotrfapi.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Page<Empresa> buscar(Integer page, Integer size, String cnpj, String nome, TipoEmpresaEnum tipoEmpresa) {
        PageRequest pageRequest = PageRequest.of(1, 5);
        return empresaRepository.findByCnpjAndNomeAndTipoEmpresa(pageRequest, cnpj, nome, tipoEmpresa);
    }

    @Override
    public Empresa buscarPorId(Long id) {
        Optional<Empresa> obj = empresaRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato ! Id: " + id + ", Tipo: " + Empresa.class.getName()));
    }

    @Override
    @Transactional
    public Empresa salvar(Empresa e) {
        return empresaRepository.save(e);
    }

    @Override
    public Empresa atualizar(Long id, Empresa e) {
        Empresa empRetorno = buscarPorId(id);
        empRetorno.setCnpj(e.getCnpj());
        empRetorno.setNome(e.getNome());
        empRetorno.setTipoEmpresa(e.getTipoEmpresa());
        empRetorno.setRazaoSocial(e.getRazaoSocial());
        empRetorno.setContato(e.getContato());
        empRetorno.setEmail(e.getEmail());
        empRetorno.setCep(e.getCep());
        empRetorno.setEstado(e.getEstado());
        empRetorno.setBairro(e.getBairro());
        empRetorno.setCidade(e.getCidade());
        empRetorno.setLogradouro(e.getLogradouro());
        empRetorno.setComplemento(e.getComplemento());
        empRetorno.setMatriz(e.getMatriz());
        empRetorno.setFiliais(e.getFiliais());

        return this.salvar(e);
    }

    @Override
    public void excluir(Long id) {
        Empresa empRetorno = buscarPorId(id);

        if (empRetorno.getTipoEmpresa().equals(TipoEmpresaEnum.MATRIZ)) {
            throw new DataIntegrityException("A empresa é matriz e não pode ser excluída.");
        }
        empresaRepository.deleteById(id);
    }
}

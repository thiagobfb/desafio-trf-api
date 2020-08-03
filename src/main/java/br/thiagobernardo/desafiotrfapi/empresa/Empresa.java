package br.thiagobernardo.desafiotrfapi.empresa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {
    private static final long serialVersionUID = 6664627967013003473L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id", nullable = false)
    private Long id;

    @Column(name = "cnpj", nullable = false, unique = true, length = 14)
    @NotEmpty(message = "CNPJ obrigatório")
    @Length(min = 14, max = 14, message = "O tamanho deve ser de 14 caracteres")
    @CNPJ
    private String cnpj;

    @Column(name = "nome", nullable = false, length = 50)
    @NotEmpty(message = "Nome obrigatório")
    @Length(min = 5, max = 50, message = "O tamanho deve ser entre 5 e 50 caracteres")
    private String nome;

    @Column(name = "tipo_empresa", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEmpresaEnum tipoEmpresa;

    @Column(name = "razao_social", nullable = false)
    @NotEmpty(message = "Razão social obrigatória")
    @Length(min = 5, max = 40, message = "O tamanho deve ser entre 5 e 40 caracteres")
    private String razaoSocial;

    @Column(name = "contato", nullable = false)
    @NotEmpty(message = "Contato obrigatório")
    @Length(min = 5, max = 40, message = "O tamanho deve ser entre 5 e 40 caracteres")
    private String contato;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "E-mail obrigatório")
    @Length(min = 5, max = 40, message = "O tamanho deve ser entre 5 e 40 caracteres")
    @Email
    private String email;

    @Column(name = "cep", nullable = false)
    @NotEmpty(message = "Cep obrigatório")
    @Length(min = 8, max = 8, message = "O tamanho deve ser de 8 caracteres")
    private String cep;

    @Column(name = "estado", nullable = false)
    @NotEmpty(message = "Estado obrigatório")
    @Length(min = 2, max = 40, message = "O tamanho deve ser entre 5 e 40 caracteres")
    private String estado;

    @Column(name = "bairro", nullable = false)
    @NotEmpty(message = "Bairro obrigatório")
    @Length(min = 5, max = 40, message = "O tamanho deve ser entre 5 e 40 caracteres")
    private String bairro;

    @Column(name = "cidade", nullable = false)
    @NotEmpty(message = "Cidade obrigatória")
    @Length(min = 5, max = 40, message = "O tamanho deve ser entre 5 e 40 caracteres")
    private String cidade;

    @Column(name = "logradouro", nullable = false)
    @NotEmpty(message = "E-mail obrigatório")
    @Length(min = 5, max = 40, message = "O tamanho deve ser entre 5 e 40 caracteres")
    private String logradouro;

    @Column(name = "complemento")
    @Length(min = 5, max = 40, message = "O tamanho deve ser entre 5 e 40 caracteres")
    private String complemento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matriz_id", referencedColumnName = "empresa_id", nullable = true)
    private Empresa matriz;
}

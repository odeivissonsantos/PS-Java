package br.com.supera.gamestore.dtos;

import br.com.supera.gamestore.models.Usuario;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Campo TELEFONE é obrigatório")
    private String nomeCompleto;

    @NotNull(message = "Campo TELEFONE é obrigatório")
    @Email(message = "Digite um email válido!")
    private String email;

    @NotNull(message = "Campo TELEFONE é obrigatório")
    @CPF(message = "Digite um CPF válido!")
    private String cpf;

    @NotNull(message = "Campo TELEFONE é obrigatório")
    private String telefone;

    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.nomeCompleto = obj.getNomeCompleto();
        this.email = obj.getEmail();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }
}

package br.com.supera.gamestore.dtos;

import br.com.supera.gamestore.models.Usuario;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe responsável por passar os dados que serão mostrados no front-end.
 */

@Data
@RequiredArgsConstructor
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Campo NOME COMPLETO é obrigatório")
    private String nomeCompleto;

    @NotNull(message = "Campo EMAIL é obrigatório")
    @Email(message = "Digite um email válido!")
    private String email;

    @NotNull(message = "Campo CPF é obrigatório")
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

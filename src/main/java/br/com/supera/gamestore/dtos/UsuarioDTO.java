package br.com.supera.gamestore.dtos;

import br.com.supera.gamestore.models.Carrinho;
import br.com.supera.gamestore.models.Usuario;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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
    private String nomeCompleto;
    private String email;
    private String cpf;
    private String telefone;
    private Carrinho carrinho;

    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.nomeCompleto = obj.getNomeCompleto();
        this.email = obj.getEmail();
        this.cpf = obj.getCpf();
        this.carrinho = obj.getCarrinho();
        this.telefone = obj.getTelefone();
    }
}

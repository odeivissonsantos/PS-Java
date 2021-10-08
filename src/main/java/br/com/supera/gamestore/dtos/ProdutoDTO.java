package br.com.supera.gamestore.dtos;

import br.com.supera.gamestore.models.Produto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe responsável por passar os dados que serão mostrados no front-end.
 */

@RequiredArgsConstructor
@Data
public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Campo NOME é Obrigatório")
    private String nome;

    @NotNull(message = "Campo PREÇO é Obrigatório")
    private String preco;

    @NotNull(message = "Campo PONTUAÇÃO é Obrigatório")
    private Integer pontuacao;

    @NotNull(message = "Campo URL DA IMAGEM é Obrigatório")
    private String urlImagem;

    public ProdutoDTO(Produto obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.preco = obj.getPreco().toString();
        this.pontuacao = obj.getPontuacao();
        this.urlImagem = obj.getUrlImagem();
    }

}

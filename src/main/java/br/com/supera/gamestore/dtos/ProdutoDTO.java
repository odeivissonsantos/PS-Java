package br.com.supera.gamestore.dtos;

import br.com.supera.gamestore.models.Produto;
import br.com.supera.gamestore.util.BigDecimalConverter;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

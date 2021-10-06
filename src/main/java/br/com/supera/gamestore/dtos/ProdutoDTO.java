package br.com.supera.gamestore.dtos;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    @NotNull(message = "Campo NOME é Obrigatório")
    private String nome;

    @NotNull(message = "Campo PREÇO é Obrigatório")
    private BigDecimal preco;

    @NotNull(message = "Campo PONTUAÇÃO é Obrigatório")
    private Integer pontuacao;

    @NotNull(message = "Campo URL DA IMAGEM é Obrigatório")
    private String urlImagem;

}

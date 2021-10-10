package br.com.supera.gamestore.dtos;

import br.com.supera.gamestore.models.Carrinho;
import br.com.supera.gamestore.models.ItemCarrinho;
import br.com.supera.gamestore.models.Produto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe responsável por passar os dados que serão mostrados no front-end.
 */

@Data
@RequiredArgsConstructor
public class ItemCarrinhoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal precoUnitario; // valor atual do produto
    private Produto produto;
    private Carrinho carrinho;

    public ItemCarrinhoDTO(ItemCarrinho obj) {
        this.id = obj.getItemCarrinhoId();
        this.precoUnitario = obj.getPrecoUnitario();
        this.produto = obj.getProduto();
        this.carrinho = obj.getCarrinho();
    }
}

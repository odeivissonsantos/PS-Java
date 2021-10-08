package br.com.supera.gamestore.dtos;

import br.com.supera.gamestore.models.ItemCarrinho;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Data
@RequiredArgsConstructor
public class ItemCarrinhoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal preco; // valor atual do produto no dia da compra
    private ProdutoDTO produto;

    public ItemCarrinhoDTO(ItemCarrinho obj) {
        this.id = obj.getItemCarrinhoId();
        this.preco = obj.getPrecoUnitario();
        this.produto = new ProdutoDTO(obj.getProduto());
    }
}

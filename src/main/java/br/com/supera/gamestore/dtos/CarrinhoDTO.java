package br.com.supera.gamestore.dtos;

import br.com.supera.gamestore.models.Carrinho;
import br.com.supera.gamestore.models.ItemCarrinho;
import br.com.supera.gamestore.models.Usuario;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe responsável por passar os dados que serão mostrados no front-end.
 */

@Data
@RequiredArgsConstructor
public class CarrinhoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal frete;
    private BigDecimal subtotal;
    private BigDecimal total;
    private Usuario usuario;
    private List<ItemCarrinho> itens;

    public CarrinhoDTO(Carrinho obj) {
        this.id = obj.getCarrinhoId();
        this.frete = obj.getFrete();
        this.subtotal = obj.getSubTotal();
        this.total = obj.getTotal();
        this.usuario = obj.getUsuario();
        this.itens = obj.getItens();
    }

}

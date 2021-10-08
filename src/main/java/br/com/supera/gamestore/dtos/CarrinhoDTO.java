package br.com.supera.gamestore.dtos;

import br.com.supera.gamestore.models.Carrinho;
import br.com.supera.gamestore.models.ItemCarrinho;
import br.com.supera.gamestore.models.Pedido;
import br.com.supera.gamestore.models.Usuario;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class CarrinhoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal frete = BigDecimal.ZERO;
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private Usuario usuario;
    private Pedido pedido;
    private List<ItemCarrinhoDTO> itens;

    public CarrinhoDTO(Carrinho obj) {
        this.id = obj.getCarrinhoId();
        this.frete = obj.getFrete();
        this.subtotal = obj.getSubTotal();
        this.total = obj.getTotal();
        this.usuario = obj.getUsuario();
        this.pedido = obj.getPedido();
        this.itens = obj.getItens().stream().map(item -> Objects.nonNull(item.getItemCarrinhoId()) ? new ItemCarrinhoDTO(item) : null).collect(Collectors.toList());
    }

}

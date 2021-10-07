package br.com.supera.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@RequiredArgsConstructor
public class ItemCarrinho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemCarrinhoId;

    @ManyToOne
    @JsonIgnore
    private Carrinho carrinho;

    @ManyToOne
    @JsonIgnore
    private Produto produto;

    private BigDecimal valorTotal;

    private Integer quantidade;

}

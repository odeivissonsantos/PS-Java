package br.com.supera.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe entidade
 */

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "carrinho")
public class Carrinho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carrinhoId;

    @JsonIgnore
    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.REMOVE)
    private List<ItemCarrinho> itens;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "frete")
    private BigDecimal frete;

    @OneToOne
    @JsonIgnore
    private Pedido pedido;

}

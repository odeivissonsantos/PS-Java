package br.com.supera.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe entidade
 */

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "item_carrinho")
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

    @Column
    private BigDecimal precoUnitario;

}

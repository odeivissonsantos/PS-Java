package br.com.supera.gamestore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
    private List<ItemCarrinho> itensCarrinho;

    @ManyToOne
    @JsonIgnore
    private Usuario usuario;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @OneToOne
    @JsonIgnore
    private Pedido pedido;

}

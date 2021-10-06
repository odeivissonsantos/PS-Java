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
@Table(name = "item_carrinho")
public class ItemCarrinho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco")
    private BigDecimal preco;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "itemCarrinho", cascade = CascadeType.ALL)
    private List<Produto> produto;

    @ManyToOne
    @JsonBackReference
    private Pedido pedido;
}

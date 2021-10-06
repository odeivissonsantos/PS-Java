package br.com.supera.gamestore.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "produto")
@Data
@RequiredArgsConstructor
public class Produto implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "nome", unique = true)
   private String nome;

   @Column(name = "preco")
   private BigDecimal preco;

   @Column(name = "pontuacao")
   private Integer pontuacao;

   @Column(name = "imagem")
   private String urlImagem;

   @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
   private ItemCarrinho itemCarrinho;

}
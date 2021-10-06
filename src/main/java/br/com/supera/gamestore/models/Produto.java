package br.com.supera.gamestore.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "produto")
@Data
@RequiredArgsConstructor
class Produto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private String nome;

   @Column
   private BigDecimal preco;

   @Column
   private Integer pontuacao;

   @Column
   private String urlImagem;

}
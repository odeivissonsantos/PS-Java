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

   @Column
   private String nome;

   @Column
   private BigDecimal preco;

   @Column
   private Integer pontuacao;

   @Column
   private String urlImagem;

}
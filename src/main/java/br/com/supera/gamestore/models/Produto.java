package br.com.supera.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe entidade
 */

@Entity
@Table(name = "produto")
@Data
@RequiredArgsConstructor
public class Produto implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotNull(message = "Campo NOME é Obrigatório!")
   @Column(name = "nome", unique = true)
   private String nome;

   @NotNull(message = "Campo PREÇO é Obrigatório!")
   @Column(name = "preco")
   private BigDecimal preco;

   @NotNull(message = "Campo PONTUAÇÃO é Obrigatório!")
   @Column(name = "pontuacao")
   private Integer pontuacao;

   @NotNull(message = "Campo URL DA IMAGEM é Obrigatório!")
   @Column(name = "imagem")
   private String urlImagem;

   @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
   @JsonIgnore
   private List<ItemCarrinho> itens;

}
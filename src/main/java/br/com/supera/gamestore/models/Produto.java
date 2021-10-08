package br.com.supera.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
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

   @Column(name = "nome", unique = true)
   private String nome;

   @Column(name = "preco")
   private BigDecimal preco;

   @Column(name = "pontuacao")
   private Integer pontuacao;

   @Column(name = "imagem")
   private String urlImagem;

   @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
   @JsonIgnore
   private List<ItemCarrinho> itemCarrinho;

   @Override
   public String toString() {
      return "Produto{" +
              "id=" + id +
              ", nome='" + nome + '\'' +
              ", preco=" + preco +
              ", pontuacao=" + pontuacao +
              ", urlImagem='" + urlImagem + '\'' +
              ", itemCarrinho=" + itemCarrinho +
              '}';
   }
}
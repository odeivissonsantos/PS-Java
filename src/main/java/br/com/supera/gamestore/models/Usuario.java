package br.com.supera.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo")
    @NotNull(message = "Campo NOME COMPLETO é obrigatório")
    private String nomeCompleto;

    @Column(name = "email", unique = true)
    @NotNull(message = "Campo EMAIL é obrigatório")
    @Email(message = "Digite um email válido!")
    private String email;

    @Column(name = "cpf")
    @NotNull(message = "Campo CPF é obrigatório")
    @CPF(message = "Digite um CPF válido!")
    private String cpf;

    @Column(name = "telefone")
    @NotNull(message = "Campo TELEFONE é obrigatório")
    private String telefone;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Carrinho carrinho;

}

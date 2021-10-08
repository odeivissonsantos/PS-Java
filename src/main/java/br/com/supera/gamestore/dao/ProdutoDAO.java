package br.com.supera.gamestore.dao;

import br.com.supera.gamestore.models.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe responsável por passar todos os métodos JPA para os services.
 */

@Repository
public interface ProdutoDAO extends PagingAndSortingRepository<Produto, Long> {
}

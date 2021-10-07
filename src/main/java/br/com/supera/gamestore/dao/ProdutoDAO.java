package br.com.supera.gamestore.dao;

import br.com.supera.gamestore.models.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoDAO extends PagingAndSortingRepository<Produto, Long> {
}

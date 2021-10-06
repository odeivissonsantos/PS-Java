package br.com.supera.gamestore.dao;

import br.com.supera.gamestore.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Long> {
}

package br.com.supera.gamestore.dao;

import br.com.supera.gamestore.models.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoDAO extends JpaRepository<Carrinho, Long> {
}

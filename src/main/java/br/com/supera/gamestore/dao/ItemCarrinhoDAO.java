package br.com.supera.gamestore.dao;

import br.com.supera.gamestore.models.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCarrinhoDAO extends JpaRepository <ItemCarrinho, Long>{
}

package br.com.supera.gamestore.dao;

import br.com.supera.gamestore.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDAO extends JpaRepository<Pedido, Long> {

}

package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.CarrinhoDAO;
import br.com.supera.gamestore.dao.PedidoDAO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Carrinho;
import br.com.supera.gamestore.models.Pedido;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoDAO pedidoDAO;

    public PedidoService(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public List<Pedido> listarTodosPedidos() {
        return pedidoDAO.findAll();
    }


    /*
        verifica existe um Pedido na base de dados passando por parâmetro um id,
        caso contrario é lançado uma excessão
    */
    private Pedido verificaSeExistePedido(Long id) throws ObjectNotFoundException {
        return pedidoDAO.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido com "+ id +" não encontrado, Tipo: " + Pedido.class.getName()));
    }



}

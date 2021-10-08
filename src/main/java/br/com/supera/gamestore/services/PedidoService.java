package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.PedidoDAO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe utilizada para realizar a busca de Pedidos.
 */

@Service
public class PedidoService {

    // Construtor e Injeção de depência JPA.
    private final PedidoDAO pedidoDAO;

    public PedidoService(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    /*
     * @return: Retorna uma lista de pedidos cadastrados na base de dados.
     */
    public List<Pedido> listarTodosPedidos() {
        return pedidoDAO.findAll();
    }


    /*
     * @param: id
     * @return: verifica existe Pedido na base de dados, caso contrario é lançado uma excessão
     */
    private Pedido verificaSeExistePedido(Long id) throws ObjectNotFoundException {
        return pedidoDAO.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido com "+ id +" não encontrado, Tipo: " + Pedido.class.getName()));
    }



}

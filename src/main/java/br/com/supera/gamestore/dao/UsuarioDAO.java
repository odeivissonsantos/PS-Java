package br.com.supera.gamestore.dao;

import br.com.supera.gamestore.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 */

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

}

package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.UsuarioDAO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Deivisson Santos
 * @version: 1.0
 * @Email: deivissonsantos@hotmail.com
 * @Contato: (71) 99188-8419 (WhatsApp)
 * Classe utilizada para realizar o CRUD e regra de negócio para Usuários.
 */

@Service
public class UsuarioService {

    // Construtor e Injeção de depência JPA.
    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /*
     * @return: Retorna uma lista de usuarios cadastrados na base de dados.
     */
    public List<Usuario> listarTodosUsuarios() {
        return usuarioDAO.findAll();
    }

    /*
     * @param: id
     * @return: Busca um Usuario na base de dados.
     */
    public Usuario buscarUsuarioPorId(Long id) {
        return verificaSeExisteUsuario(id);
    }

    /*
     * @return: Retorna a criação de um usuario na base de dados.
     */
    public Usuario criarUsuario(Usuario obj) {
        obj.setId(null);
        obj = usuarioDAO.save(obj);
        return obj;
    }

    /*
     * @param: id e entidade
     * @return: Verifica se existe um usuario na base de dados e atualiza suas informações
     */
    public Usuario atualizarUsuario(Long id, Usuario obj) {
        Usuario newObj = verificaSeExisteUsuario(id);
        atualizaData(newObj, obj);
        return usuarioDAO.save(newObj);
    }

    //Verifica se existe um usuário na base de dados e deleta.
    public void deletarUsuario(Long id) {
        verificaSeExisteUsuario(id);
        usuarioDAO.deleteById(id);
    }

    /*
     * método com a responsabilidade em passar os novos dados de um
       usuário para um já existente na base de dados.
     */
    private void atualizaData(Usuario newObj, Usuario obj) {
        newObj.setNomeCompleto(obj.getNomeCompleto());
        newObj.setEmail(obj.getEmail());
        newObj.setCpf(obj.getCpf());
        newObj.setTelefone(obj.getTelefone());
    }

    /*
     * @param: id
     * @return: verifica existe Usuario na base de dados, caso contrario é lançado uma excessão
     */
    private Usuario verificaSeExisteUsuario(Long id) throws ObjectNotFoundException {
        return usuarioDAO.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuario com id: "+ id +" não encontrado, Tipo: " + Usuario.class.getName()));
    }

}

package hotel.controller;

import hotel.dao.UsuarioDAO;
import hotel.model.Enum.TipoUsuario;
import hotel.model.Usuario;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Luiz Guilherme on 11/06/2016.
 */
@Named
@ManagedBean
public class ClienteBean {
    private static final long serialVersionUID = 1L;

    private List<Usuario> listaUsuarios;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;

    @PostConstruct
    public void postConst(){
        try {
            listaUsuarios = usuarioDAO.listAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() throws Exception {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String cadastro() throws Exception {
        return null;
    }
}
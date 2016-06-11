package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.dao.QuartoDAO;
import hotel.dao.ReservaDAO;
import hotel.dao.UsuarioDAO;
import hotel.model.Quarto;
import hotel.model.Reserva;
import hotel.model.Usuario;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.List;

@Named
@ConversationScoped
public class ReservaBean extends BaseBean {
	private static final long serialVersionUID = 1L;

	@Inject
	private ReservaDAO reservaDAO;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private QuartoDAO quartoDAO;
	private List<Quarto> listQuartos;
	private List<Usuario> listUsuarios;
	private Reserva reserva;

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Quarto> getListQuartos() {
		return quartoDAO.listAll();
	}

	public void setListQuartos(List<Quarto> listQuartos) {
		this.listQuartos = listQuartos;
	}

	public List<Usuario> getListUsuarios() {
		return usuarioDAO.listAll();
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	@Override
	@PostConstruct
	public void postConst(){
		reserva = new Reserva();
		reserva.setQuarto(new Quarto());
		reserva.setUsuario(new Usuario());
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}

	@Override
	public String cadastro() throws Exception{
		try{
			reserva.setUsuario(sessionBean.getUsuarioLogado());
			Reserva reservaSalva = reservaDAO.merge(reserva);
			if(reservaSalva != null){
				return cadastroReserva;
			}
		}catch (Exception e){
			throw  e;
		}
		return indexPage;
	}
}

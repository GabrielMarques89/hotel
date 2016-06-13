package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.ExceptionHandlers.ConstraintViolationHandler;
import hotel.Util.MsgUtil;
import hotel.dao.QuartoDAO;
import hotel.dao.ReservaDAO;
import hotel.dao.UsuarioDAO;
import hotel.model.Enum.SituacaoReserva;
import hotel.model.Quarto;
import hotel.model.Reserva;
import hotel.model.Usuario;
import org.joda.time.LocalDate;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.List;

import static hotel.Util.Utilities.ConstraintViolationException;

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

	private List<Usuario> listaClientes;

	private List<Reserva> listaReservas;

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
		return usuarioDAO.listarClientes();
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listaClientes = listUsuarios;
	}

	public List<Reserva> getListaReservas() {
		return reservaDAO.listAll();
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
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
	public String salvar() throws Exception{
		if(!sessionBean.isAdmin()){
			reserva.setUsuario(sessionBean.getUsuarioLogado());
		}
		else {
			reserva.setUsuario(usuarioDAO.findById(reserva.getUsuario().getId()));
		}

		LocalDate now = new LocalDate();
		LocalDate dataInicial = new LocalDate(reserva.getDataInicial());

		if(dataInicial.isBefore(now)){
			MsgUtil.addErrorMessage("Erro, a data inicial não pode ser anterior a data atual.", "");
			return cadastroReserva;
		}
		if(reserva.getSituacaoReserva() == null){
			reserva.setSituacaoReserva(SituacaoReserva.AGENDADA);
		}

		try{
			Reserva reservaSalva = reservaDAO.merge(reserva);

			if(reservaSalva != null){
				MsgUtil.addInfoMessage("Dados salvos com sucesso!", "");
			}else{
				MsgUtil.addErrorMessage("Desculpe, mas não foi possível salvar os dados.", "");
			}
		}catch (EJBException ex){
			ConstraintViolationHandler handler = ConstraintViolationException(ex);
			if(handler == null){
				throw ex;
			}
//			TODO: Adequar o handler para ler o erro da constraint e lançar a mensagem especifica, mexer no metodo ConstraintViolationException dentro de utilities
			if(handler.getHasError()){
				MsgUtil.addErrorMessage("O quarto escolhido já possui reserva nesta data.", "");
			}
		}catch (Exception e){
			throw e;
		}
		return cadastroReserva;
	}

	public String irEditar(long id) throws Exception{
		reserva = reservaDAO.findById(id);
		if(reserva != null){
            return cadastroReserva;
        }
		MsgUtil.addErrorMessage("Desculpe, mas não a reserva não foi encontrada.", "");
		return listarReservas;
	}

	public String irAlterarStatus(long id) throws Exception{
		reserva = reservaDAO.findById(id);
		if(reserva != null){
			return alterarStatusReserva;
		}
		MsgUtil.addErrorMessage("Desculpe, mas não a reserva não foi encontrada.", "");
		return listarReservas;
	}

	public String alterarStatus() throws Exception{
		SituacaoReserva situ = reserva.getSituacaoReserva();
		reserva = reservaDAO.findById(reserva.getId());
		reserva.setSituacaoReserva(situ);
		reservaDAO.merge(reserva);
		return listarReservas;
	}

	public Boolean isEditing(){
		return reserva.getId()!= null && reserva.getId() > 0;
	}
}

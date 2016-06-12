package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.Util.MsgUtil;
import hotel.dao.QuartoDAO;
import hotel.dao.ReservaDAO;
import hotel.dao.UsuarioDAO;
import hotel.model.Enum.SituacaoReserva;
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
import java.util.Date;
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
		return usuarioDAO.listAll();
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
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
		boolean existeErro = false;

		//Data inicial é maior que data final?
		if(reserva.getDataInicial().after(reserva.getDataFinal())){
            MsgUtil.addErrorMessage("Erro! A 'Data inicial' não pode ser posterior à 'Data final'.", "");
            existeErro = true;
        }
		//Data inicial e final são iguais?
		if(reserva.getDataInicial().equals(reserva.getDataFinal())){
			MsgUtil.addErrorMessage("Erro! A 'Data inicial' e 'Data final' não podem ser iguais.", "");
			existeErro = true;
		}
		//Data inicial é menor que o data atual?
		if(reserva.getDataInicial().before(new Date()) && reserva.getDataInicial().equals(new Date())){
			MsgUtil.addErrorMessage("Erro! A 'Data inicial' não pode ser anterior ou igual à data atual.", "");
			existeErro = true;
		}

		if(!existeErro){
			reserva.setUsuario(sessionBean.getUsuarioLogado());
			Reserva reservaSalva = reservaDAO.merge(reserva);

			if(reservaSalva != null){
				MsgUtil.addInfoMessage("Dados salvos com sucesso!", "");
			}else{
				MsgUtil.addErrorMessage("Desculpe, mas não foi possível salvar os dados.", "");
			}
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
		reservaDAO.merge(reserva);
		return listarReservas;
	}
}

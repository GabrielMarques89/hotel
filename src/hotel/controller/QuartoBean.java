package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.ExceptionHandlers.ConstraintViolationHandler;
import hotel.Util.MsgUtil;
import hotel.dao.QuartoDAO;
import hotel.dao.TipoQuartoDAO;
import hotel.model.Quarto;
import hotel.model.TipoQuarto;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static hotel.Util.Utilities.ConstraintViolationException;

@Named
@ConversationScoped
public class QuartoBean extends BaseBean {
	private static final long serialVersionUID = 1L;

	@Inject
	private QuartoDAO quartoDAO;

	@Inject
	private TipoQuartoDAO tipoQuartoDAO;

	private Quarto quarto;

	private List<Quarto> listaQuartos;

	@Override
	@PostConstruct
	public void postConst(){
		quarto = new Quarto();
		quarto.setTipo(new TipoQuarto());
	}

	public List<Quarto> getListaQuartos() {
		return quartoDAO.listAll();
	}

	public void setListaQuartos(List<Quarto> listaQuartos) {
		this.listaQuartos = listaQuartos;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public String salvar()throws Exception{
        try{
            Quarto quartoSalva = quartoDAO.merge(quarto);

            if(quartoSalva != null){
                MsgUtil.addInfoMessage("Dados salvos com sucesso!", "");
            }else{
                MsgUtil.addErrorMessage("Desculpe, mas não foi possível salvar os dados.", "");
            }
            return cadastroQuarto;
        }catch (EJBException ex){
            ConstraintViolationHandler handler = ConstraintViolationException(ex);
            if(handler == null){
                throw ex;
            }
            if(handler.getConstraintName().equals("UK_NUMERO_QUARTO")){
                MsgUtil.addErrorMessage("Já existe um quarto registrado com este número.", "");
                return cadastroQuarto;
            }
            throw ex;
        }catch (Exception e){
            throw e;
        }
	}

	public String irEditar(long id) throws Exception{
		quarto = quartoDAO.findById(id);
		if(quarto != null){
            return cadastroQuarto;
        }
		MsgUtil.addErrorMessage("Desculpe, mas não o quarto não foi encontrado.", "");
		return listarQuartos;
	}

	public String excluir(long id) throws Exception{
		quarto = quartoDAO.findById(id);
		if(quarto != null){
            quartoDAO.remove(quarto);
        }else{
            MsgUtil.addErrorMessage("Desculpe, mas não o cliente não foi encontrado.", "");
        }
		return listarQuartos;
	}
}

package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.Util.MsgUtil;
import hotel.dao.QuartoDAO;
import hotel.dao.TipoQuartoDAO;
import hotel.model.Quarto;
import hotel.model.TipoQuarto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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
			Quarto quartoSalvo = quartoDAO.merge(quarto);
			if(quartoSalvo != null) {
				MsgUtil.addInfoMessage("Dados salvos com sucesso!", "");
			}else{
				MsgUtil.addErrorMessage("Desculpe, mas não foi possível salvar os dados.", "");
			}
		}catch (Exception e){
			MsgUtil.addWarnMessage("Ocorreu algum problema. Por favor, tente mais tarde.", "");
		}
		return cadastroQuarto;
	}
}

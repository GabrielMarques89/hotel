package hotel.controller;

import hotel.Util.MsgUtil;
import hotel.dao.TipoQuartoDAO;
import hotel.model.TipoQuarto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by grupoeuropa on 10/06/16.
 */

@Named
@ConversationScoped
public class TipoQuartoBean extends BaseBean{
	private static final long serialVersionUID = 1L;

	@Inject
	private TipoQuartoDAO tipoQuartoDAO;

	private TipoQuarto tipoQuarto;

	private List<TipoQuarto> listaTipoQuartos;

	public TipoQuarto getTipoQuarto() {
		return tipoQuarto;
	}

	public void setTipoQuarto(TipoQuarto tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}

	public List<TipoQuarto> getListaTipoQuartos() {
		return tipoQuartoDAO.listAll();
	}

	public void setListaTipoQuartos(List<TipoQuarto> listaTipoQuartos) {
		this.listaTipoQuartos = listaTipoQuartos;
	}

	public String salvar()throws Exception{
		TipoQuarto tipoQuartoSalvo = tipoQuartoDAO.merge(tipoQuarto);
		if(tipoQuartoSalvo != null){
            MsgUtil.addInfoMessage("Dados salvos com sucesso!", "");
        }else{
            MsgUtil.addErrorMessage("Desculpe, mas não foi possível salvar os dados.", "");
        }
		return cadastroTipoQuarto;
	}

	@Override
	@PostConstruct
	public void postConst() {
		tipoQuarto = new TipoQuarto();
	}
}

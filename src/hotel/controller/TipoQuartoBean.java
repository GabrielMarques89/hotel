package hotel.controller;

import hotel.dao.TipoQuartoDAO;
import hotel.model.TipoQuarto;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by grupoeuropa on 10/06/16.
 */

@Named
@ConversationScoped
public class TipoQuartoBean extends BaseBean{
	private static final long serialVersionUID = 1L;

	@Inject
	private TipoQuartoDAO tipoQuartoDAO;

	private TipoQuarto tipoQuarto = new TipoQuarto();

	public TipoQuarto getTipoQuarto() {
		return tipoQuarto;
	}

	public void setTipoQuarto(TipoQuarto tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}

	public String cadastro(){
		tipoQuartoDAO.merge(tipoQuarto);
		return indexPage;
	}


}

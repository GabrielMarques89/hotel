package hotel.controller;


import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class BaseBean implements Serializable {

	@Inject
	protected SessionBean sessionBean;

//	protected void logar(String codigoUnidadeFuncional, String comandoFuncionalidade, String log) {
//		this.logService.logar(this.sessionBean.getUsuarioLogado().getAcessoAtual(), codigoUnidadeFuncional, comandoFuncionalidade, log);
//	}

}

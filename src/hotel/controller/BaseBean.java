package hotel.controller;


import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	protected static String loginPage = "Views/login.xhtml";
	protected static String indexPage = "Views/index.xhtml";
	protected static String cadastroPage = "Views/registro.xhtml";
	@Inject
	protected SessionBean sessionBean;

//	protected void logar(String codigoUnidadeFuncional, String comandoFuncionalidade, String log) {
//		this.logService.logar(this.sessionBean.getUsuarioLogado().getAcessoAtual(), codigoUnidadeFuncional, comandoFuncionalidade, log);
//	}

}

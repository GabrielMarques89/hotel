package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.ExceptionHandlers.ConstraintViolationHandler;
import hotel.Util.MsgUtil;
import hotel.dao.CartaoMagneticoDAO;
import hotel.model.CartaoMagnetico;
import hotel.model.Enum.Situacao;
import hotel.model.Reserva;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

import static hotel.Util.Utilities.ConstraintViolationException;

/**
 * Created by grupoeuropa on 10/06/16.
 */

@Named
@ConversationScoped
public class CartaoMagneticoBean extends BaseBean {
	private static final long serialVersionUID = 1L;

	@Inject
	private CartaoMagneticoDAO cartaoMagneticoDAO;

	private CartaoMagnetico cartaoMagnetico;

	private List<CartaoMagnetico> listaCartoesMagneticos;

	public CartaoMagnetico getCartaoMagnetico() {
		return cartaoMagnetico;
	}

	public void setCartaoMagnetico(CartaoMagnetico cartaoMagnetico) {
		this.cartaoMagnetico = cartaoMagnetico;
	}

	@Override
	@PostConstruct
	public void postConst() {
		cartaoMagnetico = new CartaoMagnetico();
		cartaoMagnetico.setReserva(new Reserva());
	}

	public void salvar() {
        try{
	        if(cartaoMagnetico.getSituacao() == null){
		        cartaoMagnetico.setSituacao(Situacao.Ativo);
	        }
	        cartaoMagnetico.setCodigo(UUID.randomUUID().toString());
	        while(!cartaoMagneticoDAO.hasUniqueUuid(cartaoMagnetico)){
		        cartaoMagnetico.setCodigo(UUID.randomUUID().toString());
	        }
            CartaoMagnetico cartaoMagneticoSalva = cartaoMagneticoDAO.merge(cartaoMagnetico);

            if(cartaoMagneticoSalva != null){
                MsgUtil.addInfoMessage("Dados salvos com sucesso!", "");
            }else{
                MsgUtil.addErrorMessage("Desculpe, mas não foi possível salvar os dados.", "");
            }
        }catch (EJBException ex){
            ConstraintViolationHandler handler = ConstraintViolationException(ex);
            if(handler == null){
                throw ex;
            }
            if(handler.getConstraintName().equals("UK_CARTAO_RESERVA")){
                MsgUtil.addErrorMessage("Já existe um Cartao Magnetico registrado com este número.", "");
            }
            throw ex;
        }catch (Exception e){
	        throw e;
        }
	}

	public CartaoMagneticoDAO getCartaoMagneticoDAO() {
		return cartaoMagneticoDAO;
	}

	public void setCartaoMagneticoDAO(CartaoMagneticoDAO cartaoMagneticoDAO) {
		this.cartaoMagneticoDAO = cartaoMagneticoDAO;
	}

	public List<CartaoMagnetico> getListaCartoesMagneticos() {
		return cartaoMagneticoDAO.listAll();
	}

	public void setListaCartoesMagneticos(List<CartaoMagnetico> listaCartoesMagneticos) {
		this.listaCartoesMagneticos = listaCartoesMagneticos;
	}

	public String irEditar(long id) throws Exception{
		cartaoMagnetico = cartaoMagneticoDAO.findById(id);
		if(cartaoMagnetico != null){
            return cadastroCartaoMagnetico;
        }
		MsgUtil.addErrorMessage("Desculpe, mas não o quarto não foi encontrado.", "");
		return listarCartoesMagneticos;
	}

	public void alternarSitu(long id){
		cartaoMagnetico = cartaoMagneticoDAO.findById(id);
		if(cartaoMagnetico.getSituacao().equals(Situacao.Ativo)){
			cartaoMagnetico.setSituacao(Situacao.Inativo);
		}else{
			cartaoMagnetico.setSituacao(Situacao.Ativo);
		}
		cartaoMagneticoDAO.merge(cartaoMagnetico);
	}
}

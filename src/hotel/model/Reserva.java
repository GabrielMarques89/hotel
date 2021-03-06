package hotel.model;

import hotel.model.Enum.SituacaoReserva;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by grupoeuropa on 09/06/16.
 */
@Entity
@Table(name = "TBL_RESERVAS", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_QUARTO", "DT_INICIO"}, name = "UK_DATA_QUARTO"))
public class Reserva extends Entidade{

	@Column(name = "DT_INICIO", nullable = false)
	private Date dataInicial;

	@Column(name = "QT_DEPENDENTES")
	private int quantiaDependentes;

	@Column(name = "TP_SITUACAO")
	private SituacaoReserva situacaoReserva;

	@JoinColumn(name = "ID_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "FK_RESERVA_USUARIO"))
	@OneToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	@JoinColumn(name = "ID_QUARTO", nullable = false, foreignKey = @ForeignKey(name = "FK_RESERVA_QUARTO"))
	@OneToOne(fetch = FetchType.EAGER)
	private Quarto quarto;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "reserva")
	private List<CartaoMagnetico> listaCartoesDaReserva;

	public List<CartaoMagnetico> getListaCartoesDaReserva() {
		return listaCartoesDaReserva;
	}

	public void setListaCartoesDaReserva(List<CartaoMagnetico> listaCartoesDaReserva) {
		this.listaCartoesDaReserva = listaCartoesDaReserva;
	}

	public float calcularPreco(){
		return quarto.getTipo().getDiaria() * quantiaDependentes;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

    public Boolean isOfKind(SituacaoReserva situ){
        return this.getSituacaoReserva().equals(situ);
    }

	public int getQuantiaDependentes() {
		return quantiaDependentes;
	}

	public void setQuantiaDependentes(int quantiaDependentes) {
		this.quantiaDependentes = quantiaDependentes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public SituacaoReserva getSituacaoReserva() {
		return situacaoReserva;
	}

	public void setSituacaoReserva(SituacaoReserva situacaoReserva) {
		this.situacaoReserva = situacaoReserva;
	}
}

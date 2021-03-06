package hotel.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by grupoeuropa on 10/06/16.
 */
@Entity
@Table(name = "TBL_PAGAMENTO")
public class Pagamento extends Entidade{
	@Column(name = "VL_PAGAMENTO", nullable = false)
	private float valor;

	@Column(name = "DT_PAGAMENTO")
	@Temporal(TemporalType.DATE)
	private Date data;

	@JoinColumn(name = "ID_PAGAMENTO", nullable = false, foreignKey = @ForeignKey(name = "FK_PAGAMENTO_X_RESERVA"))
	@OneToOne(fetch = FetchType.EAGER)
	private Reserva reserva;

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
}

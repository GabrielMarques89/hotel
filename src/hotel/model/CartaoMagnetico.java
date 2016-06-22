package hotel.model;

import hotel.model.Enum.Situacao;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by grupoeuropa on 09/06/16.
 */
@Entity
@Table(name = "TBL_CARTAO_MAGNETICO", uniqueConstraints = @UniqueConstraint(columnNames = {"DS_CODIGO", "ID_RESERVA"}, name = "UK_CARTAO_X_RESERVA"))
public class CartaoMagnetico extends Entidade {

	@Size(max = 36)
    @Column(name = "DS_CODIGO", nullable = false, unique = true)
	private String codigo;

	@Column(name = "VL_CONSUMO", nullable = true)
	private float consumo;

	@Column(name = "TP_SITUACAO")
	private Situacao situacao;

	@JoinColumn(name = "ID_RESERVA", nullable = false, foreignKey = @ForeignKey(name = "FK_CARTAO_X_RESERVA"))
	@ManyToOne(fetch = FetchType.EAGER)
	private Reserva reserva;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getConsumo() {
		return consumo;
	}

	public void setConsumo(float consumo) {
		this.consumo = consumo;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
}

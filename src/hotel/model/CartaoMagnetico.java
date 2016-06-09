package hotel.model;

import javax.persistence.*;

/**
 * Created by grupoeuropa on 09/06/16.
 */
@Entity
@Table(name = "TBL_CARTAO_MAGNETICO")
public class CartaoMagnetico extends Entidade {
	@Column(name = "DS_CODIGO", nullable = false)
	private String codigo;

	@Column(name = "VL_CONSUMO", nullable = false)
	private float consumo;

	@JoinColumn(name = "ID_QUARTO", nullable = false, foreignKey = @ForeignKey(name = "FK_CARTAO_X_QUARTO"))
	@OneToOne(fetch = FetchType.EAGER)
	private Quarto quarto;

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

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
}

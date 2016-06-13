package hotel.model;

import javax.persistence.*;

/**
 * Created by grupoeuropa on 09/06/16.
 */
@Entity
@Table(name = "TBL_QUARTOS", uniqueConstraints = @UniqueConstraint(columnNames = {"DS_NUMERO"}, name = "UK_NUMERO_QUARTO"))
public class Quarto extends Entidade {
	@Column(name = "DS_NUMERO", nullable = false)
	private String numero;

	@JoinColumn(name = "ID_TIPO_QUARTO", nullable = false, foreignKey = @ForeignKey(name = "FK_QUARTO_X_TIPO_QUARTO"))
	@OneToOne(fetch = FetchType.EAGER)
	private TipoQuarto Tipo;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoQuarto getTipo() {
		return Tipo;
	}

	public void setTipo(TipoQuarto tipo) {
		Tipo = tipo;
	}
}

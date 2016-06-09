package hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by grupoeuropa on 09/06/16.
 */
@Entity
@Table(name = "TBL_TIPO_QUARTO")
public class TipoQuarto extends Entidade {
	@Column(name = "NM_TIPO_QUARTO", nullable = false)
	private String Nome;

	@Column(name = "NM_CAMAS", nullable = false)
	private int camas;

	@Column(name = "VL_DIARIA", nullable = false)
	private float diaria;

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public int getCamas() {
		return camas;
	}

	public void setCamas(int camas) {
		this.camas = camas;
	}

	public float getDiaria() {
		return diaria;
	}

	public void setDiaria(float diaria) {
		this.diaria = diaria;
	}
}

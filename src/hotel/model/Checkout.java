package hotel.model;
/**
 * Created by grupoeuropa on 20/06/16.
 * Template by Gabriel Marques
 */

import org.joda.time.Days;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_CHECKOUT", uniqueConstraints = @UniqueConstraint(name = "UK_CHECKOUT_X_RESERVA", columnNames = {"ID_RESERVA", "`num_pk`"} ))
public class Checkout extends Entidade {

	@Column(name = "ValorTotal", nullable = false)
	private float valorTotal;

	@Column(name = "Data", nullable = false)
	private Date data;
	@JoinColumn(name = "ID_RESERVA", nullable = false, foreignKey = @ForeignKey(name = "FK_CHECKOUT_X_RESERVA"))
	@OneToOne(fetch = FetchType.EAGER)
	private Reserva reserva;

	public Date getData() {
		return data;
	}

	public void setData(Date date) {
		this.data = date;
	}

	public float getValorTotal() throws Exception {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public float calculaValorTotal() throws Exception {
		return totalDiarias()*totalConsumo();
	}

	public float totalDiarias(){
		return reserva.calcularPreco() * totalDeDias();
	}

	public float totalConsumo() throws Exception{
		//TODO: A lista de cartoes de uma reserva deve constar na modelagem - IMPORTANTISSIMO.
//		float totalConsumo = 0;
//		for(CartaoMagnetico item : listaDeCartoes){
//			totalConsumo = totalConsumo + item.getConsumo();
//		}
//		return totalConsumo;
		return 0;
	}

	public int totalDeDias(){
		LocalDate agora = new LocalDate();
		LocalDate inicioReserva = new LocalDate(reserva.getDataInicial());
		return Days.daysBetween(inicioReserva,agora).getDays();
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
}
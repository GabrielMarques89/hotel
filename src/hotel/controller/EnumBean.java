package hotel.controller;

import hotel.model.Enum.*;

import javax.annotation.ManagedBean;
import javax.inject.Named;

/**
 * Created by Luiz Guilherme on 11/06/2016.
 */
@Named
@ManagedBean
public class EnumBean {
    public Genero[] getGeneros() {
        return Genero.values();
    }

    public SituacaoReserva[] getSituacoesReserva() {
        return SituacaoReserva.values();
    }

    public StatusHospede[] getStatusHospede() {
        return StatusHospede.values();
    }

    public TipoPagamento[] getTiposPagamento() {
        return TipoPagamento.values();
    }

    public TipoUsuario[] getTiposUsuario() {
        return TipoUsuario.values();
    }
}

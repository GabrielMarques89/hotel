package hotel.controller;

import hotel.model.Enum.Genero;

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
}

package hotel.model;

/**
 * Created by grupoeuropa on 09/06/16.
 */

import hotel.model.Enum.Genero;
import hotel.model.Enum.StatusHospede;
import hotel.model.Enum.TipoUsuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "TBL_USUARIO")
@PrimaryKeyJoinColumn(name = "ID_USUARIO")
public class Usuario extends Entidade {

    @Column(name = "TP_STATUS")
    private StatusHospede status;

    @Column(name="TP_USUARIO")
    private TipoUsuario tipoUsuario;

    @Column(name = "NM_PESSOA")
    private String nomeCompleto;

    @Column(name = "EN_GENERO")
    private Genero genero;

    @Column(name = "DT_NASCIMENTO")
    private Date nascimento;

    @Column(name = "DS_CPF",unique = true)
    private String cpf;

    @Column(name = "DS_TELEFONE")
    private String telefone;

    @Column(name = "DS_ENDERECO")
    private String endereco;

    @Column(name = "DS_EMAIL")
    private String email;

    @Column(name = "DS_SENHA")
    private String senha;

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public StatusHospede getStatus() {
        return status;
    }

    public void setStatus(StatusHospede status) {
        this.status = status;
    }
}


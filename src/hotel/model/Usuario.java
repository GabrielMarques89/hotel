package hotel.model;

/**
 * Created by grupoeuropa on 09/06/16.
 */

import hotel.model.Enum.Genero;
import hotel.model.Enum.StatusHospede;
import hotel.model.Enum.TipoUsuario;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_USUARIOS")
public class Usuario extends Entidade {

    @Column(name = "TP_STATUS", nullable = false)
    private StatusHospede status;

    @Column(name = "TP_USUARIO", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(name = "NM_PESSOA", nullable = false)
    private String nomeCompleto;

    @Column(name = "EN_GENERO", nullable = false)
    private Genero genero;

    @Column(name = "DT_NASCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date nascimento;

    @Column(name = "DS_CPF", unique = true, nullable = false)
    private String cpf;

    @Column(name = "DS_TELEFONE")
    private String telefone;

    @Column(name = "DS_ENDERECO")
    private String endereco;

    @Column(name = "DS_EMAIL", nullable = false)
    private String email;

    @Column(name = "DS_SENHA", nullable = false)
    private String senha;

    public TipoUsuario getTipoUsuario() {
        return this.tipoUsuario;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Date getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return this.senha;
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
        return this.nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public StatusHospede getStatus() {
        return this.status;
    }

    public void setStatus(StatusHospede status) {
        this.status = status;
    }
}

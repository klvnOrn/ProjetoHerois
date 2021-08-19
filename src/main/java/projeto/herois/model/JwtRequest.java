package projeto.herois.model;
import java.io.Serializable;



public class JwtRequest implements Serializable {

private static final long serialVersionUID = 5926468583005150707L;


private String usuario;
private String senha;

public JwtRequest() {}


public JwtRequest(String usuario, String senha) {
	this.setUsuario(usuario);
	this.setSenha(senha);
}

public String getUsuario() {
return this.usuario;
}

public void setUsuario(String usuario) {
this.usuario = usuario;
}

public String getSenha() {
return this.senha;
}

public void setSenha(String senha) {
this.senha = senha;
}

}
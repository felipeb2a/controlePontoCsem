/*@author FELIPE*/
package model;

public class Usuario{

    private int id;
    private String login;
    private String senha;
    private Niveis nivel;
    private String email;

    public Niveis getNivel() {
        return nivel;
    }

    public void setNivel(Niveis nivel) {
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Login{" + "id=" + id + ", login=" + login + ", senha=" + senha + ", nivel=" + nivel + ", email=" + email + '}';
    }

}

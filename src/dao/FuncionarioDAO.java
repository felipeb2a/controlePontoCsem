/*@author FELIPE*/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

public class FuncionarioDAO extends AcessDB {

    public List<Funcionario> ObterListFuncionario(String dbName) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List funcionarioList = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(dbName);

        // contruindo a consulta
        String sql = "select * from funcionario f order by f.idFuncionario";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();
        
        //chamar metodo lista funcionario
        funcionarioList = consultaListFuncionario(resultado);
        
        // Encerrando a conexão.
        conexao.close();
        return funcionarioList;
    }

    public Funcionario obterNomeFuncionario(Funcionario funcionario, String dbName) throws SQLException, ClassNotFoundException {

        // lista de retorno
        Funcionario funcionarioRetorno = new Funcionario();

        // conectando ao banco de dados
        Connection conexao = conectar(dbName);

        // contruindo a consulta
        String sql = "select * from funcionario f where f.nome = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //passando os parametros para a consulta
        stm.setString(1, funcionario.getNome());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        //chamar metodo lista funcionario
        funcionarioRetorno = consultaFuncionario(resultado);

        // Encerrando a conexão.
        conexao.close();
        return funcionarioRetorno;
    }

    public List<Funcionario> consultaListFuncionario(ResultSet resultado) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List funcionarioList = new ArrayList();

        // criando objeto de retorno
        while (resultado.next()) {
            Funcionario funcionarioRetorno;
            funcionarioRetorno = new Funcionario();
            funcionarioRetorno.setId(resultado.getInt("idFuncionario"));
            funcionarioRetorno.setNome(resultado.getString("nome"));
            funcionarioRetorno.setCargo(resultado.getString("cargo"));
            funcionarioRetorno.setJornadaDeTrabalho(resultado.getDate("jornadaDeTrabalho"));

            funcionarioList.add(funcionarioRetorno);
        }

        return funcionarioList;
    }
    
    public Funcionario consultaFuncionario(ResultSet resultado) throws SQLException, ClassNotFoundException {

        Funcionario funcionarioRetorno = new Funcionario();
        // criando objeto de retorno
        while (resultado.next()) {
            funcionarioRetorno = new Funcionario();
            funcionarioRetorno.setId(resultado.getInt("idFuncionario"));
            funcionarioRetorno.setNome(resultado.getString("nome"));
            funcionarioRetorno.setCargo(resultado.getString("cargo"));
            funcionarioRetorno.setJornadaDeTrabalho(resultado.getDate("jornadaDeTrabalho"));
        }

        return funcionarioRetorno;
    }

}

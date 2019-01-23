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

    public List<Funcionario> ObterNomeFuncionario(String dbName) throws SQLException, ClassNotFoundException {
  
        // lista de retorno
        List funcionarioList = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(dbName);

        // contruindo a consulta
        String sql = "select f.idFuncionario, f.nome from funcionario f";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Funcionario funcionarioRetorno;
            funcionarioRetorno = new Funcionario();
            funcionarioRetorno.setNome(resultado.getString("nome"));

            funcionarioList.add(funcionarioRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return funcionarioList;
    }

}
package database.connection.model.dao;

import database.connection.ConnectionFactory;
import database.connection.model.bean.Ponto_Interesse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ponto_InteresseDAO {

    public void create(Ponto_Interesse p){
        //abertura de conexão
        Connection con = ConnectionFactory.getConnection();
        //manipular e executar as SQLs
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tb_ponto_interesse (id_ponto_interesse, id_regiao, id_tag, id_departamento, " +
                    "id_laboratorio, nome, descricao)" +
                    "VALUES (?, ?, ?, ?, ?, ?,?)");
            stmt.setInt(1,p.getId_ponto_interesse());
            stmt.setInt(2,p.getId_regiao());
            stmt.setInt(3,p.getId_tag());
            stmt.setInt(4,p.getId_departamento());
            stmt.setInt(5,p.getId_laboratorio());
            stmt.setString(6,p.getNome());
            stmt.setString(7,p.getDescricao());

            stmt.executeUpdate();

            System.out.println("Insert na tb_ponto_interesse realizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro no Insert na tb_ponto_interesse!");
        }finally {
            //independente do que ocorrer ele irá fechar aconexão
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Ponto_Interesse> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        List<Ponto_Interesse> pontos = new ArrayList<>();

        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_ponto_interesse");
            rs = stmt.executeQuery();

            while(rs.next()){
                Ponto_Interesse ponto = new Ponto_Interesse();
                ponto.setId_ponto_interesse(rs.getInt("id_ponto_interesse"));
                ponto.setId_regiao(rs.getInt("id_regiao"));
                ponto.setId_tag(rs.getInt("id_tag"));
                ponto.setId_departamento(rs.getInt("id_departamento"));
                ponto.setId_laboratorio(rs.getInt("id_laboratorio"));
                ponto.setNome(rs.getString("nome"));
                ponto.setDescricao(rs.getString("descricao"));
                pontos.add(ponto);
            }
            System.out.println("Select na tb_ponto_interesse realizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro no Select da tb_ponto_interesse");
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pontos;
    }

}

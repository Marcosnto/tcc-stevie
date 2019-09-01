package database.connection.model.dao;

import database.connection.ConnectionFactory;
import database.connection.model.bean.Laboratorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaboratorioDAO {

    public List<Laboratorio> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        List<Laboratorio> laboratorios = new ArrayList<>();

        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_laboratorio");
            rs = stmt.executeQuery();

            while(rs.next()){
                Laboratorio laboratorio = new Laboratorio();
                laboratorio.setId_laboratorio(rs.getInt("id_laboratorio"));
                laboratorio.setNome(rs.getString("nome"));
                laboratorios.add(laboratorio);
            }

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro no Select da tb_laboratorio");
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return laboratorios;
    }

}

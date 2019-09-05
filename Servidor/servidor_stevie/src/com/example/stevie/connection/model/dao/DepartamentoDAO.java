package com.example.stevie.connection.model.dao;

import com.example.stevie.connection.ConnectionFactory;
import com.example.stevie.connection.model.bean.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {

    public List<Departamento> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        List<Departamento> departamentos = new ArrayList<>();

        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_departamento");
            rs = stmt.executeQuery();

            while(rs.next()){
                Departamento departamento = new Departamento();
                departamento.setId_departamento(rs.getInt("id_departamento"));
                departamento.setNome(rs.getString("nome"));
                departamentos.add(departamento);
            }

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro no Select da tb_departamento");
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return departamentos;
    }
}

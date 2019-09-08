package database.connection.model.dao;

import database.connection.ConnectionFactory;
import database.connection.model.bean.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDAO {

    public List<Tag> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        List<Tag> tags = new ArrayList<>();

        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_tag");
            rs = stmt.executeQuery();

            while(rs.next()){
                Tag tag = new Tag();
                tag.setId_tag(rs.getInt("id_tag"));
                tag.setCod_tag(rs.getString("cod_tag"));
                tag.setId_tag_norte(rs.getString("tag_norte"));
                tag.setId_tag_sul(rs.getString("tag_sul"));
                tag.setId_tag_leste(rs.getString("tag_leste"));
                tag.setId_tag_oeste(rs.getString("tag_oeste"));
                tag.setNome(rs.getString("nome"));
                tag.setDescricao(rs.getString("descricao"));
                tag.setRegiao(rs.getString("regiao"));
                tag.setTipo_tag(rs.getString("tipo_tag"));
                tags.add(tag);
            }

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro no Select da tb_tag");
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return tags;
    }



}

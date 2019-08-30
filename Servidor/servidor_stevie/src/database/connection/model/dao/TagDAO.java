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
    public void create(Tag t){
        //abertura de conexão
        Connection con = ConnectionFactory.getConnection();
        //manipular e executar as SQLs
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tb_tag (id_tag, cod_tag, tag_frente, tag_tras, tag_esquerda, tag_direita) " +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, t.getId_tag());
            stmt.setString(2, t.getCod_tag());
            stmt.setString(3, t.getId_tag_frente());
            stmt.setString(4, t.getId_tag_tras());
            stmt.setString(5, t.getId_tag_esquerda());
            stmt.setString(6, t.getId_tag_direita());

            stmt.executeUpdate();

            System.out.println("Insert na tb_tag realizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro no Insert na tb_tag!");
        }finally {
            //independente do que ocorrer ele irá fechar aconexão
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

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
                tag.setId_tag_frente(rs.getString("tag_frente"));
                tag.setId_tag_tras(rs.getString("tag_tras"));
                tag.setId_tag_esquerda(rs.getString("tag_esquerda"));
                tag.setId_tag_direita(rs.getString("tag_direita"));
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

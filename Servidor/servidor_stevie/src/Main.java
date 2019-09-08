import java.io.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import busca.BFS;
import com.sun.xml.internal.fastinfoset.util.StringArray;
import database.connection.model.bean.Tag;
import database.connection.model.dao.TagDAO;
import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyAdapter;
import net.contentobjects.jnotify.JNotifyException;
import servidor.Servidor;

public class Main {
    static ArrayList<Tag> tags_banco = new ArrayList<>();
    static List<Tag> tags = new ArrayList<>();
    static Tag tag = new Tag();
    static TagDAO dao = new TagDAO();
    Path path = Paths.get("C:\\stevie");

    public static void main(String[] args) {
        // carrega todas as tags do banco
        carregarTags();
        Thread servidor = new Thread(new Servidor(new BFS(tags),tags));
        servidor.start();
    }


    public static void carregarTags() {
        for (Tag t : dao.read()) {
            tags.add(t);
            //tags_banco.add(t);
        }
    }
}
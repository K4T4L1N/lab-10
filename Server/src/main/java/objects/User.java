package objects;

import database.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

    Integer id;
    String name;

    public User(String name) throws SQLException {
        Connection connection = ConnectionDB.getInstance().connection;
        Statement statement = connection.createStatement();
        String sql = "SELECT max(id)+1 from users";
        ResultSet result = statement.executeQuery(sql);
        if (result.next())
            this.id = result.getInt(1);
        this.name = name;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

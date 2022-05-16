package util;

import database.ConnectionDB;
import objects.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Select {
    static public User getUserByName(String name) throws SQLException {
        Connection connection = ConnectionDB.getInstance().connection;
        Statement statement = connection.createStatement();
        String sql = String.format("Select * from users where name='%s'", name);
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            return new User(result.getInt("id"), result.getString("name"));
        }
        return null;
    }

    static public User getUserById(int id) throws SQLException {
        Connection connection = ConnectionDB.getInstance().connection;
        Statement statement = connection.createStatement();
        String sql = String.format("Select * from users where id=%d", id);
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            return new User(result.getInt("id"), result.getString("name"));
        }
        return null;
    }

    static public int getIdByName(String name) throws SQLException {
        Connection connection = ConnectionDB.getInstance().connection;
        Statement statement = connection.createStatement();
        String sql = String.format("Select * from users where name='%s'", name);
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            return result.getInt("id");
        }
        return -1;
    }

    static public List<User> getFriendsById(int id) throws SQLException {
        List<User> friends = new ArrayList<>();
        Connection connection = ConnectionDB.getInstance().connection;
        Statement statement = connection.createStatement();
        String sql = String.format("Select ID_friend2 from friends where ID_FRIEND1=%d", id);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            User friend = Select.getUserById(result.getInt(1));
            if (friend != null) {
                friends.add(friend);
            }
        }
        return friends;
    }

    static public List<String> getMessages(int id) throws SQLException {
        List<String> messages = new ArrayList<>();
        Connection connection = ConnectionDB.getInstance().connection;
        Statement statement = connection.createStatement();
        String sql = String.format("Select * from messages where ID_receiver=%d", id);
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            User friend = Select.getUserById(result.getInt(1));
            if (friend != null) {
                messages.add(friend.getName() + ": " + result.getString(3));
            }
        }
        return messages;
    }
}

package util;

import database.ConnectionDB;
import objects.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Add {
    static public void addUser(User user) throws SQLException {
        Connection connection = ConnectionDB.getInstance().connection;
        Statement statement = connection.createStatement();
        String sql = String.format("INSERT INTO USERS VALUES(%d,'%s')", user.getId(), user.getName());
        statement.execute(sql);
    }

    static public void addFriend(User user, User friend) throws SQLException {
        //TODO verify existence of the row before
        Connection connection = ConnectionDB.getInstance().connection;
        Statement statement = connection.createStatement();
        String sql = String.format("Select * from friends where id_friend1=%d and id_friend2=%d", user.getId(), friend.getId());
        ResultSet result = statement.executeQuery(sql);
        if (!result.next()) {
            sql = String.format("INSERT INTO friends VALUES(%d,%d)", user.getId(), friend.getId());
            statement.execute(sql);
            sql = String.format("INSERT INTO friends VALUES(%d,%d)", friend.getId(), user.getId());
            statement.execute(sql);
        }
    }

    public static String addMessage(User user, String message) throws SQLException {
        Connection connection = ConnectionDB.getInstance().connection;
        List<User> friends = Select.getFriendsById(user.getId());
        if (!friends.isEmpty()) {
            Statement statement = connection.createStatement();
            String sql;
            for (User friend : friends) {
                sql = String.format("Insert into messages values(%d,%d,'%s')", user.getId(), friend.getId(), message);
                statement.execute(sql);
            }
            return "Message sent to all friends";
        } else {
            return "No message sent, you have no friends";
        }

    }
}

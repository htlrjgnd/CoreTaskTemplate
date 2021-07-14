package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String SQL;
    private final Statement statement = Util.getStatement();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        SQL = "CREATE TABLE users " +
                "(id BIGINT not NULL AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age TINYINT, PRIMARY KEY(id))";
        try {
            statement.executeUpdate(SQL);
            System.out.println("DATABASE users created");
        } catch (SQLException throwables) {
            System.out.println("Databases with this name already exist");
            //throwables.printStackTrace();
        }

    }

    public void dropUsersTable() {
        SQL = "DROP TABLE users";
        try {
            statement.executeUpdate(SQL);
            System.out.println("DATABASE users drop");
        } catch (SQLException throwables) {
            System.out.println("There is no database with this name");
            //throwables.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String VALUES = String.format("VALUES('%s', '%s', '%d')", name, lastName, age);
            SQL = "INSERT INTO users(name, lastname, age) " + VALUES;
            statement.executeUpdate(SQL);
            System.out.printf("User с именем - %s добавлен в базу данных \n", name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        SQL = "DELETE FROM users WHERE id=" + id;
        try {
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        User user;
        SQL = "SELECT * FROM users";
        try {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                user = new User(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(User u : list){
            System.out.println(u);
        }
        return list;
    }

    public void cleanUsersTable() {
        SQL = "TRUNCATE TABLE users";
        try {
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

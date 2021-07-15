package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Charles", "Manson", (byte)87);
        userService.saveUser("Theodore", "Bundy", (byte)88);
        userService.saveUser("Dennis", "Rader", (byte)89);
        userService.saveUser("Edmund", "Kemper ", (byte)90);
        userService.getAllUsers();
        //userService.cleanUsersTable();
        //userService.dropUsersTable();

    }
}

package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Борис", "Кочкин", (byte) 43);
        userService.saveUser("Егор", "Жук", (byte) 53);
        userService.saveUser("Миша", "Сенников", (byte) 73);
        userService.saveUser("Костя", "Бард", (byte) 93);
////
        userService.getAllUsers();
//
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {



    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery( "CREATE TABLE IF NOT EXISTS User " +
                "( Id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(100), " +
                "lastName VARCHAR(100), " +
                "age TINYINT )" );
        query.executeUpdate();
        transaction.commit();
        session.close();


    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery( "DROP TABLE IF EXISTS  User");
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        System.out.println("User с именем "+name+" добавлен в базу данных");
        transaction.commit();
        session.close();



    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);

        session.delete(user);
        transaction.commit();
        session.close();


    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        List<User> users ;
        Transaction transaction = session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class);
            users = query.getResultList();
        System.out.println(users.toString());

        session.close();
        return users;

    }


    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery( "TRUNCATE TABLE User");
        query.executeUpdate();
        transaction.commit();
        session.close();


    }

}

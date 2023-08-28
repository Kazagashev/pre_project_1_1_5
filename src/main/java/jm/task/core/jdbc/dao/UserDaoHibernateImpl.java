package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
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
        try (Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery( "CREATE TABLE IF NOT EXISTS User " +
                    "( Id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100), " +
                    "lastName VARCHAR(100), " +
                    "age TINYINT )" );
            query.executeUpdate();
            transaction.commit();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery( "DROP TABLE IF EXISTS  User");
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            System.out.println("User с именем "+name+" добавлен в базу данных");
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public List<User> getAllUsers() {
        List <User> users = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class);
            users = query.getResultList();
            System.out.println(users.toString());
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        return users;
    }


    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery( "TRUNCATE TABLE User");
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

}

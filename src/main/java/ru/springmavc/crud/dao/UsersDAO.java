package ru.springmavc.crud.dao;

import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import ru.springmavc.crud.models.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
//import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
//@EnableTransactionManagement
//@Transactional
public class UsersDAO implements UsersDAOInterface {
//    private static int PEOPLE_COUNT;
//
//    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "GOGUDAserver123!";
//
//    private static Connection connection;

    @PersistenceContext
    private EntityManager entityManager;

//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    @Override
    public List<Users> index() {
        List<Users> users = new ArrayList<>();

        try {
            TypedQuery<Users> entity = entityManager.createQuery("SELECT u from Users u", Users.class);
            users = entity.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM Person";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            while (resultSet.next()) {
//                Person person = new Person();
//
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setEmail(resultSet.getString("email"));
//                person.setAge(resultSet.getInt("age"));
//
//                people.add(person);
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        return users;
    }

    @Override
    public Users show(int id) {
        Users users = null;

        List<Users> people = new ArrayList<>();


        TypedQuery<Users> entity = entityManager.createQuery("select p from Users p where p.id = :id", Users.class);
        entity.setParameter("id", id);
        people = entity.getResultList();
        System.out.println(people.get(0).getName());
        users = new Users();
        users.setId(people.get(0).getId());
        users.setName(people.get(0).getName());
        users.setEmail(people.get(0).getEmail());
        users.setAge(people.get(0).getAge());

//        person = new Person();
//        person.setName("sd");


//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");
//
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            resultSet.next();
//
//            person = new Person();
//
//            person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setEmail(resultSet.getString("email"));
//            person.setAge(resultSet.getInt("age"));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        System.out.println(person.getId());
        return users;
    }


    @Override
    @Transactional
    public void save(Users users) {
//        person = new Person();
//        System.out.println(person.getName());
//        entityManager.persist(person);

//        person.setId(person.getId());
        users.setName(users.getName());
        users.setAge(users.getAge());
        users.setEmail(users.getEmail());

        System.out.println(users.getId());
//        EntityTransaction tr = this.entityManager.getTransaction();
//        System.out.println("?????????!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        try {
//            tr.begin();
//        Session session=entityManager.unwrap(Session.class);
//        session.update(person);

//        entityManager.merge(person);
        entityManager.persist(users);
        entityManager.flush();


//            tr.commit();
//        } catch (Exception e) {
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            tr.rollback();
//            throw new RuntimeException(e);
//        }
//        entityManager.persist(person);

//        entityManager.createNativeQuery("INSERT INTO person (name, age, email) VALUES (?,?,?)")
//                .setParameter(1, person.getName())
//                .setParameter(2, person.getAge())
//                .setParameter(3, person.getEmail())
//                .executeUpdate();


//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?)");
//
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    @Override
    @Transactional
    public void update(int id, Users updatedUsers) {
        System.out.println(updatedUsers.getId() + "  !!!!!!!!!!!!!!!!!!!!! " + id);
        updatedUsers.setName(updatedUsers.getName());
        updatedUsers.setAge(updatedUsers.getAge());
        updatedUsers.setEmail(updatedUsers.getEmail());

        entityManager.merge(updatedUsers);
        entityManager.flush();


//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");
//
//            preparedStatement.setString(1, updatedPerson.getName());
//            preparedStatement.setInt(2, updatedPerson.getAge());
//            preparedStatement.setString(3, updatedPerson.getEmail());
//            preparedStatement.setInt(4, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        Users users = entityManager.find(Users.class, id);
//        person = entityManager.find(person., 5);
        System.out.println(users + " kkkkkkkkkkkk");
        entityManager.remove(users);
        entityManager.flush();

//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");
//
//            preparedStatement.setInt(1, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

    }
}
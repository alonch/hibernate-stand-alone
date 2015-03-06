/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.oosd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.jboss.logging.Logger;

/**
 *
 * @author alonch
 */
public class Lab2Oosd {
    private static final String PERSISTENCE_UNIT_NAME = "mysql";
    private static EntityManagerFactory factory;

    public static void connectHibernate() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console
        Query q = em.createQuery("select t from todo t");
        List<Todo> todoList = q.getResultList();
        for (Todo todo : todoList) {
            System.out.println(todo);
        }
        System.out.println("Size: " + todoList.size());
        // create new todo
        em.getTransaction().begin();
        Todo todo = new Todo();
        todo.setSummary("This is a test");
        todo.setDescription("This is a test");
        em.persist(todo);
        em.getTransaction().commit();

        em.close();
        factory.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            connectHibernate();
        }catch(Exception ex){
            ex.printStackTrace();
            factory.close();
        }
    }

    public static void connectOracle() {
        Connection connection = null;
        try {
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            // Create a connection to the database
            String serverName = "localhost";
            String portNumber = "1521";
            String serviceName = "xe";

            String url = "jdbc:oracle:thin:@//" + serverName + ":" + portNumber + "/" + serviceName;
            System.out.println(url);
            String username = "root";
            String password = "oracle20381";
            connection = DriverManager.getConnection(url, username, password);
            
            //executeQuery(connection);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            // Could not connect to the database
            System.out.println(e);
        }
    }

}

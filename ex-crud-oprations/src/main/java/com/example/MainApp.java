package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // Create Session
        Session session = factory.openSession();
        try {
            // Begin Transaction
            session.beginTransaction();
            // 1. SAVE (Insert)
            Student s = new Student();
            s.setId(1);
            s.setName("Ram");
            s.setCity("Hyderabad");
            session.persist(s);
            // Commit
            session.getTransaction().commit();
            // 2. GET (Select)
            session.beginTransaction();
            Student s1 = session.get(Student.class, 1);
            System.out.println("Fetched: " + s1.getName());
            session.getTransaction().commit();
            // 3. UPDATE
            session.beginTransaction();
            s1.setCity("Chennai");
            session.merge(s1);
            session.getTransaction().commit();
            // 4. DELETE
            session.beginTransaction();
            session.remove(s1);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}

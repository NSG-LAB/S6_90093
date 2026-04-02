package com.example.exaggrtion;


import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class FullHQLExample {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            // 🔸 CREATE (Insert using save - HQL doesn't support insert directly)
            session.beginTransaction();

            Employee e1 = new Employee();
            e1.setId(1);
            e1.setName("Ram");
            e1.setSalary(50000);

            Employee e2 = new Employee();
            e2.setId(2);
            e2.setName("Hari");
            e2.setSalary(60000);

            session.save(e1);
            session.save(e2);

            session.getTransaction().commit();

            // 🔸 READ (FROM Entity)
            session.beginTransaction();

            List<Employee> list = session
                    .createQuery("FROM Employee", Employee.class)
                    .getResultList();

            System.out.println("All Employees:");
            for (Employee e : list) {
                System.out.println(e.getId() + " " + e.getName() + " " + e.getSalary());
            }

            session.getTransaction().commit();

            // 🔸 UPDATE using HQL
            session.beginTransaction();

            Query updateQuery = session.createQuery(
                "UPDATE Employee e SET e.salary = :sal WHERE e.id = :id"
            );

            updateQuery.setParameter("sal", 70000);
            updateQuery.setParameter("id", 1);

            int updated = updateQuery.executeUpdate();
            System.out.println("Rows Updated: " + updated);

            session.getTransaction().commit();

            // 🔸 DELETE using HQL
            session.beginTransaction();

            Query deleteQuery = session.createQuery(
                "DELETE FROM Employee e WHERE e.id = :id"
            );

            deleteQuery.setParameter("id", 2);

            int deleted = deleteQuery.executeUpdate();
            System.out.println("Rows Deleted: " + deleted);

            session.getTransaction().commit();

            // 🔸 AGGREGATE FUNCTIONS
            session.beginTransaction();

            Long count = session
                    .createQuery("SELECT COUNT(e) FROM Employee e", Long.class)
                    .getSingleResult();

            Double avg = session
                    .createQuery("SELECT AVG(e.salary) FROM Employee e", Double.class)
                    .getSingleResult();

            System.out.println("Total Employees: " + count);
            System.out.println("Average Salary: " + avg);

            session.getTransaction().commit();

            // 🔸 SORTING
            session.beginTransaction();

            List<Employee> sorted = session
                    .createQuery("FROM Employee e ORDER BY e.salary DESC", Employee.class)
                    .getResultList();

            System.out.println("Sorted Employees (DESC):");
            for (Employee e : sorted) {
                System.out.println(e.getName() + " " + e.getSalary());
            }

            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}

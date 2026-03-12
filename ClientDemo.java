package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Date;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        // Insert a new record
        Hospital h = new Hospital();
        h.setName("City Hospital");
        h.setDescription("A general hospital");
        h.setDate(new Date());
        h.setStatus("Active");
        h.setLocation("Downtown");
        h.setContact("123-456-7890");
        session.save(h);
        session.getTransaction().commit();
        System.out.println("Inserted record with ID: " + h.getId());

        // View the record based on the ID
        int id = h.getId();
        Hospital retrieved = session.get(Hospital.class, id);
        if (retrieved != null) {
            System.out.println("Retrieved Hospital: ID=" + retrieved.getId() + ", Name=" + retrieved.getName() + ", Description=" + retrieved.getDescription() + ", Date=" + retrieved.getDate() + ", Status=" + retrieved.getStatus() + ", Location=" + retrieved.getLocation() + ", Contact=" + retrieved.getContact());
        } else {
            System.out.println("No record found with ID: " + id);
        }

        session.close();
        factory.close();
    }
}
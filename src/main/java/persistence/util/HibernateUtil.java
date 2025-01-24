package persistence.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    
    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry reg = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            
            Metadata metadata = new MetadataSources(reg).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed: " + ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
    
    public static void shutdown() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}

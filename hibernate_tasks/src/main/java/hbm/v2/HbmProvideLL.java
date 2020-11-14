package hbm.v2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Hibernate Provider Low Level
 * <p>
 * Lazy Singleton
 */
public class HbmProvideLL {
    private final StandardServiceRegistry registry
            = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf
            = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final HbmProvideLL INST = new HbmProvideLL();
    }

    public static HbmProvideLL instOf() {
        return HbmProvideLL.Lazy.INST;
    }


    /**
     * Get session with default configuration and default commit after action(s).
     *
     * @param action - action with HBM. example: {@code
     *               providerLL.defaultTransaction(session -> session.save(model)
     * @return - return result of your function.
     */
    Object defaultTransaction(Function<Session, Object> action) {
        Session session = sf.getCurrentSession();
        Object rsl;
        try {
            session.beginTransaction();

            rsl = action.apply(session);

            session.getTransaction().commit();
        } finally {
            session.close();
        }

        return rsl;
    }

    /**
     * The same that defaultTransaction() but without return type.
     *
     * @param action - action with HBM. example: {@code
     *               providerLL.defaultTransactionVoid(session -> session.save(model)
     */
    void defaultTransactionVoid(Consumer<Session> action) {
        defaultTransaction(session -> {
            action.accept(session);
            return null;
        });
    }


    /* Rollback transactions */


    /**
     * Get session with default configuration and rollback after action(s).
     *
     * @param action - action with HBM. example: {@code
     *               providerLL.rollbackTransaction(session -> session.save(model)
     * @return - return result of your function.
     */
    Object rollbackTransaction(Function<Session, Object> action) {
        Session session = sf.getCurrentSession();
        Object rsl;
        try {
            session.beginTransaction();

            rsl = action.apply(session);

            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return rsl;
    }

    /**
     * The same that defaultTransaction() but without return type.
     *
     * @param action - action with HBM. example: {@code
     *               providerLL.rollbackTransactionVoid(session -> session.save(model)
     */
    void rollbackTransactionVoid(Consumer<Session> action) {
        rollbackTransaction(session -> {
            action.accept(session);
            return null;
        });
    }
}

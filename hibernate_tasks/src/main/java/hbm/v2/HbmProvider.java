package hbm.v2;

import org.hibernate.Session;

import java.util.List;
import java.util.function.Function;

/**
 * Core for make you own ModelStore.
 * Have all basic API.
 *
 * @author Daniils Loputevs(laiwiense@gmail.com)
 * @version 1.0.
 * @since 14.11.2020.
 */
public class HbmProvider<T> {
    private static final HbmProvideLL provideLL = HbmProvideLL.instOf();
    private final String modelClassName;

    public HbmProvider(String modelClassName) {
        this.modelClassName = modelClassName;
    }


    /* Default CRUD operations */


    public void save(T model) {
        provideLL.defaultTransactionVoid(session -> session.save(model));
    }

    public void saveAll(List<T> models) {
        provideLL.defaultTransactionVoid(session -> models.forEach(session::save));
    }

    public <V> T getBy(String fieldName, V value) {
        var hql = "from " + modelClassName + " as mt where mt." + fieldName + "="
                + '\'' + value + '\'';
        return this.exeQuerySingleRsl(hql);
    }

    public <V> List<T> getListBy(String fieldName, V value) {
        var hql = "from " + modelClassName + " as mt where mt." + fieldName + "="
                + '\'' + value + '\'';
        return this.exeQueryList(hql);
    }

    public List<T> getAll() {
        String hql = "from " + modelClassName;
        return this.exeQueryList(hql);
    }

    public void update(T model) {
        provideLL.defaultTransactionVoid(session -> session.update(model));
    }

    public void updateAll(List<T> items) {
        provideLL.defaultTransactionVoid(session -> items.forEach(session::merge));
    }

    public void delete(T model) {
        provideLL.defaultTransactionVoid(session -> session.delete(model));
    }

    public void delete(int id) {
        this.exeQueryVoid("delete from " + modelClassName + " where id=" + id);
    }

    public void deleteAll(List<T> items) {
        provideLL.defaultTransactionVoid(session -> items.forEach(session::delete));
    }


    /* String query operations */


    public T exeQuerySingleRsl(String query) {
        return (T) provideLL.defaultTransaction(session -> session.createQuery(query).getSingleResult());
    }

    public List<T> exeQueryList(String query) {
        return (List<T>) provideLL.defaultTransaction(session -> session.createQuery(query).list());
    }

    public void exeQueryVoid(String query) {
        provideLL.defaultTransaction(session -> session.createQuery(query).executeUpdate());
    }


    /* Other operations */

    public void sessionWork(Function<Session, Object> action) {
        provideLL.defaultTransaction(action::apply);
    }

    /**
     * When you use query with return List<T> but expect only one result.
     *
     * @param list  -
     * @param empty -
     * @return -
     */
    public T getFirstOrEmpty(List<T> list, T empty) {
        return (list.isEmpty()) ? empty : list.get(0);
    }

}

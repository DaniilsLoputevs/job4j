package hbm.v2;

import java.util.List;

/**
 * Core for make you own ModelStoreRollback. Use it for tests.
 * Have all basic API.
 *
 * @author Daniils Loputevs(laiwiense@gmail.com)
 * @version 1.0.
 * @since 14.11.2020.
 */
public class HbmProviderRollback<T> {
    private static final HbmProvideLL provideLL = HbmProvideLL.instOf();
    private final String modelClassName;

    public HbmProviderRollback(String modelClassName) {
        this.modelClassName = modelClassName;
    }


    /* Default CRUD operations */


    public void save(T model) {
        provideLL.rollbackTransaction(session -> session.save(model));
    }

    public void saveAll(List<T> models) {
        provideLL.rollbackTransactionVoid(session -> models.forEach(session::save));
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
        provideLL.rollbackTransactionVoid(session -> session.update(model));
    }

    public void updateAll(List<T> items) {
        provideLL.rollbackTransactionVoid(session -> items.forEach(session::merge));
    }

    public void delete(T model) {
        provideLL.rollbackTransactionVoid(session -> session.delete(model));
    }

    public void delete(int id) {
        this.exeQueryVoid("delete from " + modelClassName + " where id=" + id);
    }

    public void deleteAll(List<T> items) {
        provideLL.rollbackTransactionVoid(session -> items.forEach(session::delete));
    }


    /* String query operations */


    public T exeQuerySingleRsl(String query) {
        return (T) provideLL.rollbackTransaction(session -> session.createQuery(query).getSingleResult());
    }

    public List<T> exeQueryList(String query) {
        return (List<T>) provideLL.rollbackTransaction(session -> session.createQuery(query).list());
    }

    public void exeQueryVoid(String query) {
        provideLL.rollbackTransactionVoid(session -> session.createQuery(query).executeUpdate());
    }


    /* Other operations */


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

package ru.job4j.generic;

public abstract class AbstractStore<Y extends Base> implements Store<Y> {

    protected AbstractStore(SimpleArray<Y> store) {
        this.store = store;
    }


    private SimpleArray<Y> store;
    private int index = 0;

    @Override
    public void add(Base model) {
        this.store.add((Y) model);
        index++;
    }

    @Override
    public boolean replace(String id, Base model) {
        var result = false;
        this.store.set(findIndexById(id), (Y) model);
        result = true;
        // Можно просто - return true, но думается что это не очень вырно.
        return result;
    }

    @Override
    public boolean delete(String id) {
        var result = false;
        this.store.remove(findIndexById(id));
        result = true;
        // Можно просто - return true, но думается что это не очень вырно.
        return result;
    }

    @Override
    public Y findById(String id) {
        Y result = null;
        Y hold; // переменная против дублирования.
        for (int i = 0; i < index; i++) {
            hold = this.store.get(i);
            if (hold.getId().equals(id)) {
                result = hold;
                break;
            }
        }
        return result;
    }
    private int findIndexById(String id) {
        var result = -1;
        for (int i = 0; i < index; i++) {
            if (this.store.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }


}

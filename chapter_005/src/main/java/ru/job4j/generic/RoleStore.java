package ru.job4j.generic;

public class RoleStore implements Store<Role>  {

    private SimpleArray<Role> store = new SimpleArray<>(100);
    private int index = 0;

    @Override
    public void add(Role model) {
        this.store.add(model);
        index++;
    }

    @Override
    public boolean replace(String id, Role model) {
        var result = false;
        this.store.set(findIndexById(id), model);
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
    public Role findById(String id) {
        Role result = null;
        Role hold; // переменная против дублирования.
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
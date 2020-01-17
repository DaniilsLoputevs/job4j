package ru.job4j.generic;

public class RoleStore<Role extends Base> implements Store {

    private SimpleArray<Role> store = new SimpleArray<>(100);
    private int index = 0;

    @Override
    public void add(Base model) {
        this.store.add((Role) model);
        index++;
    }

    @Override
    public boolean replace(String id, Base model) {
        var result = false;
        for (int i = 0; i < index; i++) {
            if (this.store.get(i).getId().equals(id)) {
                this.store.set(i, (Role) model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        // Сдесь Имеенно for() и get() т.к. Через forEach() обращение идёт как, к Object, а не как, к User
        var result = false;
        for (int i = 0; i < index; i++) {
            if (this.store.get(i).getId().equals(id)) {
                this.store.remove(i);
                result = true;
                break;
            }
        }
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
}
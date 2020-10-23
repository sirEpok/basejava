/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int size = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if(storage[i] != null) {
                storage[i] = null;
            }
        }
        size = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
            size++;
        }
    }

    Resume get(String uuid) {
        Resume result = null;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (storage[i].uuid.equals(uuid)) {
                    result = storage[i];
                    break;
                }
            }
        }
        return result;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = storage[size-1];
                storage[size-1] = null;
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] r = new Resume[storage.length];
        for (int i = 0; i < storage.length; i++) {
            r[i] = storage[i];
        }
        return r;
    }

    int size() {
        return size;
    }
}

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    Resume r = new Resume();

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if(storage[i] != null) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
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
        int size = storage.length-1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = storage[size-1];
                storage[size-1] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] r = new Resume[storage.length];
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                r[i] = storage[i];
            }
        }
        return r;
    }

    int size() {
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                count++;
            }
        }
        return count;
    }
}

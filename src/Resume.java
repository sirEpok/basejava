/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    String uuid;
    Resume() {
    }

    Resume(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}

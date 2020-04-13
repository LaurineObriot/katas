package fr.octo.craft.common.event;

import java.util.Objects;
import java.util.UUID;

public final class MessageId {

    private final UUID id;

    protected MessageId() {
        this(UUID.randomUUID());
    }

    public MessageId(UUID dysfunctionUUID) {
        this.id = dysfunctionUUID;
    }

    public MessageId(String uuid) {
        this.id = UUID.fromString(uuid);
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageId that = (MessageId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

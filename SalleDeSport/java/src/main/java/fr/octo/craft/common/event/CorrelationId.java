package fr.octo.craft.common.event;

import java.util.Objects;
import java.util.UUID;

public final class CorrelationId {

    private final UUID id;

    protected CorrelationId() {
        this(UUID.randomUUID());
    }

    public CorrelationId(UUID dysfunctionUUID) {
        this.id = dysfunctionUUID;
    }

    public CorrelationId(String uuid) {
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
        CorrelationId that = (CorrelationId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

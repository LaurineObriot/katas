package fr.octo.craft.common.event;

import java.util.Objects;
import java.util.UUID;

public final class CausationId {

    private final UUID id;

    protected CausationId() {
        this(UUID.randomUUID());
    }

    public CausationId(UUID dysfunctionUUID) {
        this.id = dysfunctionUUID;
    }

    public CausationId(String uuid) {
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
        CausationId that = (CausationId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

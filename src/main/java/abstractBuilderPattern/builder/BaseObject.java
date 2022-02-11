package abstractBuilderPattern.builder;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseObject {

    protected UUID tenantUuid;
    protected UUID uuid;
    protected String id;
    protected LocalDateTime createdAt;
    protected UUID createBy;
    protected LocalDateTime modifiedAt;
    protected UUID modifiedBy;

    protected BaseObject(BaseObjectBuilder<?> baseObjectBuilder) {
        this.tenantUuid = baseObjectBuilder.tenantUuid;
        this.uuid = baseObjectBuilder.uuid == null ? UUID.randomUUID() : baseObjectBuilder.uuid;
        this.id = baseObjectBuilder.id;
        this.createdAt = baseObjectBuilder.createdAt == null ? LocalDateTime.now() : baseObjectBuilder.createdAt;
        this.createBy = baseObjectBuilder.createBy;
        this.modifiedAt = baseObjectBuilder.modifiedAt == null ? LocalDateTime.now() : baseObjectBuilder.modifiedAt;
        this.modifiedBy = baseObjectBuilder.modifiedBy;
    }

    abstract static class BaseObjectBuilder<T extends BaseObjectBuilder<?>> {

        private UUID tenantUuid;
        private UUID uuid;
        private String id;
        private LocalDateTime createdAt;
        private UUID createBy;
        private LocalDateTime modifiedAt;
        private UUID modifiedBy;

        public T withTenantUuid(UUID tenantUuid) {
            this.tenantUuid = tenantUuid;
            return self();
        }

        public T withUuid(UUID uuid) {
            this.uuid = uuid;
            return self();
        }

        public T withId(String id) {
            this.id = id;
            return self();
        }

        public T withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return self();
        }

        public T withCreateBy(UUID createBy) {
            this.createBy = createBy;
            return self();
        }

        public T withModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return self();
        }

        public T withModifiedBy(UUID modifiedBy) {
            this.modifiedBy = modifiedBy;
            return self();
        }

        public abstract BaseObject build();

        protected abstract T self();
    }


    @Override
    public String toString() {
        return "%nBaseObject{%n tenantUuid=%s,  %n uuid=%s, %n id='%s',%n createdAt=%s,%n createBy=%s,%n modifiedAt=%s,%n modifiedBy=%s%n}"
                .formatted(tenantUuid, uuid, id, createdAt, createBy, modifiedAt, modifiedBy);
    }
}


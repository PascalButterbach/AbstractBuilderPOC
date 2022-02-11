package abstractBuilderPattern.restrictedBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class RestrictedBaseObject {

    protected UUID tenantUuid;
    protected UUID uuid;
    protected String id;
    protected LocalDateTime createdAt;
    protected UUID createBy;
    protected LocalDateTime modifiedAt;
    protected UUID modifiedBy;

    protected RestrictedBaseObject(RestrictedBaseObjectBuilder<?> restrictedBaseObjectBuilder) {

        this.tenantUuid = restrictedBaseObjectBuilder.tenantUuid;
        this.uuid = restrictedBaseObjectBuilder.uuid == null ? UUID.randomUUID() : restrictedBaseObjectBuilder.uuid;
        this.id = restrictedBaseObjectBuilder.id;
        this.createdAt = restrictedBaseObjectBuilder.createdAt == null ? LocalDateTime.now() : restrictedBaseObjectBuilder.createdAt;
        this.createBy = restrictedBaseObjectBuilder.createBy;
        this.modifiedAt = restrictedBaseObjectBuilder.modifiedAt == null ? LocalDateTime.now() : restrictedBaseObjectBuilder.modifiedAt;
        this.modifiedBy = restrictedBaseObjectBuilder.modifiedBy;
    }

    abstract static class RestrictedBaseObjectBuilder<T extends RestrictedBaseObjectBuilder<?>> implements RestrictedBaseObjectBuilderTenant<T>, RestrictedBaseObjectBuilderCreatedBy<T> {

        private UUID tenantUuid;
        private UUID uuid;
        private String id;
        private LocalDateTime createdAt;
        private UUID createBy;
        private LocalDateTime modifiedAt;
        private UUID modifiedBy;

        public RestrictedBaseObjectBuilderCreatedBy<T> withTenantUuid(UUID tenantUuid) {
            this.tenantUuid = tenantUuid;
            return this;
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

        public abstract RestrictedBaseObject build();

        protected abstract T self();
    }

    public interface RestrictedBaseObjectBuilderTenant<T>{
        RestrictedBaseObjectBuilderCreatedBy<T> withTenantUuid(UUID tenantUuid);
    }

    public interface RestrictedBaseObjectBuilderCreatedBy<T>{
        T withCreateBy(UUID createBy);
    }


    @Override
    public String toString() {
        return "%nRestrictedBaseObject{%n tenantUuid=%s,  %n uuid=%s, %n id='%s',%n createdAt=%s,%n createBy=%s,%n modifiedAt=%s,%n modifiedBy=%s%n}"
                .formatted(tenantUuid, uuid, id, createdAt, createBy, modifiedAt, modifiedBy);
    }
}


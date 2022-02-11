package abstractBuilderPattern.restrictedBuilder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder =  RestrictedInheritedObject.Builder.class)
public class RestrictedInheritedObject extends RestrictedBaseObject {

    private String name;

    // private Constructor
    protected RestrictedInheritedObject(Builder stateMachineBaseObjectBuilder) {
        super(stateMachineBaseObjectBuilder);
        this.name = stateMachineBaseObjectBuilder.name;
    }

    // method to get a builder instance
    public static RestrictedBaseObjectBuilderTenant<Builder> builder(){
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder extends RestrictedBaseObjectBuilder<Builder> {

        private String name;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public RestrictedInheritedObject build() {
            return new RestrictedInheritedObject(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    @Override
    public String toString() {
        return "%nRestrictedInheritedObject{%n name='%s'%n}%s"
                .formatted(name, super.toString());
    }
}

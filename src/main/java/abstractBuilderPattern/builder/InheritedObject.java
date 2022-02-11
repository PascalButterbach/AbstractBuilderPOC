package abstractBuilderPattern.builder;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = InheritedObject.Builder.class)
public class InheritedObject extends BaseObject{

    private String name;

    // private Constructor
    private InheritedObject(Builder builder) {
        super(builder);
        this.name = builder.name;
    }

    // method to get a builder instance
    public static Builder builder(){
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder extends BaseObjectBuilder<Builder> {

        private String name;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public InheritedObject build() {
            return new InheritedObject(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    @Override
    public String toString() {
        return "%nInheritedObject{%n name='%s'%n}%s"
                .formatted(name, super.toString());
    }
}

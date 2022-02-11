import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import abstractBuilderPattern.builder.InheritedObject;

import java.time.LocalDateTime;
import java.util.UUID;

public class RegularBuilder {
    public static void main(String[] args) throws JsonProcessingException {

        InheritedObject.builder()
                .withTenantUuid(UUID.randomUUID())
                .withCreateBy(UUID.randomUUID())
                .build();

        InheritedObject inhObj = InheritedObject.builder()
                .withTenantUuid(UUID.randomUUID())
                .withCreateBy(UUID.randomUUID())
                .withUuid(UUID.randomUUID())
                .withId("some ID")
                .withCreatedAt(LocalDateTime.now())
                .withModifiedBy(UUID.randomUUID())
                .withModifiedAt(LocalDateTime.now())
                .withName("some Name")
                .build();

        System.out.println("full obj " + inhObj);


        inhObj = InheritedObject.builder()
                .withTenantUuid(UUID.randomUUID())
                .withCreateBy(UUID.randomUUID())
                .withName("some Name")
                .build();

        System.out.println("minimalistic obj " + inhObj);


        // region: Jackson / HTTP Requests
        String json_raw = """
                {
                  "name": "somename",
                  "tenantUuid": "e3c58dfe-f20f-4389-b327-365884a13538",
                  "uuid": "6b1f2789-e130-41f4-805b-ef137fa08c51",
                  "id": "some ID",
                  "createdAt": "2022-02-11T18:27:00.746567900",
                  "createBy": "dbfbba49-9163-43e5-9b62-051c7f698079",
                  "modifiedAt": "2022-02-11T18:27:00.746567900",
                  "modifiedBy": "c9d0d1f0-9880-433e-abc1-abce4888d8a1"
                }
                """;

        InheritedObject object = new ObjectMapper()
                .findAndRegisterModules()
                .readValue(json_raw, InheritedObject.class);

        System.out.println("full obj, parsed from json " + object);


        json_raw = """
                {
                  "name": "somename",
                  "tenantUuid": "e3c58dfe-f20f-4389-b327-365884a13538"
                }
                """;

        object = new ObjectMapper()
                .findAndRegisterModules()
                .readValue(json_raw, InheritedObject.class);

        System.out.println("minimalistic obj, parsed from json " + object);

        // endregion
    }
}

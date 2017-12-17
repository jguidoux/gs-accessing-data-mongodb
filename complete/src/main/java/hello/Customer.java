package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class Customer {

    @Id
    private String id;

    private String firstName;
    private String lastName;


    /**
     * define builder class - Lombok will enhance this class as needed, Jackson will use this builder then through the @JsonDeserialize annotation above
     */
    @JsonPOJOBuilder(withPrefix = "")
    @JsonIgnoreProperties(ignoreUnknown = true) // don't barf on unknown properties during deserialization
    public static class CustomerBuilder {

    }

}


package io.github.demirmustafa.samplereactiveapp.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;
    @Field
    private String name;
    @Field
    private String surname;
    @Field
    private String username;
}

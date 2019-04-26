package io.github.demirmustafa.samplereactiveapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {

    private String id;
    private String name;
    private String surname;
    private String username;
}

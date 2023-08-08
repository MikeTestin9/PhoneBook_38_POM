package models;

import lombok.*;

@Setter
@Getter
@Builder
@ToString

public class Contact {

    String name;
    String lastName;
    String email;
    String phone;
    String address;
    String description;

}
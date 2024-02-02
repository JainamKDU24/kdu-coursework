package kdu.assessment.assessment2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address extends DefaultTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;

    private String city;

    private String state;

    private int postalCode;

    private String role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
package dev.patika.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", columnDefinition = "serial")
    private long id;

    @NotNull
    @Column(name = "customer_name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "customer_phone", nullable = false)
    private String phone;

    @Column(name = "customer_mail")
    private String mail;

    @Column(name = "customer_address", nullable = false)
    private String address;

    @Column(name = "customer_city", nullable = false)
    private String city;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Animal> animals;
}

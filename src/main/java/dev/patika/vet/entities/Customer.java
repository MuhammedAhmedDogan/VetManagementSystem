package dev.patika.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Email
    @Column(name = "customer_mail")
    private String mail;

    @Column(name = "customer_address", nullable = false)
    private String address;

    @Column(name = "customer_city", nullable = false)
    private String city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Animal> animals;
}
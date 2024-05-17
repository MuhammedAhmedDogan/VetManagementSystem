package dev.patika.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", columnDefinition = "serial")
    private long id;

    @NotNull
    @NotEmpty
    @Column(name = "animal_name", nullable = false)
    private String name;

    @NotNull
    @NotEmpty
    @Column(name = "animal_species", nullable = false)
    private String species;

    @NotNull
    @NotEmpty
    @Column(name = "animal_breed", nullable = false)
    private String breed;

    @NotNull
    @NotEmpty
    @Column(name = "animal_gender", nullable = false)
    private String gender;

    @NotNull
    @NotEmpty
    @Column(name = "animal_color", nullable = false)
    private String color;

    @Column(name = "animal_birth_date")
    private LocalDate birthDate;

    @ManyToOne()
    @JoinColumn(name = "animal_customer_id",referencedColumnName = "customer_id")
    private Customer customer;
}

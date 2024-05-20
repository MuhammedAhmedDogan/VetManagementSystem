package dev.patika.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", columnDefinition = "serial")
    private long id;

    @NotNull
    @Column(name = "animal_name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "animal_species", nullable = false)
    private String species;

    @NotNull
    @Column(name = "animal_breed", nullable = false)
    private String breed;

    @NotNull
    @Column(name = "animal_gender", nullable = false)
    private String gender;

    @NotNull
    @Column(name = "animal_color", nullable = false)
    private String color;

    @Temporal(TemporalType.DATE)
    @Column(name = "animal_birth_date")
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "animal", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Vaccine> vaccines;
}
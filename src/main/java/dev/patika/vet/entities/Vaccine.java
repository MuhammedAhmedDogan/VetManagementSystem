package dev.patika.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id", columnDefinition = "serial")
    private long id;

    @NotNull
    @Column(name = "vaccine_name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "vaccine_code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "vaccine_protection_start_date", nullable = false)
    private LocalDate protectionStartDate;

    @NotNull
    @Column(name = "vaccine_protection_finish_date", nullable = false)
    private LocalDate protectionFinishDate;

    @ManyToOne()
    @JoinColumn(name = "vaccine_animal_id", referencedColumnName = "animal_id")
    private Animal animal;
}

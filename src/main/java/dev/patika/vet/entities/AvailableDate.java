package dev.patika.vet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "available_date")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_date_id", columnDefinition = "serial")
    private long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "available_date")
    private LocalDate availableDate;

    @ManyToMany(mappedBy = "availableDates", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Veterinarian> veterinarians;
}

package dev.patika.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "veterinarians")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Veterinarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veterinarian_id", columnDefinition = "serial")
    private long id;

    @NotNull
    @Column(name = "veterinarian_name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "veterinarian_phone", nullable = false)
    private String phone;

    @Email
    @Column(name = "veterinarian_mail")
    private String mail;

    @Column(name = "veterinarian_address", nullable = false)
    private String address;

    @Column(name = "veterinarian_city", nullable = false)
    private String city;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "veterinarian2available_date",
            joinColumns = {@JoinColumn(name = "veterinarian_id")},
            inverseJoinColumns = {@JoinColumn(name = "available_date_id")}
    )
    private Set<AvailableDate> availableDates;

    public void addAvailableDate(AvailableDate availableDate) {
        this.availableDates.add(availableDate);
    }

    public void removeAvailableDate(AvailableDate availableDate) {
        this.availableDates.remove(availableDate);
    }
}

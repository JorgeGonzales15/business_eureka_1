package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reservation_service", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "service_id"), // Columna que hace referencia a la entidad actual (Servicio)
            inverseJoinColumns = @JoinColumn(name = "reservation_id") // Columna que hace referencia a la entidad relacionada (Reservation)
    )
    private List<Reservation> reservations;

    @ManyToMany(mappedBy = "servicios")
    private List<Company> companies;*/
}

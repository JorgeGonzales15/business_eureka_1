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
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stars", nullable = false)
    private int stars;

    @Column(name = "idCompany", nullable = false)
    private Long companyId;


    /*public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne
    @JoinColumn(name="idCompany", nullable = false)
    private Company company;*/

}

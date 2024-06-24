package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an entity of booking history within the context of carga sin estres booking system. This class is a JPA entity
 * that maps to the "bookings" table in the database.
 * <p>
 *     The annotations used in this class are:
 *     <ul>
 *         <li>{@code @Data}: This Lombok annotation generates getter and setter methods for the class attributes.</li>
 *         <li>{@code @Builder}: This Lombok annotation generates a constructor with all the class parameters.</li>
 *         <li>{@code @NoArgsConstructor}: This Lombok annotation generates an empty constructor.</li>
 *         <li>{@code @AllArgsConstructor}: This Lombok annotation generates a constructor with all the class parameters.</li>
 *         <li>{@code @Entity}: This JPA annotation indicates that the class is an entity.</li>
 *         <li>{@code @Table}: This JPA annotation indicates the name of the database table that represents the entity.</li>
 *     </ul>
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reservations")
public class Reservation {
    /**
     * The id of the booking history.
     * <p>
     *     This attribute maps to the "id" column in the "bookings" table in the database.
     *     The value is generated automatically.
     *     The column is a primary key.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The company of the booking history.
     * <p>
     *     This attribute maps to the "idCompany" column in the "bookings" table in the database.
     *     The column is a foreign key.
     *     The attribute is mapped by the {@code company} attribute of the {@code Company} class.
     * </p>
     */
    //@JoinColumn(name="idCompany", nullable = false, foreignKey = @ForeignKey(name="FK_reservation_company"))
    //@ManyToOne
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //private Company company;

    @Column(name="idCompany", nullable = false)
    private Long companyId;

    /**
     * The client of the booking history.
     * <p>
     *     This attribute maps to the "idClient" column in the "bookings" table in the database.
     *     The column is a foreign key.
     *     The attribute is mapped by the {@code client} attribute of the {@code Client} class.
     * </p>
     */
    /*@ManyToOne
    @JoinColumn(name="idClient", nullable = false, foreignKey = @ForeignKey(name="FK_reservation_client"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customer customer;*/

    @Column(name="idClient", nullable = false)
    private Long customerId;

    /**
     * The moving date of the booking history.
     */
    @Column(name="start_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate; //movingDate

    /**
     * The moving time of the booking history.
     * <p>
     *     The attribute is mapped by the {@code movingTime} attribute of the {@code Time} class.
     * </p>
     */
    @Column(name="start_time", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    /**
     * The pickup address of the booking history.
     */
    @JoinColumn(name = "origin_address", nullable = false)
    private String originAddress;

    /**
     * The destination address of the booking history.
     */
    @JoinColumn(name = "destination_address", nullable = false)
    private String destinationAddress;

    /**
     * The end date of the reservation.
     */
    @Column(name="end_date")
    private LocalDate endDate;

    /**
     * The end time of the reservation.
     */
    @Column(name="end_time")
    private LocalTime endTime;

    /**
     * The payment of the booking history.
     */
    @Column(name="price")
    private float price;

    /**
     * The status of the booking history.
     */
    @Column(name="status", nullable = false)
    private String status;

    /**
     * The services of the reservation
     */
    @Column(name="services", nullable = false)
    private String services;

    /**
     * The chats of the booking history.
     */
    @Column(name="chatId")
    private Long chatId;
}
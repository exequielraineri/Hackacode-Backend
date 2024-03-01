package com.hackacode.commons.entity.models.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pack_turisticos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackTuristico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //si en la lista solo hay 1 servicio, se adopta forma indivudual
    //en caso que sea mas de 1, se costea como paquete
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "pack_servicio",
            joinColumns = @JoinColumn(name = "pack_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id"),
            uniqueConstraints = {
                @UniqueConstraint(columnNames = {"pack_id", "servicio_id"})}
    )
    private List<Servicio> servicios;

    private Double costoPack;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    public Double getCostoPack() {
        double total = 0.0;
        for (Servicio servicio : servicios) {
            total += servicio.getCosto();
        }
        return total;
    }

}

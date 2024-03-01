package com.hackacode.commons.entity.models.entity;

import com.hackacode.commons.entity.util.MedioPago;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ventas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;

    @Enumerated(EnumType.STRING)
    private MedioPago medioPago;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private PackTuristico producto;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(name = "importe")
    private BigDecimal importe;

    public BigDecimal getImporte() {
        if (importe == null) {
            importe = BigDecimal.ZERO;
        }
        BigDecimal comision = BigDecimal.ZERO;
        switch (medioPago) {
            case Debito: {
                //3%
                comision = new BigDecimal(0.03);
                break;
            }
            case Credito: {
                //9%
                comision = new BigDecimal(0.09);
                break;
            }
            case Transferencia: {
                //2,45%
                comision = new BigDecimal(0.0245);
                break;
            }
            default: {
                break;
            }
        }
        //multiplico por la comision y redondeo para arriba con 2 decimales
        comision = importe.multiply(comision).setScale(2, RoundingMode.HALF_UP);

        return importe.add(comision);
    }

}

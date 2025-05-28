package com.tomzamora.msvc.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Embeddable -> Esto es una clase que si bien esta definido por fuera puede estar asociada a otra tabla es como si los
 * atributos de esta clase se traspasaran a la clase que se define el objeto audit.
 */
@Embeddable
@Getter @Setter @NoArgsConstructor @ToString
public class Audit {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Este metodo se ejecuto automaticamente una vez que el objeto es creado
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Este metodo se ejecuta automaticamente cuando se realiza la acutalizacion de un elemento
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

package com.tomzamora.msvc.carrito.msvc.carrito.services;

import com.tomzamora.msvc.carrito.msvc.carrito.clients.ProductosClientRest;
import com.tomzamora.msvc.carrito.msvc.carrito.clients.VentaClientRest;
import com.tomzamora.msvc.carrito.msvc.carrito.exceptions.CarritoException;
import com.tomzamora.msvc.carrito.msvc.carrito.model.Producto;
import com.tomzamora.msvc.carrito.msvc.carrito.model.Venta;
import com.tomzamora.msvc.carrito.msvc.carrito.model.entities.Carrito;
import com.tomzamora.msvc.carrito.msvc.carrito.repositories.CarritoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductosClientRest productosClientRest;

    @Autowired
    private VentaClientRest ventaClientRest;

    @Override
    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    @Override
    public Carrito findById(Long id) {
        return this.carritoRepository.findById(id).orElseThrow(
                () -> new CarritoException("El carrito con el id " + id + " no existe.")
        );
    }

    @Override
    public Carrito save(Carrito carrito) {
        try {
            Venta venta = this.ventaClientRest.findById(carrito.getIdVenta());
        }catch (FeignException exception){
            throw new CarritoException("El carrito con el id " + carrito.getIdVenta() + " no existe.");
        }

        try{
            Producto producto = this.productosClientRest.findById(carrito.getIdProducto());
        } catch (FeignException exception) {
            throw new CarritoException("El producto con el id " + carrito.getIdProducto() + " no fue encontrado.");
        }
        return this.carritoRepository.save(carrito);
    }
}


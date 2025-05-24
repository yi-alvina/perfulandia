package com.mcontreras.msvc.sucursal.servicies;

import com.mcontreras.msvc.sucursal.models.Sucursal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SucursalService {
    List<Sucursal> findAll();
    Sucursal findById(long id);
    Sucursal save(Sucursal sucursal);
}

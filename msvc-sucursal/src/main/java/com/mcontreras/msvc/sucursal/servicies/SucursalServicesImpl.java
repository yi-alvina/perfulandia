package com.mcontreras.msvc.sucursal.servicies;

import com.mcontreras.msvc.sucursal.exceptions.SucursalException;
import com.mcontreras.msvc.sucursal.models.Sucursal;
import com.mcontreras.msvc.sucursal.repositories.SucursalRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServicesImpl implements SucursalService {

    @Autowired
    private SucursalRespository sucursalRespository;

    @Override
    public List<Sucursal> findAll() {
        return this.findAll();
    }

    @Override
    public Sucursal findById(long id) {
        return this.sucursalRespository.findById(id).orElseThrow(
                () -> new SucursalException("Sucursal con el id " +id+ " no encontrado.")
        );
    }

    @Override
    public Sucursal save(Sucursal sucursal) {
        Sucursal sucursalEntity = new Sucursal();
        sucursalEntity.setNombreSucursal(sucursal.getNombreSucursal());
        sucursalEntity.setDireccion( sucursal.getDireccion());
        return this.sucursalRespository.save(sucursalEntity);
    }
}

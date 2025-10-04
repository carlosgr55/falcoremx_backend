package falcoremx.com.FalcoreMX.service;


import falcoremx.com.FalcoreMX.entity.DireccionEmpresa;
import falcoremx.com.FalcoreMX.repository.DireccionEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionesService {

    @Autowired
    private DireccionEmpresaRepository direccionRepository;

    public List<DireccionEmpresa> findAll(){
        return direccionRepository.findAll();
    }

    public List<DireccionEmpresa> findByEmpresaId(Integer idEmpresa){
        return direccionRepository.findByEmpresaId(idEmpresa);
    }


}

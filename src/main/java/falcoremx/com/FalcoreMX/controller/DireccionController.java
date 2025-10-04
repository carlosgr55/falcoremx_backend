package falcoremx.com.FalcoreMX.controller;


import falcoremx.com.FalcoreMX.entity.DireccionEmpresa;
import falcoremx.com.FalcoreMX.service.DireccionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/falcoremx")
public class DireccionController {

    @Autowired
    private DireccionesService direccionesService;

    @GetMapping("/direcciones")
    public List<DireccionEmpresa> getAllDirecciones() {
        return direccionesService.findAll();
    }

    @GetMapping("/direcciones/empresa/{idEmpresa}")
    public List<DireccionEmpresa> getDireccionesByEmpresaId(@PathVariable Integer idEmpresa) {
        return direccionesService.findByEmpresaId(idEmpresa);
    }


}

package falcoremx.com.FalcoreMX.controller;


import falcoremx.com.FalcoreMX.entity.Empresa;
import falcoremx.com.FalcoreMX.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/falcoremx")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/empresas")
    public List<Empresa> getAllEmpresas() {
        return empresaService.findAllEmpresas();
    }

    @GetMapping("/empresas/{id}")
    public Empresa getEmpresaById(@PathVariable Integer id) {
        return empresaService.findEmpresaById(id);
    }






}

package falcoremx.com.FalcoreMX.service;


import falcoremx.com.FalcoreMX.entity.Empresa;
import falcoremx.com.FalcoreMX.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> findAllEmpresas() {
        return empresaRepository.findAll();
    }

    public Empresa findEmpresaById(Integer id) {
        return empresaRepository.findById(id).orElse(null);
    }

    public Empresa saveEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa updateEmpresa(Integer id, Empresa empresaDetails) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if (empresa != null) {
            empresa.setNombre(empresaDetails.getNombre());
            empresa.setRfc(empresaDetails.getRfc());
            empresa.setRazonSocial(empresaDetails.getRazonSocial());
            return empresaRepository.save(empresa);
        }
        return null;
    }

    public void deleteEmpresa(Integer id) {
        empresaRepository.deleteById(id);
    }


}

package falcoremx.com.FalcoreMX.service;


import falcoremx.com.FalcoreMX.entity.RolEmpleado;
import falcoremx.com.FalcoreMX.repository.RolEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    private RolEmpleadoRepository rolEmpleadoRepository;

    public List<RolEmpleado> findAllRoles() {
        return rolEmpleadoRepository.findAll();
    }

    public List<RolEmpleado> findRolByEmpleado(String idEmpleado) {
        return rolEmpleadoRepository.findRolByEmpleado(idEmpleado);
    }

}

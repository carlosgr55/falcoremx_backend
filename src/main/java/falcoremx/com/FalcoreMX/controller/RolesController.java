package falcoremx.com.FalcoreMX.controller;

import falcoremx.com.FalcoreMX.entity.RolEmpleado;
import falcoremx.com.FalcoreMX.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/falcoremx")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping("/roles")
    public List<RolEmpleado> getAllRoles(){
        return rolesService.findAllRoles();
    }

    @GetMapping("/roles/empleado/{idEmpleado}" )
    public List<RolEmpleado> getRolesByEmpleadoId(@PathVariable String idEmpleado){
        return rolesService.findRolByEmpleado(idEmpleado);
    }



}

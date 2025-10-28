package falcoremx.com.FalcoreMX.controller;

import falcoremx.com.FalcoreMX.entity.RolEmpleado;
import falcoremx.com.FalcoreMX.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/roles/{user}/{rolId}")
    public void saveRolEmpleado(@PathVariable String user, @PathVariable Integer rolId) {
        rolesService.saveRol(user, rolId);
    }

    @DeleteMapping("/roles/{user}/{rolId}")
    public void desaginarRol(@PathVariable String user, @PathVariable Integer rolId) {
        rolesService.desaginarRol(user, rolId);
    }

    @PostMapping("/roles/update")
    public String actualizarRoles(@RequestBody Map<String, Object> body) {
        String username = (String) body.get("username");
        List<Integer> roles = (List<Integer>) body.get("roles");

        rolesService.actualizarRolesUsuario(username, roles);
        return "Roles actualizados correctamente para el usuario " + username;
    }

}

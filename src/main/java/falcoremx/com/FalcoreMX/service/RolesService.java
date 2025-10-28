package falcoremx.com.FalcoreMX.service;


import falcoremx.com.FalcoreMX.entity.RolEmpleado;
import falcoremx.com.FalcoreMX.entity.User;
import falcoremx.com.FalcoreMX.repository.RolEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void saveRol(String user, Integer rolId) {
        rolEmpleadoRepository.insertRolEmpleado(user, rolId);
    }

    public void desaginarRol(String user, Integer rolId) {
        rolEmpleadoRepository.deleteByUserAndRolId(user, rolId);
    }



    @Transactional
    public void actualizarRolesUsuario(String username, List<Integer> nuevosRoles) {
        // 1. Obtener roles actuales del usuario
        List<RolEmpleado> rolesActuales = rolEmpleadoRepository.findByUser(username);
        Set<Integer> rolesActualesIds = rolesActuales.stream()
                .map(RolEmpleado::getRol)
                .collect(Collectors.toSet());

        Set<Integer> nuevosRolesSet = Set.copyOf(nuevosRoles);

        // 2. Roles a eliminar
        Set<Integer> rolesAEliminar = rolesActualesIds.stream()
                .filter(r -> !nuevosRolesSet.contains(r))
                .collect(Collectors.toSet());

        // 3. Roles a agregar
        Set<Integer> rolesAAgregar = nuevosRolesSet.stream()
                .filter(r -> !rolesActualesIds.contains(r))
                .collect(Collectors.toSet());

        // 4. Eliminar los que ya no están
        rolesAEliminar.forEach(rolId -> rolEmpleadoRepository.deleteByUserAndRol(username, rolId));

        // 5. Agregar nuevos
        for (Integer rolId : rolesAAgregar) {
            RolEmpleado re = new RolEmpleado();
            re.setUser(username);
            re.setRol(rolId);
            rolEmpleadoRepository.save(re);
        }
    }

}

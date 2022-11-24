package com.primer_parcial8;

import com.primer_parcial8.Models.User;
import com.primer_parcial8.Repository.UserRepository;
import com.primer_parcial8.Services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class UserServiceMockTest {

    public static User mockUser() {
        User model = new User();
        model.setId(1L);
        model.setNombre("Harold");
        model.setApellidos("Martinez");
        model.setDocumento("1003168607");
        model.setDireccion("Ocaña");
        model.setFechaNacimiento(new Date(20, 06, 21));
        model.setTelefono("3112884010");
        model.setCorreo("hfmartinezc@ufpso.edu.co");
        model.setPassword("HaroldMartinez");
        return model;
    }

    public static User mockUserMod() {
        User model = new User();
        model.setNombre("Oscar");
        model.setApellidos("Vargas");
        model.setDocumento("1003168777");
        model.setDireccion("Ocaña");
        model.setFechaNacimiento(new Date(20, 05, 07));
        model.setTelefono("3112885020");
        model.setCorreo("omvargast@ufpso.edu.co");
        model.setPassword("OscarVargas");
        return model;
    }

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;


    @DisplayName("Test para crear Usuario")
    @Test
    void createUserTest() {
        //Given
        User user = mockUser();
        given(userRepository.findAllByNombre(user.getNombre())).willReturn(List.of(user));
        given(userRepository.findAllByApellidos(user.getApellidos())).willReturn(List.of(user));
        given(userRepository.findByNombreAndApellidos(user.getNombre(), user.getApellidos())).willReturn(List.of(user));
        given(userRepository.findByCorreo(user.getCorreo())).willReturn(user);
        given(userRepository.save(user)).willReturn(user);
        //When

        ResponseEntity<User> articuloGuardado = userService.createUser(user);

        //Then
        Assertions.assertNotNull(articuloGuardado);
    }

    @DisplayName("Test para listar a los Usuarios")
    @Test
    void getAllUsersTest() {

        //Given
        User user = mockUser();

        //When

        ResponseEntity<List<User>> lista = userService.allUsers();

        //Then
        Assertions.assertNotNull(lista);
    }

    @DisplayName("Test para obtener usuarios por codigo")
    @Test
    void GetArticleByCodTest() {

        //Given
        User user = mockUser();

        //When
        given(userRepository.findAllByNombre(user.getNombre())).willReturn(List.of(user));
        given(userRepository.findAllByApellidos(user.getApellidos())).willReturn(List.of(user));
        given(userRepository.findByNombreAndApellidos(user.getNombre(), user.getApellidos())).willReturn(List.of(user));
        given(userRepository.findByCorreo(user.getCorreo())).willReturn(user);
        ResponseEntity<User> respuesta = userService.getUserById(user.getId());

        //Then
        Assertions.assertNotNull(respuesta);

    }


}


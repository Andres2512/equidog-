package com.equidog.web.api;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.domain.dto.generic.UserPersonDTO;
import com.equidog.domain.dto.request.UserRequestDTO;
import com.equidog.domain.dto.response.UserResponseDTO;
import com.equidog.services.user.UserService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Usuario - API REST Micorservicio")
@RestController
@RequestMapping(value = "/api", consumes = "application/json")
public class UserRestApi {

    private ModelMapper mapper;

    private final UserService service;

    @Autowired
    public UserRestApi(UserService userService) {
        this.service = userService;
    }

    /* @ApiOperation(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
             notes = "La operación de la API AddFirstBuy provee un OTP para realizar la primera compra sin necesidad de tarjeta de crédito.",
             value = "La operación de la API AddFirstBuy provee un OTP para realizar la primera compra sin necesidad de tarjeta de crédito.")
     @ApiResponses(value = {
             @ApiResponse(code = 200, message = "Transacción éxitosa de primera compra.", response = UserDTORequest.class),
             @ApiResponse(code = 201, message = "Código de Estado HTTP no soportado en la API.", response = void.class),
             @ApiResponse(code = 401, message = "Error en la configuración de certificados ó seguridad del cliente."),
             @ApiResponse(code = 403, message = "Inconsistencia en la peticíon, retorna un JSON con el tipo de error: LogRefServicios.DATO_CORRUPTO_EXCEPTION."),
             @ApiResponse(code = 500, message = "Error Interno, retorna un JSON con el tipo de error: LogRefServicios.")
     })*/
    @GetMapping("/user")
    public List<UserPersonDTO> getAllPersons() throws ServiceException {
        return service.getAllUser();
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserPersonDTO> getUserById(@PathVariable("id") Long id)
            throws ServiceException {
        UserPersonDTO entity = service.getUserById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("user/{id}/info")
    public ResponseEntity<UserPersonDTO> getUserAllInfoById(@PathVariable("id") Long id)
            throws ServiceException {
        UserPersonDTO entity = service.getUserAllInfoById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO entity) throws ServiceException {
        UserResponseDTO updated = service.createUser(entity);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping("/user/{id}/status/{isActive}")
    public ResponseEntity<UserPersonDTO> updateStatus(@PathVariable Boolean isActive, @PathVariable long id)
            throws ServiceException {
        return new ResponseEntity<>(service.updateStatusUser(isActive, id), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<UserPersonDTO> login(@PathVariable String username, @PathVariable String password) throws ServiceException {
        UserPersonDTO updated = service.login(username,password);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/registerUserData",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> registerPersonalDataUser(@RequestBody UserRequestDTO userRequest) throws ServiceException {
        UserResponseDTO updated = service.registerPersonalDataUser(userRequest);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }
}

package com.securebank.securenank.Controller;

import com.securebank.securenank.DTO.direction_userDTO;
import com.securebank.securenank.Service.ServiceDirectionUser;
import com.securebank.securenank.Utils.JsonResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/direction")
public class DirectionUserController {

    private ServiceDirectionUser servicedirectionUser;

    @PostMapping("/")
    public ResponseEntity<?> saveDirection(@Valid direction_userDTO directionUser){
        direction_userDTO newDirection = servicedirectionUser.saveDirectionUser(directionUser);
        return JsonResponse.sendJsonGenericDto(newDirection);
    }

    @GetMapping("/")
    public ResponseEntity<?> getDirections(){
        List<direction_userDTO> findDirections = servicedirectionUser.findAllDirection();
        return JsonResponse.sendJsonGenericObjectList(findDirections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDirectionById(@PathVariable int id){
        direction_userDTO findDirection = servicedirectionUser.findByIdDirection(id);
        return JsonResponse.sendJsonGenericDto(findDirection);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDirectionAll(@PathVariable int id, @Valid direction_userDTO directionUserDTO){
        direction_userDTO directionUpdated = servicedirectionUser.updateDirection(directionUserDTO, id);
        return JsonResponse.sendJsonGenericDto(directionUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDirection(@PathVariable int id){
        String response = servicedirectionUser.deleteDirection_user(id);
        return JsonResponse.sendJsonSuccessResponse(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchDirection(@PathVariable int id, Map<String, Object> newData){
        direction_userDTO patchDirectionNew = servicedirectionUser.patchDirection(newData, id);
        return JsonResponse.sendJsonGenericDto(patchDirectionNew);
    }
}

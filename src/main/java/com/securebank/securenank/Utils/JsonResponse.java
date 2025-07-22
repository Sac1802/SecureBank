package com.securebank.securenank.Utils;
import org.springframework.http.ResponseEntity;
import com.securebank.securenank.DTO.JsonDto;

import java.util.List;

/***
 * Class in charge of creating the responses
 */
public class JsonResponse {

    public JsonResponse(){

    }

    public static ResponseEntity<?> sendJsonSuccessResponse(String message) {
        JsonDto jsonDto = new JsonDto(StatusMessage.SUCCESS, message);
        return ResponseEntity.ok().body(jsonDto);
    }

    public static ResponseEntity<?> sendJsonErrorClientBadRequest(String message){
        JsonDto jsonDto = new JsonDto(StatusMessage.CLIENT_ERROR, message);
        return ResponseEntity.badRequest().body(jsonDto);
    }

    public static ResponseEntity<?> sendJsonErrorServerInauthorized(String message){
        return sendJsonErrorClientBadRequest(message);
    }

    public static ResponseEntity<?> sendJsonErrorServerForbidden(String message){
        JsonDto jsonDto = new JsonDto(StatusMessage.CLIENT_ERROR, message);
        return ResponseEntity.status(403).body(jsonDto);
    }

    public static ResponseEntity<?> sendJsonErrorServerNotFound(String message){
        JsonDto jsonDto = new JsonDto(StatusMessage.CLIENT_ERROR, message);
        return ResponseEntity.status(404).body(jsonDto);
    }

    public static ResponseEntity<?> sendJsonErrorServerInternalServerError(String message){
        JsonDto jsonDto = new JsonDto(StatusMessage.SERVER_ERROR, message);
        return ResponseEntity.status(500).body(jsonDto);
    }

    public static ResponseEntity<?> sendJsonErrorClientBadGateway(String message){
        JsonDto jsonDto = new JsonDto(StatusMessage.SERVER_ERROR, message);
        return ResponseEntity.status(502).body(jsonDto);
    }

    public static ResponseEntity<?> sendJsonErrorServiceUnavailable(String message){
        JsonDto jsonDto = new JsonDto(StatusMessage.SERVER_ERROR, message);
        return ResponseEntity.status(503).body(jsonDto);
    }

    public static <T> ResponseEntity<?> sendJsonGenericDto(T object) {
        return ResponseEntity.ok().body(object);
    }

    public static <T> ResponseEntity<?> sendJsonGenericObjectList(List<T> objects) {
        if (objects != null && !objects.isEmpty()) {
            return ResponseEntity.ok().body(objects);
        }
        return ResponseEntity.noContent().build();
    }
}

package projeto.herois.exception;

import javax.persistence.RollbackException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import projeto.herois.payload.ApiResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(new ApiResponse(false, "Acesso não Autorizado!"),new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, NumberFormatException.class})
    public ResponseEntity<Object> handleTypeException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(new ApiResponse(false, "Formato invalido!"),new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler({NotFoundException.class, IllegalArgumentException.class, InvalidFormatException.class})
    public ResponseEntity<Object> handleIdNotFoundException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(new ApiResponse(false, "Id não encontrado!"),new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler({RollbackException.class})
    public ResponseEntity<Object> handleConflictException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(new ApiResponse(false, "Tamanho minimo de usuario é 4 caracteres!"),new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}

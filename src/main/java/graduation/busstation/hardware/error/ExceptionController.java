package graduation.busstation.hardware.error;


import graduation.busstation.hardware.util.ClientIpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<ErrorResult> unauthorizedExceptionHandler(IllegalArgumentException e){
        log.error("미등록 사용자 접근");
        ErrorResult errorResult = new ErrorResult("unauthorized","접근 권한이 없습니다.");
        return new ResponseEntity<>(errorResult, HttpStatus.FORBIDDEN);
    }


}

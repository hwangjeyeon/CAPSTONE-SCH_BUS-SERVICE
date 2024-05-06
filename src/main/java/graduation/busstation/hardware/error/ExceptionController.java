package graduation.busstation.hardware.error;



import jakarta.transaction.NotSupportedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResult> unauthorizedExceptionHandler(IllegalArgumentException e){
        log.error("미등록 사용자 접근, 검증 실패 이유 = {}", e.getMessage());
        ErrorResult errorResult = new ErrorResult("unauthorized","접근 권한이 없습니다.");
        return new ResponseEntity<>(errorResult, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void parsingExceptionHandler(){
        log.error("잘못된 JSON 형식 전송");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public void noResourceExceptionHandler(NoResourceFoundException e) throws NoResourceFoundException{
        throw e;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void notSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) throws HttpRequestMethodNotSupportedException{
        throw e;
    }


    @ExceptionHandler(Exception.class)
    public void unknownExceptionHandler(Exception e) throws Exception {
        log.error("-- 알 수 없는 예외 발생 --", e);
        throw new Exception(e);
    }


}

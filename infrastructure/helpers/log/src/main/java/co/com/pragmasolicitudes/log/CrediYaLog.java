package co.com.pragmasolicitudes.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CrediYaLog {

    public void info(String message, Object... args) {
        log.info(message, args);
    }

    public void error(String message, Object... args) {
        log.error(message, args);
    }

    public void warn(String message, Object... args) {
        log.warn(message, args);
    }
}
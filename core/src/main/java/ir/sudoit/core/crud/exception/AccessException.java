package ir.sudoit.core.crud.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class AccessException extends RuntimeException {

    private final Integer code;

    private final String message;

}
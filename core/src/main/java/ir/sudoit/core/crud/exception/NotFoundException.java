package ir.sudoit.core.crud.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException
{

    private final String code;

    private final String message;

}

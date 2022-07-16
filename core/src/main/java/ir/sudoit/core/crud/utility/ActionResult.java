package ir.sudoit.core.crud.utility;

import ir.sudoit.core.crud.port.out.MetaModel;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ActionResult<T> {

    private MetaModel meta;
    private T data;

    public ActionResult(MetaModel meta, T data) {
        this.meta = meta;
        this.data = data;
    }


    public ActionResult(MetaModel meta) {
        this.meta = meta;
    }
}
package ir.sudoit.core.crud.utility;

import ir.sudoit.core.crud.port.out.MetaModel;

public class ApplicationUtilities {
    public static <T> ActionResult<T> toActionResultWithObject(T object, PropertiesConfig propertiesConfig) {
        return new ActionResult<T>(MetaModel.getInstance(propertiesConfig), object);
    }

    public static ActionResult toActionResult(PropertiesConfig propertiesConfig) {
        return new ActionResult(MetaModel.getInstance(propertiesConfig));
    }
}

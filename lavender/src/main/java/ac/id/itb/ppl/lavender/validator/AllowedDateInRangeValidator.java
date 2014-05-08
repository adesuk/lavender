package ac.id.itb.ppl.lavender.validator;

import ac.id.itb.ppl.lavender.model.Periode;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author edbert
 */
@FacesValidator("allowedDateInRangeValidator")
public class AllowedDateInRangeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        if (value == null) {
            return; // Let required="true" handle.
        }

        UIInput periodeComponent = (UIInput) component.getAttributes().get("periodeComponent");

        if (!periodeComponent.isValid()) {
            return; // Already invalidated. Don't care about it then.
        }

        Date selectedDate = (Date) periodeComponent.getValue();
        
        if (selectedDate == null) {
            return;
        }
        
        Date startDate = ((Periode) value).getPeriodeAwal();
        Date endDate = ((Periode) value).getPeriodeAkhir();
        
        System.out.println(">>> Validasinya <<<");
        if (selectedDate.before(startDate) || selectedDate.after(endDate)) {
            System.out.println(">>> masuk if nya <<<");
            System.out.println(">>> start date : " + startDate + " <<<");
            System.out.println(">>> end date : " + endDate + " <<<");
            periodeComponent.setValid(false);
            throw new ValidatorException(
                new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Tanggal pelaksanaan tidak valid.",
                    null
                )
            );
        }
    }
    
}

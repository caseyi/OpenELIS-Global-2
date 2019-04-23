package spring.mine.siteinformation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.siteinformation.form.SiteInformationForm;

@Component
public class SiteInformationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SiteInformationForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SiteInformationForm form = (SiteInformationForm) target;

		// check what encrypted means in this context (is it just masked from view
		// like in the current method
		// or should it be completely unaccessible client side)
		// encrypted doesn't need validation

		ValidationHelper.validateOptionField(form.getValueType(), "valueType", errors,
				new String[] { "boolean", "logoUpload", "text", "freeText", "dictionary", "complex" });

		ValidationHelper.validateOptionField(form.getSiteInfoDomainName(), "siteInfoDomainName", errors,
				new String[] { "non_conformityConfiguration", "WorkplanConfiguration", "PrintedReportsConfiguration",
						"sampleEntryConfig", "ResultConfiguration", "MenuStatementConfig", "PaitientConfiguration",
						"SiteInformation" });

		ValidationHelper.validateOptionField(form.getTag(), "tag", errors,
				new String[] { "enable", "url", "numericOnly", "programConfiguration", "localization", "", null });

	}

}
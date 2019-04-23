package spring.generated.inventory.validation;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import spring.generated.inventory.form.InventoryForm;
import spring.mine.common.validator.ValidationHelper;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;

@Component
public class InventoryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return InventoryForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		InventoryForm form = (InventoryForm) target;

		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(form.getNewKitsXML())));

			NodeList newKits = doc.getElementsByTagName("kit");
			for (int i = 0; i < newKits.getLength(); ++i) {
				Element kit = (Element) newKits.item(i);

				ValidationHelper.validateFieldAndCharset(kit.getAttribute("kitName"), "newKitsXML",
						"Kit Name[" + i + "]", errors, true, 255, "a-zA-Z0-9_");

				ValidationHelper.validateDateField(kit.getAttribute("receiveDate"), "newKitsXML",
						"Received date[" + i + "]", errors, DateRelation.PAST);

				ValidationHelper.validateDateField(kit.getAttribute("expirationDate"), "newKitsXML",
						"Expiration date[" + i + "]", errors, DateRelation.ANY);

				ValidationHelper.validateFieldAndCharset(kit.getAttribute("lotNumber"), "newKitsXML",
						"lotNumber[" + i + "]", errors, false, 255, "0-9#");

				ValidationHelper.validateField(kit.getAttribute("kitType"), "newKitsXML", "Type[" + i + "]", errors,
						false, 255, "^$|^HIV$|^SYPHILIS$");

				ValidationHelper.validateIdField(kit.getAttribute("organizationId"), "newKitsXML", "Source[" + i + "]",
						errors, true);
			}

		} catch (SAXException | IOException | ParserConfigurationException e) {
			errors.rejectValue("newKitsXMl", "errors.field.format.xml");
			e.printStackTrace();
		}
	}

}

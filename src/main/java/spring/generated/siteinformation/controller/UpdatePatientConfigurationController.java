package spring.generated.siteinformation.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.PatientConfigurationForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class UpdatePatientConfigurationController extends BaseController {
  @RequestMapping(
      value = "/UpdatePatientConfiguration",
      method = RequestMethod.GET
  )
  public ModelAndView showUpdatePatientConfiguration(HttpServletRequest request,
      @ModelAttribute("form") PatientConfigurationForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new PatientConfigurationForm();
    }
    form.setFormName("PatientConfigurationForm");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    if (form.getErrors() != null) {
    	errors = (BaseErrors) form.getErrors();
    }
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("insertSuccess".equals(forward)) {
      return new ModelAndView("/PatientConfigurationMenu.do", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("/PatientConfiguration.do", "form", form);
    } else {
      return new ModelAndView("PageNotFound");
    }
  }

  protected String getPageTitleKey() {
    return null;
  }

  protected String getPageSubtitleKey() {
    return null;
  }
}
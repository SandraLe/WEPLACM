package framework.web;

import java.io.IOException;
import java.util.Set;

import javax.ejb.EJBException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class Util {

	public static String getCausingMessage(EJBException e) {
		Throwable cause = getRootCause(e);
		if (cause instanceof ConstraintViolationException) {
			return getConstraintMessages((ConstraintViolationException) cause);
		} else {
			return cause.getMessage();
		}
	}

	private static Throwable getRootCause(EJBException e) {
		Throwable cause = e.getCause();
		while (cause.getCause() != null) {
			cause = cause.getCause();
		}
		return cause;
	}

	private static String getConstraintMessages(ConstraintViolationException cve) {
		String messages = "";
		Set<ConstraintViolation<?>> violations = cve.getConstraintViolations();
		if (violations != null)
			for (ConstraintViolation<?> cur : violations)
				messages += cur.getMessage() + " ";
		else
			messages += cve.getMessage();
		return messages;
	}
	
	public static void redirectToRoot() {
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
			ctx.redirect("/" + ctx.getContextName() + "/");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}

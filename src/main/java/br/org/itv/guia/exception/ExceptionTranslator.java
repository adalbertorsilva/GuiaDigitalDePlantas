package br.org.itv.guia.exception;

public class ExceptionTranslator {

	public static Exception translateException(Exception exception) throws Exception{
		
//		Throwable realCause = exception.getCause().getCause(); 
//		
//		if(realCause instanceof ConstraintViolationException){
//			
//			if(((ConstraintViolationException) realCause).getConstraintViolations().stream().findFirst().get().getMessageTemplate().contains("NotNull")){
//				throw new MissingFieldException();
//			}
//			
//			if(((ConstraintViolationException) realCause).getConstraintViolations().stream().findFirst().get().getMessageTemplate().contains("NotEmpty")){
//				throw new MissingFieldException();
//			}
//			
//		}
		
		return exception;
	}
	
}

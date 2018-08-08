package hospitalService;

public class DiagnosticSystemClass {
	FunctionClass fn;
	public DiagnosticSystemClass() throws Exception {
		fn=new FunctionClass();
	}

	public void testDetails() throws Exception {
		// TODO Auto-generated method stub
		
		try{
		
			fn.testDetails();//FunctionClass
			
		}
		catch(InvalidEmailException e){
			System.out.println("InvalidEmailId");
		}
		
		
	}

	public void generateReport() throws Exception {
		// TODO Auto-generated method stub
		
		try {
			fn.generateReport();//FunctionClass
		} catch (InvalidEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}

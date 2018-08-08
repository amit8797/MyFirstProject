package hospitalClient;

//import DiagnosticSystemClass;
import hospitalService.DiagnosticSystemClass;
import hospitalService.FunctionClass;
import hospitalService.InvalidPhoneException;

import java.util.Scanner;

public class MainCaller {

public static void main(String [] args) throws Exception{
		DiagnosticSystemClass ds=new DiagnosticSystemClass();
		//FunctionClass fun=new FunctionClass();
		Scanner sc=new Scanner(System.in);
		System.out.println("=============DIAGNOSTIC CENTRE=============");
		System.out.println("1.Add diagnostic tests");
		System.out.println("2.Generate Report");
		System.out.println("3.EXIT");
		System.out.println("Enter your choice:");
		int choice=sc.nextInt();
		do {
			switch(choice){
				case 1:
					ds.testDetails();//DiagnosticSystemClass
					break;
				case 2:
					ds.generateReport();//DiagnosticSystemClass
					break;
				case 3:
					System.exit(0);
					break;
				default:System.out.println("Wrong Input");
			}
			System.out.println("=============DIAGNOSTIC CENTRE=============");
			System.out.println("1.Add diagnostic tests");
			System.out.println("2.Generate Report");
			System.out.println("3.EXIT");
			System.out.println("Enter your choice:");
			choice=sc.nextInt();
			
		}while(true);
		
	}
}

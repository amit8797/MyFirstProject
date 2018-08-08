package hospitalService;
//import hospitalClient.MainCaller;

//import hospitalEntity.DiagnosticSystemClass;

import hospitalDao.DatabaseConn;
import hospitalDao.Report;

import java.sql.SQLException;
import java.util.*;

public class FunctionClass {

	DatabaseConn addPatientTestConn;

	public FunctionClass() throws Exception {
		addPatientTestConn = new DatabaseConn();
	}

	Scanner sc = new Scanner(System.in);

	public void testDetails() throws InvalidEmailException, InvalidPhoneException, Exception {
		// TODO Auto-generated method stub
		System.out.println("======Enter test details:======");
		System.out.println("1.Fetch patient details by email:");
		System.out.println("2.Fetch patient details by phone:");
		System.out.println("3.EXIT");
		System.out.println("Enter your choice:");
		int choice = Integer.parseInt(sc.nextLine());

		boolean flag = true;
		do {
			switch (choice) {
			case 1:
				System.out.println("Enter Email");
				String email = sc.nextLine();
				System.out.println(email + " hello");
				addtest(email, "e");
				break;

			case 2:
				System.out.println("Enter phone no:");
				String phone = sc.nextLine();
				addtest(phone, "p");
				break;
			case 3:
				System.out.println("returning");
				break;
			}
			System.out.println("======Enter test details:======");
			System.out.println("1.Fetch patient details by email:");
			System.out.println("2.Fetch patient details by phone:");
			System.out.println("3.EXIT");
			System.out.println("Enter your choice:");
			choice = Integer.parseInt(sc.nextLine());
		} while (flag);
	}

	private void addtest(String receive, String status) throws Exception {
		// MainCaller mc=new MainCaller();
		// TODO Auto-generated method stub
		System.out.println("Enter the test name:");
		String testName = sc.nextLine();
		System.out.println(receive + " receive");

		if (status.equals("e")) {

			try {
				addPatientTestConn.testDetailsByEmail(receive, testName);//DatabaseConn
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw e;
			}
			System.out.println("Want to add more tests [YES/NO]");
			String yesNO = sc.nextLine();
			if (yesNO.equals("YES")) {
				addtest(receive, "e");
			}
			if (yesNO.equals("NO")) {
				System.out.println("Test details are successfully saved");
				testDetails();
				System.exit(0);
			}
		}

		else if (status.equals("p")) {

			try {
				addPatientTestConn.testDetailsByPhone(receive, testName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;

			System.out.println("Want to add more tests [YES/NO]");
			String yesNO = sc.nextLine();
			if (yesNO.equals("YES")) {
				addtest(receive, "p");
			}
			if (yesNO.equals("NO")) {
				System.out.println("Test details are successfully saved");
				testDetails();
				// System.exit(0);
			}

		}
	}

	public void generateReport() throws InvalidEmailException, SQLException {
		// TODO Auto-generated method stub
		// FunctionClass func=new FunctionClass();
		System.out.println("=====GENERATE REPORT=====");
		System.out.println("1.Fetch patient details by email:");
		System.out.println("2.Fetch patient details by phone:");
		System.out.println("Enter your choice:");
		int choice = Integer.parseInt(sc.nextLine());
		// DatabaseConn viewDetails = new DatabaseConn();
		ArrayList<String> list=new ArrayList<String>();
		do {
			switch (choice) {
			case 1:
				System.out.println("Enter Email");
				String email = sc.nextLine();

				System.out.println("Enter testDate:");
				String testDate = sc.nextLine();

				break;

			case 2:
				System.out.println("Enter phone no:");
				String phone = sc.nextLine();
				System.out.println("Enter testDate:");
				testDate = sc.nextLine();

				break;

			case 3:
				return;
			}

		} while (choice != 3);

	}

//	public void display(String list) {
//		int sum = 0;
//		System.out.println("=========OUTPUT==========");
//		System.out.println("Name=" + list.get(0).getPatientName());
//		System.out.println("Email=" + list.get(0).getPatientEmail());
//		System.out.println("Phone=" + list.get(0).getPatientPhone());
//		System.out.println("Date=" + list.get(0).getDate());
//
//		System.out.println("Test Details");
//		for (Report obj : list) {
//			System.out.println(obj.getTestName() + " " + obj.getTestCost());
//			sum = sum + Integer.valueOf(obj.getTestCost());
//		}
//
//		System.out.println("Total cost");
//		System.out.println(sum);
//
//	}

}

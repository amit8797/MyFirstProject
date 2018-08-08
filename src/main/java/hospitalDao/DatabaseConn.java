package hospitalDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//import com.google.gson.Gson;

import hospitalService.InvalidEmailException;
//import hospitalService.InvalidEmailException;



public class DatabaseConn {

	
	public Connection con;
	public Statement st;
	public ResultSet rs;
	public DatabaseConn() throws Exception
	{	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/diagnostic_db","root","Welcome123");
			st=con.createStatement();
		} catch (ClassNotFoundException e) {
			throw new InvalidEmailException("Error: class not there");
		}
		catch(SQLException ex)
		{
			throw ex;
			//throw new InvalidEmailException("Connect to MySQL failed");
		}	
	}
	
	public void testDetailsByEmail(String email,String testName)throws SQLException
	{
		//PID as per email
		//String query=+email;		
		String q="Select p_id from patient where email='"+email+"'";
		rs = st.executeQuery(q);
		int pid=-1;
		if(rs.next())
		{
			pid = rs.getInt(1);
		}
		q="Select t_id from test where name='"+testName+"'";
		rs=st.executeQuery(q);
		int tid=-1;
		if(rs.next())
		{
			tid = rs.getInt(1);
		}
		
		if(pid>0 && tid>0)
		{
			
			//setting current date
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(date);
			
			
			//prepared statement call
			PreparedStatement insertPatientDetails=con.prepareStatement("insert into patient_test values(?,?,?)");
			insertPatientDetails.setInt(1, pid);
			insertPatientDetails.setInt(2, tid);
			insertPatientDetails.setString(3, strDate);
			insertPatientDetails.executeUpdate();
		}
		else
		{
			System.out.println("Invalid data");
		}
		
	}
	
	public void testDetailsByPhone(String phone,String testName)throws SQLException
	{
		//PID as per email
		
		
		String q="Select p_id from patient where phone='"+phone+"'";
		rs = st.executeQuery(q);
		int pid=-1;
		if(rs.next())
		{
			pid = rs.getInt(1);
		}
		
		System.out.println(pid);
		
		PreparedStatement getTIDQuery = con.prepareStatement("Select t_id from test where name=?");
		getTIDQuery.setString(1, testName);
		int tid=-1;
		ResultSet rs1 = getTIDQuery.executeQuery();
		if(rs1.next())
		{
			tid = rs1.getInt(1);
		}
		
		if(pid>0 && tid>0)
		{
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
			String strDate = formatter.format(date);
			PreparedStatement insertPatientDetails=con.prepareStatement("insert into patient_test values(?,?,?)");
			insertPatientDetails.setInt(1, pid);
			insertPatientDetails.setInt(2, tid);
			insertPatientDetails.setString(3, strDate);
			insertPatientDetails.executeUpdate();
		}
		else
		{
			System.out.println("Invalid data");
		}
		rs.close();
	}

	public String report(String receive, String testDate,String status) throws SQLException {
		System.out.println("receive : "+receive +" testDate:"+testDate);
		ArrayList<String> reportList=new ArrayList<String>();
		PreparedStatement ps=null;
		String q="";
		if(status.equals("e")){
			ps=con.prepareStatement("select p.name , p.email, p.phone , pt.date , t.name,t.cost from patient p inner join patient_test pt inner join test t on p.p_id=pt.p_id and t.t_id=pt.t_id and p.email=? and pt.date=? order by t.name");
			ps.setString(1, receive);
			ps.setString(2, testDate);
		}
		else if(status.equals("p"))
		{
			ps=con.prepareStatement("select p.name , p.email, p.phone , pt.date , t.name,t.cost from patient p inner join patient_test pt inner join test t on p.p_id=pt.p_id and t.t_id=pt.t_id and p.phone=? and pt.date=? order by t.name");
			ps.setString(1, receive);
			ps.setString(2, testDate);
		}
		rs=ps.executeQuery();
		String data="";
		String s1 = null,s2 = null,s3 = null,s4 = null,s5 = null,s6;
		int cost=0;
		int cs;
		String s = "<table><th>Test Name</th><th>Test Cost</th>";
		String details = "";
		while(rs.next()){
		
			s1=rs.getString(1);
			s2=rs.getString(2);
			s3=rs.getString(3);
			s4=rs.getString(4);
			s5=rs.getString(5);
			s6=rs.getString(6);
			cs=Integer.parseInt(s6);
			reportList.add(s6);
			cost=cost+cs;
			details = "Name:"+rs.getString(1)+"<br> Email:"+rs.getString(2)+"<br> Phone:"+rs.getString(3)+"<br> Test Date:"+rs.getString(4);
			s=s+"<tr><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td></tr>";
			
		}
		s=s+"</table>";
		data=data+"<br>Total cost: "+cost;
		s=details+data+s;
		return s;
		
		
	}	
}

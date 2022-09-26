
package Banking_System;
import java.util.*;
import java.sql.*;
import java.io.*;
	
	public class test_demo
	{
		public static void main(String [] prgrocks)throws IOException
		{
			int n; // n stores the number of rows affected by insert, update or delete
			int ch,ch1;// for user choice switch case
			Connection con=null;// connection object
			Statement stmt=null;// statement object, helps to execute sql statements through java 
			try
			{
				String uid="system",pwd="1234";
				// Load the driver class
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// Create the connection object
				String conurl="jdbc:oracle:thin:@localhost:1521:xe";// predefined
				con=DriverManager.getConnection(conurl,uid,pwd);
				stmt=con.createStatement();
				System.out.println("\n\n***** Banking Management System*****\n\n");
				BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				do
				{
				System.out.println("1: display the customer details");
				System.out.println("2: insert a customer's details");
				System.out.println("3: delete a customer's details");
				System.out.println("4: update a customer's details");
				System.out.println("5: Deposit Money to an Account");
				System.out.println("6: Withdraw Money from an Account");
				System.out.println("7: EXIT\n");
				System.out.println("enter your choice");
				ch=Integer.parseInt(br.readLine());
				switch(ch)
				{
				case 1://displaying customer records
				int c=0;
					String sqlstr="select * from customer_details";// sql query
					ResultSet rs=stmt.executeQuery(sqlstr);// ResultSet reference is a matrix
					while(rs.next())
					{
						System.out.print(rs.getString(1)+"\t\t");// these indices are the column number of the column
						System.out.print(rs.getString(2)+"\t\t");
						System.out.print(rs.getString(3)+"\t\t");
						System.out.print(rs.getString(4)+"\t\t");
						System.out.print(rs.getString(5)+"\t\t");
						System.out.print(rs.getString(6)+"\t\t");
						System.out.print(rs.getString(7)+"\t\t");
						System.out.print(rs.getString(8)+"\t\t");
						System.out.println(rs.getString(9)+"\t\t\n");
						c++;
					}
					System.out.println(c+" rows selected !!!\n\n");
					break;
				case 2://inserting records in customer table
					System.out.println("enter the cust_no of the customer");
					String cno=br.readLine(); // readLine() is used to take string type user input
					System.out.println("enter the name of the customer");
					String name=br.readLine();
					System.out.println("enter the phone no. of the customer");
					long phn=Long.parseLong(br.readLine());
					System.out.println("enter the email of the customer");
					String email=br.readLine();
					System.out.println("enter the city of the customer");
					String city=br.readLine();
					Random r = new Random();
				    int accountno = 1000000000 + (int)(r.nextDouble() * 999999999);
					
					System.out.println("enter the account type: Savings or Current");
					String type=br.readLine();
					System.out.println("enter the branch code of the customer");
					String branch_code=br.readLine();
					System.out.println("enter the balance of the customer");
					String balance=br.readLine();
					String insstr="insert into customer_details values('"+cno+"','"+name+"',"+phn+",'"+email+"','"+city+"','"+accountno+"','"+type+"','"+branch_code+"','"+balance+"')";// sql query
					n=stmt.executeUpdate(insstr);// n returns the number of rows added
					System.out.println(n+" \nrows inserted\n\n");
					break;
				case 3:// delete the details of a customer
					System.out.println("enter the cust_no of the customer");
					String cn=br.readLine(); // readLine() is used to take string type user input
					String delstr="delete from customer_details  where cust_id='"+cn+"'";// sql query
					n=stmt.executeUpdate(delstr);// n returns the number of rows added
					System.out.println("\n"+n+" rows deleted\n");
					break;
				case 4: // update the record's  of a customer
					System.out.println("enter the cust_no of the customer");
					String cn4=br.readLine(); 
					String updstr="";// since it is getting modified inside switch case
					System.out.println("1: Update name of the customer");
					System.out.println("2: Update phone_no of the customer");
					System.out.println("3: Update email address of the customer");
					System.out.println("4: Update city of the customer");
					System.out.println("enter your choice\n");
					ch1=Integer.parseInt(br.readLine());
					switch(ch1)
					{
						case 1:
							System.out.println("enter the updated name of the customer");
							String name4=br.readLine();
							updstr="update customer_details set cust_name='"+name4+"' where cust_id='"+cn4+"'";// sql query
						break;
						case 2:
							System.out.println("enter the updated phone no. of the customer");
							long phn4=Long.parseLong(br.readLine());
							updstr="update customer_details set phone_no="+phn4+" where cust_id='"+cn4+"'";// sql query
						break;
						case 3:
							System.out.println("enter the updated of the customer");
							String email4=br.readLine();
							updstr="update customer_details set email='"+email4+"' where cust_id='"+cn4+"'";// sql query
						break;
						case 4:
							System.out.println("enter the  updated city of the customer");
							String city4=br.readLine();
							updstr="update customer_details set address='"+city4+"' where cust_id='"+cn4+"'";// sql query
						break;
					}
					n=stmt.executeUpdate(updstr);// n returns the number of rows added
					System.out.println("\n"+n+" rows updated\n");
					break;
				
				
				case 5://Deposit Money to an Account
					System.out.println("enter the account number");
					String acc7=br.readLine();
					System.out.println("enter the amount to be deposited");
					int amt7=Integer.parseInt(br.readLine());
					String sqlstr71="select balance from customer_details where account_no='"+acc7+"'";
					ResultSet rs71=stmt.executeQuery(sqlstr71);// ResultSet reference is a matrix
					System.out.print("Previous balance is: \t");
					while(rs71.next())
						System.out.println(rs71.getString("balance")+"\n");
					updstr="update customer_details set balance=balance+"+amt7+" where account_no='"+acc7+"'";// sql query
					n=stmt.executeUpdate(updstr);// n returns the number of rows added
					//System.out.println("\n"+n+" rows updated\n");
					String sqlstr72="select balance from customer_details where account_no='"+acc7+"'";
					ResultSet rs72=stmt.executeQuery(sqlstr72);// ResultSet reference is a matrix
					System.out.print("Updated balance is: \t");
					while(rs72.next())
						System.out.println(rs72.getString("balance")+"\n");
				break;
				case 6://Withdraw Money from an Account
					int bal8=0;
					System.out.println("enter the account number");
					String acc8=br.readLine();
					System.out.println("enter the amount to be withdraw");
					int amt8=Integer.parseInt(br.readLine());
					String sqlstr81="select balance from customer_details where account_no='"+acc8+"'";
					ResultSet rs81=stmt.executeQuery(sqlstr81);// ResultSet reference is a matrix
					System.out.print("Previous balance is: \t");
					while(rs81.next())
					{
						System.out.println(rs81.getString("balance")+"\n");
						bal8=Integer.parseInt(rs81.getString("balance"));
					}
					
					if(bal8>=amt8)
					{
						
						
						updstr="update customer_details set balance=balance-"+amt8+" where account_no='"+acc8+"'";// sql query
						n=stmt.executeUpdate(updstr);// n returns the number of rows added
						//System.out.println("\n"+n+" rows updated\n");
						String sqlstr82="select balance from customer_details where account_no='"+acc8+"'";
						ResultSet rs82=stmt.executeQuery(sqlstr82);// ResultSet reference is a matrix
						System.out.print("Current balance is: \t");
						while(rs82.next())
						System.out.println(rs82.getString("balance")+"\n");
					}
					else {
						
						
						System.out.println("Insufficient Balance !!!!!\n");
					}
				break;
				case 7: //exit case
					stmt.close();
					con.close();
					System.out.println("\nThank you\n");
					System.exit(0);
					break;
				default:
					System.out.println("\nWrong choice\n");
					}// end of switch case
				}// end of do block
				while(ch!=7);			
			}// end of try block
			catch(Exception e)
			{
				System.out.println(e);
			}
		}// end of main method
	}// end of public class


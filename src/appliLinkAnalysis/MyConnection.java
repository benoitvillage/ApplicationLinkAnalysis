package appliLinkAnalysis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MyConnection {

	
	  private Connection connect_eor_global_nagiosbp = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;
	  private String globalNagiosBPDatabase = "global_nagiosbp";
	  private ShareVariables shareVariable;
	  
	  public MyConnection (ShareVariables shareVariable) {
		  
		  this.shareVariable = shareVariable;
		  try {
			this.readDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  }
	  
	  public void readDataBase() throws Exception {
	    try {
	      // this will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // setup the connection with the DB.
	      connect_eor_global_nagiosbp = DriverManager
	          .getConnection("jdbc:mysql://" + this.shareVariable.getHost() + ":3306/global_nagiosbp?"
	              + "user=" + this.shareVariable.getUser() + "&password=" + 
	              this.shareVariable.getPassword());
	      
	      
	    } catch (Exception e) {
	      throw e;
	    }

	  }

	public ArrayList<Bp> getAllBpCategory() {
		// TODO Auto-generated method stub
		
		ArrayList <Bp> bpCategoryList = new ArrayList <Bp>();
		
		try {
				
			// resultSet gets the result of the SQL query
				this.preparedStatement = connect_eor_global_nagiosbp
					      .prepareStatement("SELECT * from bp_category");
				
				resultSet =	preparedStatement.executeQuery();

				String bpSource;
				String bpName;
				String bpCategory;
				Bp myBP;
				
				while (resultSet.next()) {
	  
					
					bpSource = resultSet.getString("bp_source");
					bpName = resultSet.getString("bp_name");
					bpCategory = resultSet.getString("category");
					myBP =  new Bp(bpName,bpSource,bpCategory);
					bpCategoryList.add(myBP);
				      
				}
			
				this.resultSet.close();
				this.preparedStatement.close();
					

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return bpCategoryList;
			}
		return bpCategoryList;
	}

	/**
	 * 
	 * @param bpName : name of the bp with category
	 * @param bpSource :  name of the bp with category
	 * @return list of bp above bp with category in bp_links
	 */
	public ArrayList<Bp> getAllHighBpList(String bpNameCategory, String bpSourceCategory) {
		// TODO Auto-generated method stub
		
		ArrayList <Bp> bpCategoryList = new ArrayList <Bp>();
		
		try {
				
			// resultSet gets the result of the SQL query
				this.preparedStatement = connect_eor_global_nagiosbp
					      .prepareStatement("SELECT bp_name, "
					      				  + "'global' as master_source "
					      				  + "FROM bp_links "
					      				  + "WHERE bp_link = ? and bp_source = ? ");
				
				preparedStatement.setString(1, bpNameCategory);
				preparedStatement.setString(2, bpSourceCategory);
				resultSet =	preparedStatement.executeQuery();

				String bpSource;
				String bpName;
				Bp myBP;
				
				System.out.println("Liste bp high for " + bpNameCategory + " " + bpSourceCategory);
				
				while (resultSet.next()) {
	  
					
					bpSource = resultSet.getString("master_source");
					bpName = resultSet.getString("bp_name");
					myBP =  new Bp(bpName,bpSource,"NA");
					bpCategoryList.add(myBP);
					
					System.out.println("   " + bpName + " " + bpName);
					
				      
				}
			
				this.resultSet.close();
				this.preparedStatement.close();
					

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return bpCategoryList;
			}
		return bpCategoryList;
	}

	public ArrayList<Bp> getAllBottomBpList(String bpNameCategory, String bpSourceCategory) {
		// TODO Auto-generated method stub
		ArrayList <Bp> bpCategoryList = new ArrayList <Bp>();
		
		try {
			
			   System.out.println("Liste bp bottom for " + bpNameCategory + " " + bpSourceCategory);
			
			// resultSet gets the result of the SQL query
				this.preparedStatement = connect_eor_global_nagiosbp
					      .prepareStatement("SELECT bp_link, "
					      				  + "bp_source "
					      				  + "FROM bp_links "
					      				  + "WHERE bp_name = ? ");
				
				preparedStatement.setString(1, bpNameCategory);
				resultSet =	preparedStatement.executeQuery();

				String bpSource;
				String bpName;
				Bp myBP;
				
				while (resultSet.next()) {
	  
					
					bpSource = resultSet.getString("bp_source");
					bpName = resultSet.getString("bp_link");
					myBP =  new Bp(bpName,bpSource,"NA");
					bpCategoryList.add(myBP);
				     
					System.out.println("   " + bpName + " " + bpName);
				}
			
				this.resultSet.close();
				this.preparedStatement.close();
					

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return bpCategoryList;
			}
		return bpCategoryList;
	}

	public void truncateBPLinksAnalysis() {
		// TODO Auto-generated method stub
		
		try {
		
			preparedStatement = connect_eor_global_nagiosbp.prepareStatement("TRUNCATE TABLE bp_links_analysis ;");
			preparedStatement.executeUpdate();
		
			this.preparedStatement.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertBPLinksAnalysis(String highBPName, String highBPSource, String bottomBPName,
			String bottomBpSource, String bpName, String bpSource) {
		// TODO Auto-generated method stub
		try {
			preparedStatement = connect_eor_global_nagiosbp.prepareStatement("insert into bp_links_analysis " +
										" values (?,?,?,?,?,?)");
			preparedStatement.setString(1, highBPName);
			preparedStatement.setString(2, highBPSource);
			preparedStatement.setString(3, bottomBPName);
			preparedStatement.setString(4, bottomBpSource);
			preparedStatement.setString(5, bpName);
			preparedStatement.setString(6, bpSource);
			preparedStatement.executeUpdate();
		
			this.resultSet.close();
			this.preparedStatement.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
	 

}
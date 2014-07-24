import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Populate {


	static Connection connection = null;
	static String folderName ="C:/Users/Sultan/" +
			"Documents/MATLAB/MLPROJECT2nd/v6_July/HTT/";


	public static String replaceInnerSingleQoute(String old)
	{


		if(numberOf(old,'\'')>2)
		{
			old =old.trim();
			int str_size = old.length();
			String tempStr = old.substring(1,str_size-2);
			tempStr = tempStr.replaceAll("'", "''");
			old="'"+tempStr+"'";
		}
		return old;

	}
	public static int numberOf(String str,int c) {
		int res=0;
		if(str==null)
			return res;
		for(int i=0;i<str.length();i++)
			if(c==str.charAt(i))
				res++;
		return res;
	}


	public static void main(String[] argv) {

		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		connection = null;;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/UKNew", "postgres",
					"password");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			//populateRetweet();
			//populateTweet();
			//populateRetweet();
			//populateUser();
			//populateHashtags();
			//populateUrl();
			//populateUser_mention();
			populate_hashtag_picked();
		} else {
			System.out.println("Failed to make connection!");
		}
		// Populate tables



	}

	public static void populate_hashtag_picked()
	{
		Statement stmt = null;
		try{
			Class.forName("org.postgresql.Driver");

			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = connection.createStatement();
			BufferedReader br = new BufferedReader(new FileReader(folderName+
					"hashtag_picked.csv"));

			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				int count = 0;
				int rowNum=0;
				while (line != null) {
					count++;
					rowNum++;
					if(count==1){
						line = br.readLine();
						continue;
					}
					int size=0;
					line=line.replaceAll("\"", "'");
					String []str = line.split("\\$epr\\$");
					size = str.length;
					boolean c=false;

					while(size<2)
					{
						line=line+ br.readLine();
						line=line.replaceAll("\"", "'");
						str = line.split("\\$epr\\$");
						size = str.length;
						count++;

					}

					System.out.println("Line= "+line);
					System.out.println("The Size = "+size+"  & line: "+count);

					String tempStr0 = str[0];
					String tempStr1 = str[1];
					String tempStr2 = str[2];
					String tempStr3 = str[3];
					String tempStr4 = str[4];
					String tempStr5 = str[5];
					String tempStr6 = str[6];
					

					String sql = "INSERT INTO hashtag_picked (hashtag,count,mean,sigma,date,last_modified,id)"
							+ "VALUES ("+
							tempStr0+","+
							tempStr1+","+
							tempStr2+","+
							tempStr3+","+
							tempStr4+","+
							tempStr5+","+
							tempStr6+");";
							
					System.out.println(sql);
					stmt.executeUpdate(sql);
					line = br.readLine();
					//if(count>10)
					 //break;
				}
			}
			finally {
				br.close();
			}


			stmt.close();
			connection.commit();
			//connection.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");

	}

	public static void populateUser_mention()
	{
		Statement stmt = null;
		try{
			Class.forName("org.postgresql.Driver");

			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = connection.createStatement();
			BufferedReader br = new BufferedReader(new FileReader(folderName+
					"user_mention.csv"));

			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				int count = 0;
				int rowNum=0;
				while (line != null) {
					count++;
					rowNum++;
					if(count==1){
						line = br.readLine();
						continue;
					}
					int size=0;
					line=line.replaceAll("\"", "'");
					String []str = line.split("\\$epr\\$");
					size = str.length;
					boolean c=false;

					while(size<2)
					{
						line=line+ br.readLine();
						line=line.replaceAll("\"", "'");
						str = line.split("\\$epr\\$");
						size = str.length;
						count++;

					}

					System.out.println("Line= "+line);
					System.out.println("The Size = "+size+"  & line: "+count);

					String tempStr0 = replaceInnerSingleQoute(str[0]);
					String tempStr1= replaceInnerSingleQoute(str[1]);
					String tempStr2 = replaceInnerSingleQoute(str[2]);


					String sql = "INSERT INTO user_mention (tweetid,userid,screenname)"
							+ "VALUES ("+tempStr0+","+tempStr1+","+tempStr2+");";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					line = br.readLine();
					//if(count>10)
					// break;
				}
			}
			finally {
				br.close();
			}


			stmt.close();
			connection.commit();
			//connection.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");

	}
	public static void populateUrl()
	{
		Statement stmt = null;
		try{
			Class.forName("org.postgresql.Driver");

			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = connection.createStatement();
			BufferedReader br = new BufferedReader(new FileReader(folderName+
					"url.csv"));

			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				int count = 0;
				int rowNum=0;
				while (line != null) {
					count++;
					rowNum++;
					if(count==1){
						line = br.readLine();
						continue;
					}
					int size=0;
					line=line.replaceAll("\"", "'");
					String []str = line.split("\\$epr\\$");
					size = str.length;
					boolean c=false;

					while(size<2)
					{
						line=line+ br.readLine();
						line=line.replaceAll("\"", "'");
						str = line.split("\\$epr\\$");
						size = str.length;
						count++;

					}

					System.out.println("Line= "+line);
					System.out.println("The Size = "+size+"  & line: "+count);

					String tempStr0 = replaceInnerSingleQoute(str[0]);
					String tempStr1_url= java.net.URLDecoder.decode(str[1], "UTF-8");
					String tempStr1 = replaceInnerSingleQoute(tempStr1_url);


					String sql = "INSERT INTO url (tweetid,url)"
							+ "VALUES ("+tempStr0+","+tempStr1+");";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					line = br.readLine();
					//if(count>10)
					// break;
				}
			}
			finally {
				br.close();
			}


			stmt.close();
			connection.commit();
			//connection.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}


	public static void populateHashtags()
	{
		Statement stmt = null;
		try{
			Class.forName("org.postgresql.Driver");

			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = connection.createStatement();
			BufferedReader br = new BufferedReader(new FileReader(folderName+
					"hashtags.csv"));

			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				int count = 0;
				int rowNum=0;
				while (line != null) {
					count++;
					rowNum++;
					if(count==1){
						line = br.readLine();
						continue;
					}
					int size=0;
					line=line.replaceAll("\"", "'");
					String []str = line.split("\\$epr\\$");
					size = str.length;
					boolean c=false;

					while(size<3)
					{
						line=line+ br.readLine();
						line=line.replaceAll("\"", "'");
						str = line.split("\\$epr\\$");
						size = str.length;
						count++;

					}

					System.out.println("Line= "+line);
					System.out.println("The Size = "+size+"  & line: "+count);

					String tempStr0 = replaceInnerSingleQoute(str[0]);
					String tempStr1 = replaceInnerSingleQoute(str[1]);
					String tempStr2 = replaceInnerSingleQoute(str[2]);

					String sql = "INSERT INTO hashtags (tweetid,hashtag,breaking)"
							+ "VALUES ("+tempStr0+","+tempStr1+",'"+tempStr2+"');";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					line = br.readLine();
					//if(count>10)
					// break;
				}
			}
			finally {
				br.close();
			}


			stmt.close();
			connection.commit();
			//connection.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public static void populateTweet()
	{
		Statement stmt = null;
		try{
			Class.forName("org.postgresql.Driver");

			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = connection.createStatement();
			BufferedReader br = new BufferedReader(new FileReader(folderName+
					"tweet.csv"));

			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				int count = 0;
				int rowNum=0;
				while (line != null) {
					count++;
					rowNum++;
					if(count==1){
						line = br.readLine();
						continue;
					}
					int size=0;
					line=line.replaceAll("\"", "'");
					String []str = line.split("\\$epr\\$");
					size = str.length;
					boolean c=false;

					while(size<7)
					{
						line=line+ br.readLine();
						line=line.replaceAll("\"", "'");
						str = line.split("\\$epr\\$");
						size = str.length;
						count++;

					}

					System.out.println("Line= "+line);
					System.out.println("The Size = "+size+"  & line: "+count);

					String tempStr0 = replaceInnerSingleQoute(str[0]);
					String tempStr1 = replaceInnerSingleQoute(str[1])
							.replace("&gt;", ">")
							.replace("&lt;", "<");
					String tempStr2 = replaceInnerSingleQoute(str[2]);
					String tempStr3 = replaceInnerSingleQoute(str[3]); 
					String tempStr4 = replaceInnerSingleQoute(str[4]); 
					String tempStr5 = replaceInnerSingleQoute(str[5]);
					String tempStr6 = replaceInnerSingleQoute(str[6]);

					String sql = "INSERT INTO tweet (tweetid,text,createdat,geo,place" +
							",retweetcount, userid) "
							+ "VALUES ("+tempStr0+","+tempStr1+","+tempStr2+","+tempStr3+","+tempStr4+","+
							tempStr5+","+tempStr6+");";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					line = br.readLine();
					//if(count>10)
					// break;
				}
			}
			finally {
				br.close();
			}


			stmt.close();
			connection.commit();
			//connection.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}




	public static void populateUser()
	{
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");

			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = connection.createStatement();
			BufferedReader br = new BufferedReader(new FileReader(folderName+
					"user.csv"));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				int count = 0;
				while (line != null) {
					count++;
					if(count==1){
						line = br.readLine();
						continue;
					}
					int size=0;
					line=line.replaceAll("\"", "'");
					String []str = line.split("\\$epr\\$");
					size = str.length;
					boolean c=false;

					while(size<6)
					{
						line=line+ br.readLine();
						line=line.replaceAll("\"", "'");
						str = line.split("\\$epr\\$");
						size = str.length;
						count++;

					}

					System.out.println("Line= "+line);
					System.out.println("The Size = "+size+"  & line: "+count);

					String tempStr0 = replaceInnerSingleQoute(str[0]);
					String tempStr1 = replaceInnerSingleQoute(str[1]);
					String tempStr2 = replaceInnerSingleQoute(str[2]);
					String tempStr3 = replaceInnerSingleQoute(str[3]); 
					String tempStr4 = replaceInnerSingleQoute(str[4]); 
					String tempStr5 = replaceInnerSingleQoute(str[5]);


					String sql = "INSERT INTO \"user\" (userid,name,screenname,url,location" +
							",language) "
							+ "VALUES ("+tempStr0+","+tempStr1+","+tempStr2+","+tempStr3+","+tempStr4+","+
							tempStr5+");";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					line = br.readLine();

					//if(count>10)
					// break;
				}

			} finally {
				br.close();
			}


			stmt.close();
			connection.commit();
			//connection.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}


	public static void populateRetweet()
	{
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");

			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = connection.createStatement();
			BufferedReader br = new BufferedReader(new FileReader(folderName+
					"retweet.csv"));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				int count = 0;
				while (line != null) {
					count++;
					if(count==1 ){
						line = br.readLine();
						continue;
					}
					String []str = line.split("\\$epr\\$");
					int size = str.length;
					String sql = "INSERT INTO retweet (tweetid,uidfrom,uidto) "
							+ "VALUES ("+str[0]+","+str[1]+","+str[2]+");";
					//System.out.println(sql);
					stmt.executeUpdate(sql);
					line = br.readLine();

					// if(count>10)
					// break;
				}

			} finally {
				br.close();
			}


			stmt.close();
			connection.commit();
			//connection.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

}
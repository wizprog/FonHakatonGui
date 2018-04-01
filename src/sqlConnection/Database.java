package sqlConnection;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Database {

	private static String hostName = "babicuni.database.windows.net";
	private static String dbName = "batnUserLog";
	private static String user = "babicuni";
	private static String password = "Vodavoda123";
	private static String url = String.format(
			"jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
			hostName, dbName, user, password);
	private Connection connection = null;
	private static AdvancedEncryptionStandard aes = null;

	public Database() {
		try {
			connection = DriverManager.getConnection(url);
			String schema = connection.getSchema();
			System.out.println("Successful connection - Schema: " + schema);
			aes = new AdvancedEncryptionStandard();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public ResultSet queryMe(String selectSql) { try (Statement statement =
	 * connection.createStatement(); ResultSet resultSet =
	 * statement.executeQuery(selectSql)) { while (resultSet.next()) {
	 * System.out.println(resultSet.getString(1) + " " + resultSet.getString(2)); }
	 * return resultSet; } catch (Exception e) { e.printStackTrace(); return null; }
	 * }
	 */

	public User queryLog(String user, String pass) {
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(String.format(
						"SELECT * FROM UserLogin WHERE CONVERT(VARCHAR, Username)='%s' AND CONVERT(VARCHAR, Password)='%s'",
						user, pass))) {
			while (resultSet.next()) {
				return new User(resultSet.getString(1), resultSet.getString(2));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean queryCreateAcc(String user, String pass) {

		String aesUser = aes.cipherText(user);
		String aesPass = aes.cipherText(pass);
		try (Statement statement = connection.createStatement()) {
			return statement.execute(
					String.format("INSERT INTO UserLogin(Username,Password) VALUES('%s','%s')", aesUser, aesPass));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getLastHour(HashMap<String, int[]> hashmap, int array[], int oldArray[]) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		String to = dtf.format(now);
		String from = dtf.format(now.minusHours(1));

		// String local = String.format("SELECT TOP (1000) [\"vreme\"] FROM
		// dbo.fon_hackaton_2018 WHERE [\"vreme\"] BETWEEN '\"%s\"' and
		// '\"%s\"'", from, to);

		try (Statement statement = connection.createStatement()) {
			statement.execute(String.format("DROP VIEW dbo.UserByBs"));
			statement.execute(String.format(
					"CREATE VIEW UserByBs AS SELECT [\"naziv_bs\"] , [\"a_imei\"], AVG(CONVERT(INT,[\"trajanje\"])) AS 'SrednjaVrednost' FROM dbo.fon_hackaton_2018  WHERE [\"tip_zapisa\"] = '\"ODLAZNI POZIV\"' AND [\"vreme\"] BETWEEN '\"%s\"' AND '\"%s\"' GROUP BY [\"naziv_bs\"] , [\"a_imei\"]",
					from, to));
			statement.execute(String.format("DELETE FROM dbo.BS_CALLS"));
			statement.execute(String.format(
					"INSERT INTO dbo.BS_CALLS SELECT [\"naziv_bs\"], AVG(SrednjaVrednost)  FROM dbo.UserByBs GROUP BY [\"naziv_bs\"] ORDER BY  AVG(SrednjaVrednost) DESC"));

			statement.execute(String.format("DROP VIEW dbo.UserByBs"));
			statement.execute(String.format(
					"CREATE VIEW UserByBs AS SELECT [\"naziv_bs\"] , [\"a_imei\"], COUNT([\"trajanje\"]) AS 'SrednjaVrednostPoruka' FROM dbo.fon_hackaton_2018 WHERE [\"tip_zapisa\"] = '\"ODLAZNI SMS\"' AND [\"vreme\"] BETWEEN '\"%s\"' AND '\"%s\"' GROUP BY [\"naziv_bs\"] , [\"a_imei\"] ",
					from, to));
			statement.execute(String.format("DELETE FROM dbo.BS_SMS"));
			statement.execute(String.format(
					"INSERT INTO dbo.BS_SMS SELECT [\"naziv_bs\"], AVG(SrednjaVrednostPoruka)  FROM dbo.UserByBs GROUP BY [\"naziv_bs\"] ORDER BY  AVG(SrednjaVrednostPoruka) DESC"));

			statement.execute(String.format("DROP VIEW dbo.UserByBs"));
			statement.execute(String.format("CREATE VIEW UserByBs AS\r\n"
					+ "SELECT [\"naziv_bs\"] , [\"a_imei\"], AVG(CONVERT(INT,[\"download\"])) AS 'SrednjaVrednostInterneta'\r\n"
					+ " FROM dbo.fon_hackaton_2018 \r\n"
					+ "WHERE [\"vreme\"] BETWEEN '\"%s\"' AND '\"%s\"' AND [\"tip_zapisa\"] = '\"MOBILNI INTERNET\"' AND [\"download\"] != 'NA'\r\n"
					+ "GROUP BY [\"naziv_bs\"] , [\"a_imei\"]", from, to));
			statement.execute(String.format("DELETE FROM dbo.BS_INTERNET"));
			statement.execute(String.format(
					"INSERT INTO dbo.BS_INTERNET SELECT [\"naziv_bs\"], AVG(SrednjaVrednostInterneta) FROM dbo.UserByBs GROUP BY [\"naziv_bs\"] ORDER BY  AVG(SrednjaVrednostInterneta) DESC"));

			statement.execute(String.format("DROP VIEW dbo.UserByBs"));
			statement.execute(String.format("CREATE VIEW UserByBs AS\r\n"
					+ "SELECT dbo.BS_CALLS.[Base station] AS 'BS', dbo.BS_CALLS.[Average Cll Duration] AS 'ACD' , dbo.BS_SMS.[Average SMS sent] AS 'ASMS'\r\n"
					+ "FROM dbo.BS_CALLS LEFT JOIN DBO.BS_SMS\r\n"
					+ "ON dbo.BS_CALLS.[Base station] = dbo.BS_SMS.[Base Station]\r\n" + "\r\n" + "UNION \r\n" + "\r\n"
					+ "SELECT dbo.BS_SMS.[Base station] AS 'BS', dbo.BS_CALLS.[Average Cll Duration] AS 'ACD' , dbo.BS_SMS.[Average SMS sent] AS 'ASMS'\r\n"
					+ "FROM dbo.BS_CALLS RIGHT JOIN DBO.BS_SMS\r\n"
					+ "ON dbo.BS_CALLS.[Base station] = dbo.BS_SMS.[Base Station]"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Statement statement = connection.createStatement();
				ResultSet resultSetCalls = statement.executeQuery(String.format(
						"SELECT dbo.UserByBs.[BS] AS 'BS' , dbo.UserByBs.[ACD] ,  dbo.UserByBs.[ASMS] , dbo.BS_INTERNET.[Average DownloadUpload] AS 'ADU'\r\n"
								+ "FROM dbo.UserByBs LEFT JOIN dbo.BS_INTERNET\r\n"
								+ "ON dbo.UserByBs.[BS] = dbo.BS_INTERNET.[Base Station]\r\n" + "\r\n" + "UNION\r\n"
								+ "\r\n"
								+ "SELECT dbo.BS_INTERNET.[Base Station] AS 'BS' , dbo.UserByBs.[ACD] ,  dbo.UserByBs.[ASMS] , dbo.BS_INTERNET.[Average DownloadUpload] AS 'ADU'\r\n"
								+ "FROM dbo.UserByBs RIGHT JOIN dbo.BS_INTERNET\r\n"
								+ "ON dbo.UserByBs.[BS] = dbo.BS_INTERNET.[Base Station]"));
			) {
			
			while (resultSetCalls.next()) {
				int a[] = new int[3];
				if (resultSetCalls.getString(2) == null)
					a[0] = 0;
				else
					a[0] = Integer.parseInt(resultSetCalls.getString(2));
				if (resultSetCalls.getString(3) == null)
					a[1] = 0;
				else
					a[1] = Integer.parseInt(resultSetCalls.getString(3));
				if (resultSetCalls.getString(4) == null)
					a[2] = 0;
				else
					a[2] = Integer.parseInt(resultSetCalls.getString(4));
				hashmap.put(resultSetCalls.getString(1), a);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			
			try (Statement statement = connection.createStatement();
					ResultSet resultSetC = statement.executeQuery(String.format(
							"SELECT AVG(CONVERT (INT, BS_CALLS.[Average Cll Duration]) ) FROM BS_CALLS"));
				) {
				
				while (resultSetC.next()) {
					oldArray[0] = array[0];
					array[0] = Integer.parseInt(resultSetC.getString(1));
					System.out.println(array[0] + " CALL");
					System.out.println(array[0] - oldArray[0] + " DELTA CALL");
				}
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			
			
			try (Statement statement = connection.createStatement();
					ResultSet resultSetS = statement.executeQuery(String.format(
							"SELECT AVG(CONVERT (INT, BS_SMS.[Average SMS sent]) ) FROM BS_SMS"));
				) {
				
				while (resultSetS.next()) {
					oldArray[1] = array[1];
					array[1] = Integer.parseInt(resultSetS.getString(1));
					System.out.println(array[1] + " SMS");
					System.out.println(array[1] - oldArray[1] + " DELTA SMS");
				}
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			
			try (Statement statement = connection.createStatement();
					ResultSet resultSetI = statement.executeQuery(
							String.format("SELECT AVG(	CONVERT (INT, BS_INTERNET.[Average DownloadUpload]) ) FROM BS_INTERNET"));
				) {
				
				while (resultSetI.next()) {
					oldArray[2] = array[2];
					array[2] = Integer.parseInt(resultSetI.getString(1));
					System.out.println(array[2] + " INTERNET");
					System.out.println(array[2] - oldArray[2] + " DELTA INTERNET");
				}
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("-------------------------------");
		
	}

}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.Dto;

public class Dao {

	/* Board 용 Method */
	public ArrayList<Dto> list() {
		ArrayList<Dto> dtos = new ArrayList<Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin_incheon",
					"incheon4");
			String query = "SELECT * FROM board WHERE upload='0' ORDER BY bno ASC";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String usernickname = resultSet.getString("usernickname");
				int bno = resultSet.getInt("bno");
				String btitle = resultSet.getString("btitle");
				String hash = resultSet.getString("hash");
				String bcontent = resultSet.getString("bcontent");
				int upload = resultSet.getInt("upload");
				int del = resultSet.getInt("del");
				String url = resultSet.getString("url");
				Timestamp uploaddate = resultSet.getTimestamp("uploaddate");
				Dto dto = new Dto(usernickname, bno, btitle, hash, bcontent, upload, del, url, uploaddate);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	public Dto tcount() {
		Dto dto = new Dto();
		return dto;
	}

	public void write(String usernickname, String btitle, String hash, String bcontent, String url) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin_incheon",
					"incheon4");
			String query = "INSERT INTO board(usernickname, bno, btitle, hash, bcontent, upload, agree, deagree, del, url, uploaddate) VALUES(?, board_seq.nextval, ?, ?, ?, 0, 0, 0, 0, ?, sysdate)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, usernickname);
			preparedStatement.setString(2, btitle);
			preparedStatement.setString(3, hash);
			preparedStatement.setString(4, bcontent);
			preparedStatement.setString(5, url);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Board 용 Method */
	public void join(String userid, String userpw, String username, String usernickname, String useremail,
			String userphone) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin_incheon",
					"incheon4");
			String query = "INSERT INTO signup(userid, userpw, username, usernickname, useremail, userphone, userdate) VALUES(?, ?, ?, ?, ?, ?, sysdate)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, userpw);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, usernickname);
			preparedStatement.setString(5, useremail);
			preparedStatement.setString(6, userphone);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Dto login(String loginid, String loginpw) {
		Dto dto = new Dto();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String usernickname = null;
		String userpw = null;
		int loginCheck = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin_incheon",
					"incheon4");
			String query = "SELECT * FROM signup WHERE userid=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, loginid);
			resultSet = preparedStatement.executeQuery();

			System.out.println("=== Dao loginCheck() Start... ===");
			System.out.println("resultSet : " + resultSet);
			while (resultSet.next()) {
				String userid = resultSet.getString("userid");
				userpw = resultSet.getString("userpw");
				String username = resultSet.getString("username");
				usernickname = resultSet.getString("usernickname");
				String useremail = resultSet.getString("useremail");
				String userphone = resultSet.getString("userphone");
				String userdate = resultSet.getString("userdate");

				if (userpw.equals(loginpw)) {
					System.out.println("loginid : " + loginid);
					System.out.println("userpw : " + userpw);
					System.out.println("loginpw : " + loginpw);
					System.out.println("Login Success");

					loginCheck = 1;
					System.out.println("loginCheck : " + loginCheck);
					dto = new Dto(userid, userpw, username, usernickname, useremail, userphone, userdate, loginCheck);
				} else if (!(userpw.equals(loginpw))) {
					dto = new Dto(userid, loginCheck);
					System.out.println("loginCheck : " + loginCheck);
					System.out.println("userpw: " + userpw);
					System.out.println("Password not match");
				} else {
					userid = resultSet.getString("userid");
					loginCheck = 2;
					dto = new Dto(userid, loginCheck);
					System.out.println("userid : " + userid);
					System.out.println("loginCheck : " + loginCheck);
				}
			}

			System.out.println("=== Dao loginCheck() End... ===");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public boolean idCheck(String strId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isCheck = false;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin_incheon",
					"incheon4");
			String query = "SELECT userid FROM signup WHERE userid=?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, strId);
			resultSet = preparedStatement.executeQuery();

			System.out.println("=== Dao idCheck() �쁺�뿭 === ");

			if (resultSet.next() == false) {
				isCheck = true;
			} else {
				isCheck = false;
				String userid = resultSet.getString("userid");
				System.out.println("userid : " + userid);
			}

			System.out.println("userid isCheck: " + isCheck);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isCheck;
	}

	public boolean nicknameCheck(String strNickname) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isCheck = false;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin_incheon",
					"incheon4");
			String query = "SELECT usernickname FROM signup WHERE usernickname=?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, strNickname);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next() == false) {
				isCheck = true;
			} else {
				isCheck = false;
				String usernickname = resultSet.getString("usernickname");
				System.out.println("usernickname : " + usernickname);
			}

			System.out.println("=== Dao nicknameCheck() ===");
			System.out.println("usernickname isCheck : " + isCheck);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isCheck;
	}

	public void informModify(String loginid, String loginpw, String loginname, String loginnickname, String loginemail,
			String loginphone) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin_incheon",
					"incheon4");
			String query = "UPDATE signup SET userpw=?, username=?, usernickname=?, useremail=?, userphone=? WHERE userid=?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, loginpw);
			preparedStatement.setString(2, loginname);
			preparedStatement.setString(3, loginnickname);
			preparedStatement.setString(4, loginemail);
			preparedStatement.setString(5, loginphone);
			preparedStatement.setString(6, loginid);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Dto modifyUpdate(String loginid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Dto dto = null;
		ResultSet resultSet = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin_incheon",
					"incheon4");
			String query = "SELECT * FROM signup WHERE userid=?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, loginid);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String userid = resultSet.getString("userid");
				String userpw = resultSet.getString("userpw");
				String username = resultSet.getString("username");
				String usernickname = resultSet.getString("usernickname");
				String useremail = resultSet.getString("useremail");
				String userphone = resultSet.getString("userphone");
				String userdate = resultSet.getString("userdate");
				int loginCheck = resultSet.getInt("loginCheck");

				loginCheck = 1;
				dto = new Dto(userid, userpw, username, usernickname, useremail, userphone, userdate, loginCheck);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dto;
	}

	public void logout(String loginid, int loginCheck) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin_incheon",
					"incheon4");
			String query = "UPDATE signup SET loginCheck=? WHERE userid=?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, loginCheck);
			preparedStatement.setString(2, loginid);

			preparedStatement.executeUpdate();

			System.out.println("=== Dao logout() Start.... ===");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void loginUpdate(String loginid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int loginCheck = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin_incheon",
					"incheon4");
			String query = "UPDATE signup SET loginCheck=? WHERE userid=?";
			preparedStatement = connection.prepareStatement(query);

			System.out.println("=== Dao loginCheckUpdate() ===");
			if (loginid != null) {
				loginCheck = 1;
			}

			System.out.println("loginCheck : " + loginCheck);
			preparedStatement.setInt(1, loginCheck);
			preparedStatement.setString(2, loginid);
			preparedStatement.executeUpdate();

			System.out.println("=== loginCheckUpdate End...==");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

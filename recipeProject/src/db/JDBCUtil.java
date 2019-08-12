package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCUtil {
	 public static Connection getConnection() {
		 Connection con = null;
	     //Java Naming and Directory Interface
	     //자바 플랫폼에서 자원에 명칭을 부여하는 기능을 제공하는 인터페이스
		 
		 try{
	      Context initCtx = new InitialContext();
	      //톰켓 자체의 컨텍스트
	      
	      Context envCtx = (Context) initCtx.lookup("java:comp/env");
	      //컨테이너에서 리소스 정의에 관한 컨텍스트
	      
	      DataSource ds = (DataSource)envCtx.lookup("jdbc/xe");
	      con = ds.getConnection();
	      con.setAutoCommit(false);
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	      return con;
	    }
	 
	 public static void close(Connection con){
		 try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public static void close(Statement stmt){
		 try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public static void close(ResultSet rs){
		 try {
			 rs.close();
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	 }
	 
	 public static void commit(Connection con){
		 try {
			 con.commit();
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	 }
	 
	 public static void rollback(Connection con){
		 try {
			 con.rollback();
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	 }
}

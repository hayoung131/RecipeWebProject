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
	     //�ڹ� �÷������� �ڿ��� ��Ī�� �ο��ϴ� ����� �����ϴ� �������̽�
		 
		 try{
	      Context initCtx = new InitialContext();
	      //���� ��ü�� ���ؽ�Ʈ
	      
	      Context envCtx = (Context) initCtx.lookup("java:comp/env");
	      //�����̳ʿ��� ���ҽ� ���ǿ� ���� ���ؽ�Ʈ
	      
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

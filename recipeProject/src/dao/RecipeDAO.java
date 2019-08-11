package dao;

import java.sql.*;

public class RecipeDAO {
	/*
	전역 변수를 사용하지 않고 객체를 하나만 생성 하도록 하며, 생성된 객체를 어디에서든지 참조할 수 있도록 하는 패턴
	애플리케이션이 시작될 때 어떤 클래스가 최초 한번만 메모리를 할당하고(Static) 그 메모리에 인스턴스를 만들어 사용하는 디자인패턴.
	생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나고 최초 생성 이후에 호출된 생성자는 최초에 생성한 객체를 반환한다. 
	(자바에선 생성자를 private로 선언해서 생성 불가하게 하고 getInstance()로 받아쓰기도 함)
	=> 싱글톤 패턴은 단 하나의 인스턴스를 생성해 사용하는 디자인 패턴이다.
	*/
	
	private Connection con;
	/*외부클래스에서 BoardDAO 변수에 직접접근하지못하게 private으로 지정해준것
	*				클래스명	변수명	 = new 메소드()
	* 밖에서는 BoardDAO() 생성자로 객체 생성 못하게 막아뒀으니, 요 안에서 객체 생성하고 그걸 getInstance() 메소드를
	* 통해서만 객체를 가져갈 수 있도록해둔것.
	*/
	private RecipeDAO() {
 		//외부 클래스에서 생성자를 이용해 객체를 생성할 수 없도록 생성자의 접근제한을 private으로 설정하고
 	}
	private static RecipeDAO instance = new RecipeDAO();
	public static RecipeDAO getInstance() {
        return instance;
        /*싱글톤패턴 : 특정클래스 기능사용시 매번 클래스객체 매번 다 생성하는게아니라 
         * 처음만 생성하고 힙에있는걸 공유해서 다음엔 생성안하고도 가능하게....
         * getInstance()메소드를 통해서만..
         *외부에서 하나만 생성하도록 함... */
    }
	
	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;
	}
}

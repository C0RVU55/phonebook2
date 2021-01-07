package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PhoneVo;

public class PhoneDao {

	// 필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb"; // webdb아니고 phonedb
	private String pw = "phonedb";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private int count = 0;

	// 생성자
	// 메소드 겟셋
	// 메소드 일반

	// 화면 출력
	public void view() {
		System.out.println("");
		System.out.println("1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료");
		System.out.println("------------------------------------------");
		System.out.print(">메뉴번호: ");
	}

	// DB접속
	public void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	//////////////////////////////////////////////////

	// **********리스트**********
	public List<PhoneVo> getList() {
		List<PhoneVo> pList = new ArrayList<PhoneVo>();

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " SELECT  person_id, ";
			query += "         name, ";
			query += "         hp, ";
			query += "         company ";
			query += " FROM person ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PhoneVo vo = new PhoneVo(personId, name, hp, company);
				pList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return pList;
	}

	// **********등록**********
	public int phoneInsert(PhoneVo pVo) {

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, pVo.getName());
			pstmt.setString(2, pVo.getHp());
			pstmt.setString(3, pVo.getCompany());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + count + "건 등록되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;
	}

	// **********수정**********
	public int phoneUpdate(PhoneVo pVo) {

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += "     hp = ?, ";
			query += "     company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, pVo.getName());
			pstmt.setString(2, pVo.getHp());
			pstmt.setString(3, pVo.getCompany());
			pstmt.setInt(4, pVo.getPersonId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + count + "건 수정되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;
	}

	// **********삭제**********
	public int phoneDelete(int id) {

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from person ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, id);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + count + "건 삭제되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;
	}

	// **********검색**********
	public List<PhoneVo> getList(String str) { // 메소드 오버로딩 시키기
		List<PhoneVo> pList = new ArrayList<PhoneVo>();

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " SELECT  person_id, ";
			query += "         name, ";
			query += "         hp, ";
			query += "         company ";
			query += " FROM person ";
			query += " where person_id like ? ";
			query += " or name like ? ";
			query += " or hp like ? ";
			query += " or company like ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, "%" + str + "%");
			pstmt.setString(2, "%" + str + "%");
			pstmt.setString(3, "%" + str + "%");
			pstmt.setString(4, "%" + str + "%");

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PhoneVo vo = new PhoneVo(personId, name, hp, company);
				pList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return pList;

	}
	
	// *********사람 1명 정보 가져오기*********
	public PhoneVo getPerson(int personId) {
		PhoneVo pVo = null; // for문 안에 있어서 return할 게 없으니까 초기값 정해줌.
		
		getConnection();
		
		try {
			String query = "";
			query += " SELECT  person_id, ";
			query += "         name, ";
			query += "         hp, ";
			query += "         company ";
			query += " FROM person ";
			query += " where person_id = ? ";
			
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, personId);
			
			rs = pstmt.executeQuery();
			
			// 결과처리
			while(rs.next()) {
				int personID = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				pVo = new PhoneVo(personID, name, hp, company);
			}
		
			
		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return pVo;


		
	}

}

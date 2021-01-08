package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PhoneVo;

@WebServlet("/pbc") //길어서 이름 바꿈 (파일명 대신 쳐서 들어갈 주소)
public class PhoneController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// controller 테스트
		System.out.println("controller");
		
		// 파라미터 action값을 읽어서 작동함
		// action = 일 --> 일 (action 파라미터를 구분용으로 쓰는 거)
		String action = request.getParameter("action");
		System.out.println(action);
		
		
		if("list".equals(action)) { // 리스트 출력 
			// action 없으면 null point 에러 나니까 순서 바꿈. switch문도 가능. 
		
			// 리스트 출력 처리
			PhoneDao pDao = new PhoneDao();
			List<PhoneVo> pList = pDao.getList();
			
			// 여기에 html 쓰려면 복잡함 --> forward할 입력화면은 jsp로 만듦
			
			// 데이터 전달 (attribute는 파라미터 영역과 구분됨. 파라미터는 주소창에서만 입력 가능하고 attribute는 controller에서만 입력 가능.)
			request.setAttribute("perList", pList); // ("꺼낼 때 이름", 받는 데이터명)
			
			// jsp에 forward --> controller로 받은 파라미터와 attribute를 list.jsp로 보냄 --> list.jsp에서 받은 데이터를 꺼내서 섞음
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/list.jsp"); // jsp파일 위치
			rd.forward(request, response);
		
			
		} else if("wform".equals(action)) { // action = wform --> 등록폼(jsp)으로 forword 
			System.out.println("등록 폼 처리"); //이걸로 여기까지 도달하는지 테스트하고 코드 짜기
			// ***폼이 필요한 실행문은 폼을 따로 만들어서 forward하고 그 폼에서 form action = ""과 action <input value=>으로 해당 실행문과 연결해야됨***
			
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/writeForm.jsp");
			rd.forward(request, response);
			
			
		} else if ("insert".equals(action)) { // forward 후에 받은 데이터 등록 및 DB저장
			System.out.println("전화번호 저장");
			
			// 파라미터 3개 값을 받음
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			// PhoneVo로 묶은 후
			PhoneVo pVo = new PhoneVo(name, hp, company);
			
			// new dao insert(phoneVo) 해서 DB에 저장
			PhoneDao pDao = new PhoneDao();
			pDao.phoneInsert(pVo);
			
			//리스트 재요청
			response.sendRedirect("/phonebook2/pbc?action=list");
			
			
		} else if ("delete".equals(action)) { // id 파라미터 받아서 삭제
			System.out.println("전화번호 삭제");
			
			// id 파라미터 받음
			int id = Integer.parseInt(request.getParameter("id"));
			
			// new Dao 해서 phoneDelete(); 씀
			PhoneDao pDao = new PhoneDao();
			pDao.phoneDelete(id);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
			
			
		} else if ("uform".equals(action)) { // 수정 폼 forward
			System.out.println("수정 폼 처리"); 
			
			// 파라미터 받아서 dao.getPerson()을 여기서 씀. 수정폼에서 하지말고 여기서 처리한 PhoneVo를 attribute시킴.
			int id = Integer.parseInt(request.getParameter("id"));
			PhoneDao pDao = new PhoneDao();
			PhoneVo perVo = pDao.getPerson(id);
			
			// 받은 파라미터로 원하는 사람 데이터만 뽑은 PhoneVo를 attribute시킴.
			request.setAttribute("pVo", perVo);
			
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/updateForm.jsp");
			rd.forward(request, response);
			
			
		} else if ("update".equals(action)) { // 변경된 데이터 받아서 DB에 반영
			System.out.println("전화번호 수정");
			
			//파라미터 4개값 받음
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int id = Integer.parseInt(request.getParameter("id"));
			
			//PhoneVo로 묶은 후
			PhoneVo pVo = new PhoneVo(id, name, hp, company);
			
			//new Dao update(PhoneVo)해서 DB저장
			PhoneDao pDao = new PhoneDao();
			pDao.phoneUpdate(pVo);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		
	}

}

### JDBCServlet
  - JDBC, JSP, Servlet 을 이용한 회원정보 조회, 로그인 기능 구현 

### 수정이력
  - 2016.02.13
    * JDBC를 이용하여 MySQL의 members 테이블의 유저정보를 select하여 화면에 출력하는 servlet 개발(/member/list)
    * response에 character encoding 설정 적용
    * GET 방식의 request 처리
  - 2016.02.14
    * /member/list 에 신규 회원 등록 링크 추가(GET 방식으로 요청)
    * 신규 회원 등록 폼 및 등록 시 POST 방식으로 MySQL DB에 회원정보 insert 하는 기능 추가(/member/add)
  - 2016.02.15 ~ 2016.02.25
    * 회원 정보 처리를 위한 VO 파일(Member.java) 적용
    * 파라미터 값의 인코딩 설정(UTF-8)을 위한 Filter 기능 적용(CharacterEncodingFilter.java)
    * 회원 업데이트 페이지 및 기능 추가(MemberUpdateServlet.java)
    * 회원 삭제 버튼을 리스트페이지에 추가하고 삭제 서블릿 추가(MemberDeleteServlet.java)
    * 웹어플리케이션 로딩 시 자동 수행되는 서블릿(AppInitServlet.java) 생성하여, JDBC Connection 객체 연결설정하고 ServletContext에 주입하여 다른 서블릿에서 사용하도록 변경
    * 멤버 리스트 뷰로직을 JSP 페이지로 이관(MemberList.jsp)
    * 로그인 페이지(LogInForm.jsp)와 로그인 실패 페이지(LogInFail.jsp) 추가
    * 로그인 처리 서블릿 추가, 로그인 시 HttpSession에 로그인 정보 입력로직 적용(LoginServlet.java)
    * 로그아웃 기능 추가, 로그아웃 시 session 에 설정된 데이터 invalidate 수행(LogoutServlet.java)
    * MemberList.jsp 페이지에 header, tail 페이지를 include 하고 header 페이지에는 로그인 정보 출력과 로그인/로그아웃 버튼 추가
  - 2016.02.28
    * 멤버 신규 입력, 기존 멤버 수정 기능의 View를 별도의 JSP (MemberForm.jsp, MemberFormUpdate.jsp) 로 이관
    * MemberAddServlet, MemberUpdateServlet 에서 뷰 생성 부분 삭제, Exception 처리시 Error.jsp 페이지로 포워딩 추가
    * Header.jsp, MemberList.jsp 페이지에 JSP 액션 태그 (<jsp:useBean>) 적용
  - 2016.03.01
    * MemberUpdateForm.jsp : JSP 액션 태그 삭제 후 EL 표현식 적용
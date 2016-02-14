# JDBCServlet
JDBC, Servlet 을 이용한 회원정보 조회, 로그인 기능 구현 

### 데이터베이스 연결
DriverManager의 getConnection() 이용 
'''conn = DriverManager.getConnection("jdbc:mysql://localhost/DB명", "ID", "PWD");'''
1. 첫번째 인자값 : DB서버의 접속 정보(JDBC URL)
  - DriverManager는 등록된 JDBC 드라이버(DriverManager.registerDriver() 메서드로 등록한 드라이버) 중에서 이 URL 을 승인하는 드라이버를 찾음
  - 이후에 찾은 java.sql.Driver 구현체를 이용하여 DB에 연결
  - JDBC URL의 앞부분 jdbc:mysql 은 JDBC 드라이버의 이름
  - JDBC URL 은 DB마다 조금씩 다르므로 각 드라이버에서 제공하는 개발 문서를 참조해야 함

package ex09_up_down;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class Up_down
 */
@WebServlet("/upload")
public class Up_down extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Up_down() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  /*
	   * 파일 첨부 서넹 작업
	   * 1. http://servlets.com/ 접속
	   * 2. COS File Upload Libarary - cos-22.05.zip 다운로드
	   * 3. zip 압축 해제 후 lib/cos.jar 라이브러리 -> 프로젝트 webapp/WEB-INF/lib.cor.jar                
	   */
	  /*
	   * cos.jar
	   * 1. 업로드 전용 라이브러리이다.
	   * 2. 파일 첨부 폼 양식은 enctype="multipart/form-data"로 설정하는데 이렇게되면 HttpServletRequest를 사용할 수 없다.
	   * 3. HttpServletRequest 대신 사용할 MultipartRequest 클래스를 지원하는 라이브러리이다.
	   */
	  // 첨부 파일의 경로!
	  String path = "C:/storage";
	  
	  // 파일 생성
	  File dir = new File(path);
	  if(!dir.exists()) {
	    dir.mkdirs();
	  }
	  // MultipartRequest 객체 생성 (실제 업로드가 진행되는 코드)                          // 파일명 중복일때 파일이름 바꾸기
	  MultipartRequest mr = new MultipartRequest(request, path, 1024 * 1024 * 50, "UTF-8", new DefaultFileRenamePolicy());
	  
	  // 요청
	  String uploader = mr.getParameter("uploader");
	  
	  // 첨부 파일
	  String origin = mr.getOriginalFileName("attach");    // 올릴 첨부 파일명
	  String filesysName = mr.getFilesystemName("attach"); // 저장 첨부 파일명
	  
	  File attach = mr.getFile("attach");
	  String name = attach.getName();      // 저장된 첨부 파일명
	  String parent = attach.getParent();  // 첨부 파일 저장 디렉터리
//	  String path = attach.getPath();    // 디렉터리 + 파일명
	  String lastModified = new SimpleDateFormat("yyyy-MM-dd").format(attach.lastModified());
	  String size = new DecimalFormat("#,##0").format(attach.length() / 1024 + (attach.length() % 1024 == 0 ? 0 : 1)); // 1024로 나눠서 kb로 표기 1024넘으면 2로 표기
	  
	  // 응답
	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.println("<ul>");
	  out.println("<li>업로더: " + uploader + "</li>");
	  out.println("<li>올릴 첨부파일명: " + origin + "</li>");
	  out.println("<li>저장된 첨부파일명: " + name + "</li>");
	  out.println("<li>저장된 첨부파일명: " + filesysName + "</li>");
	  out.println("<li>저장 디렉터리: " + parent + "</li>");
	  out.println("<li>최종수정일: " + lastModified + "</li>");
	  out.println("<li>파일크기: " + size + "KB </li>");
	  out.println("</ul>");
	  out.println("<hr>");
	  // 이 곳에 첨부된 모든 파일의 목록을 보여주세요!
	  File[] attaches = dir.listFiles();
	  for(int i = 0; i < attaches.length; i++) {
	    out.println("<div><a href=\"/servlet/download?path="+ URLEncoder.encode(attaches[i].getPath(), "UTF-8") +"\">" + attaches[i].getName() + "</a></div>");
	  }
 	  
	  
	  out.flush();
	  out.close();
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

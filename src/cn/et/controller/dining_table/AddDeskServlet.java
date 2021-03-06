package cn.et.controller.dining_table;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.desk.MyDesk;


/**
 * Servlet implementation class AddDeskServlet
 */
public class AddDeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyDesk md = new MyDesk();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集

		request.setCharacterEncoding("UTF-8");
		
		//获取添加的列名
		String dname=request.getParameter("dname");
		try {
			md.saveDesk(dname);
			//请求转发
			request.getRequestDispatcher("/DeskServlet").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

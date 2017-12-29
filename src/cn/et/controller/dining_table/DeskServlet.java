package cn.et.controller.dining_table;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.PageTools;
import cn.et.model.desk.MyDesk;

/**
 * Servlet implementation class DeskServlet
 */
public class DeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyDesk myTest = new MyDesk();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//设置字符集
			request.setCharacterEncoding("UTF-8");
			//获取列名
			String name = request.getParameter("dname");
			String curPage = request.getParameter("curPage");
			Integer curPageInt = 1;
			//判断分页不为null
			if(curPage != null){
				curPageInt = Integer.parseInt(curPage);
			}
			PageTools tableList = myTest.getTableListPager(name,curPageInt);
			//设置request作用域中
			request.setAttribute("tableList", tableList);
			//请求转发
			request.getRequestDispatcher("/detail/boardList.jsp").forward(request, response);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

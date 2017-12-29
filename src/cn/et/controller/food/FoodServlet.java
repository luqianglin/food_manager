package cn.et.controller.food;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.PageTools;
import cn.et.model.food.MyFood;

/**
 * Servlet implementation class FoodServlet
 */
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyFood myTest = new MyFood();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//设置字符集
			request.setCharacterEncoding("UTF-8");
			//获取参数名
			String name = request.getParameter("dname");
			//获取当前页
			String curPage = request.getParameter("curPage");
			Integer curPageInt = 1;
			if(curPage != null){
				curPageInt = Integer.parseInt(curPage);
			}
			PageTools tableList = myTest.getTableListPager(name,curPageInt);
			//设置request作用域中
			request.setAttribute("tableList", tableList);
			//请求转发
			request.getRequestDispatcher("/detail/foodList.jsp").forward(request, response);;
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

package cn.et.controller.food_type;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.food_type.MyFoodType;

/**
 * Servlet implementation class UpdateFoodTypeServlet
 */
public class UpdateFoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFoodTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyFoodType mft = new MyFoodType();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		
		//获取参数
		String typeId=request.getParameter("typeid");
		String typeName =request.getParameter("typename");
		try {
			mft.updateFoodType(typeId, typeName);
			//请求转发
			request.getRequestDispatcher("/FoodTypeServlet").forward(request, response);
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

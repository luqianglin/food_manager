package cn.et.controller.food_type;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.PageTools;
import cn.et.model.food_type.MyFoodType;

/**
 * Servlet implementation class FoodTypeServlet
 */
public class FoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyFoodType myTest = new MyFoodType();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//�����ַ���
			request.setCharacterEncoding("UTF-8");
			
			//��ȡ��ѯ������
			String name = request.getParameter("dname");
			//��ȡ��ǰҳ
			String curPage = request.getParameter("curPage");
			Integer curPageInt = 1;
			if(curPage != null){
				curPageInt = Integer.parseInt(curPage);
			}
			PageTools tableList = myTest.getTableListPager(name,curPageInt);
			//����request��������
			request.setAttribute("tableList", tableList);
			//����ת��
			request.getRequestDispatcher("/detail/cuisineList.jsp").forward(request, response);;
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

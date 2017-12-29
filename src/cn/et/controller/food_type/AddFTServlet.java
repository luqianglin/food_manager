package cn.et.controller.food_type;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.food_type.MyFoodType;

/**
 * Servlet implementation class AddFTServlet
 */
public class AddFTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFTServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyFoodType mft = new MyFoodType();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ַ���
		request.setCharacterEncoding("UTF-8");
		
		//��ȡ����
		String dname=request.getParameter("dname");
		try {
			mft.saveFoodType(dname);
			//����ת��
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

package cn.et.controller.food;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.et.model.food.MyFood;

/**
 * Servlet implementation class AddFoodSservlet
 */
public class AddFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    String absPath="F:/myfile/";
    MyFood mf = new MyFood();
	/**
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		//�����ַ���
		request.setCharacterEncoding("UTF-8");
		
		ServletFileUpload.isMultipartContent(request);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//����һ���µ��ļ��ϴ��������
		ServletFileUpload upload = new ServletFileUpload(factory);//�����ϴ����ļ���С�����ֵ upload.setSizeMax( maxFileSize ); 
		upload.setHeaderEncoding("UTF-8");
		//��������,��ȡ�ļ���
		List fileItems;
		String foodName=null;
		String typeId=null;
		String price=null;
		String synopsis=null;
		ServletContext sc = this.getServletContext();
		//���·����ȡ����·��c:/a/b
		//String absPath = sc.getRealPath("/myfile");
		String spath = "/";
		try {
			fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();
			while(i.hasNext()){
				//�����ϴ����ļ���
				FileItem fi = (FileItem)i.next();
				if(fi.isFormField()){
					if(fi.getFieldName().equals("foodName")){
						foodName=fi.getString("UTF-8");
					}
					if(fi.getFieldName().equals("price")){
						price=fi.getString();
					}
					if(fi.getFieldName().equals("typeId")){
						typeId=fi.getString();
					}
					if(fi.getFieldName().equals("synopsis")){
						synopsis=fi.getString("UTF-8");
					}
				}else{
					InputStream is = fi.getInputStream();
					String name = fi.getName();
					String destPath = absPath+"/"+name;
					spath = spath+name;
					FileOutputStream fis = new FileOutputStream(destPath);
					byte[] b = new byte[1024];
					int n=-1;
					while((n=is.read(b))!=-1){
						fis.write(b,0,n);
					}
					fis.close();
					is.close();
				}
				
			}
			mf.saveFood(typeId, foodName, price, spath ,synopsis);
			request.getRequestDispatcher("/FoodServlet").forward(request, response);
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

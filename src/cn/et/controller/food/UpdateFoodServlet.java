package cn.et.controller.food;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.et.model.food.MyFood;


/**
 * Servlet implementation class UpdateFoodServlet
 */
public class UpdateFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyFood mf = new MyFood();
    String absPath="F:/myfile/";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
				
		ServletFileUpload.isMultipartContent(request);
		DiskFileItemFactory factory = new DiskFileItemFactory();
				
		//创建一个新的文件上传处理程序
		ServletFileUpload upload = new ServletFileUpload(factory);//允许上传的文件大小的最大值 upload.setSizeMax( maxFileSize ); 
		upload.setHeaderEncoding("UTF-8");
		//解析请求,获取文件项
		List fileItems;
		String foodName=null;
		String typeId=null;
		String price=null;
		String foodId=null;
		String synopsis=null;
		String image=null;
		ServletContext sc = this.getServletContext();
		//相对路径获取绝对路径c:/a/b
		//String absPath = sc.getRealPath("/myfile");
		String spath = "/";
		try {
			fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();
			while(i.hasNext()){
			//处理上传的文件项
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
					if(fi.getFieldName().equals("foodId")){
						foodId=fi.getString();
					}
					if(fi.getFieldName().equals("synopsis")){
						synopsis=fi.getString("UTF-8");
					}
					if(fi.getFieldName().equals("imagePath")){
						image=fi.getString("UTF-8");
					}
				}else{
					InputStream is = fi.getInputStream();
					String name = fi.getName();
					if(name==null || name.equals("")){
						spath = image;
					}
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
					mf.updateFood(foodId, typeId, foodName, price,synopsis,spath);
					request.getRequestDispatcher("/FoodServlet").forward(request, response);
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

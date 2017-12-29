package cn.et.model.food;

import java.util.List;
import java.util.Map;

import cn.et.model.DbUntils;
import cn.et.model.PageTools;
/**
 * ��Ʒ��
 * @author Administrator
 *
 */
public class MyFood {
	/**
	 * ��ȡ������
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Integer getTableListCount(String name) throws Exception{
		if(name==null){
			name="";
		}
		String sql="select count(rowid) as cr from food where FOODNAME like '%"+name+"%'";
		List<Map> result=DbUntils.query(sql);
		return Integer.parseInt(result.get(0).get("CR").toString());
	}
	/**
	 * ��װ��get����
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public PageTools getTableListPager(String name,Integer curPage) throws Exception{
		if(name==null){
			name="";
		}
		Integer totalCount=getTableListCount(name);
		PageTools pt=new PageTools(curPage, totalCount, null);
		StringBuffer sb = new StringBuffer();
		String sql="select * from (select t.*,ft.typename,rownum rn from food t inner join foodtype ft on t.typeid=ft.typeid where t.FOODNAME like '%"+name+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		List<Map> result = DbUntils.query(sql);
		pt.setData(result);
		
		return pt;
	
	}
	/**
	 * ��Ӳ�Ʒ
	 * @param deskName
	 * @throws Exception
	 */
	public void saveFood(String typeId,String foodName,String price,String imagePath,String synopsis) throws Exception{

		String sql="insert into Food values((select nvl(max(foodid),0)+1 from food),'"+typeId+"','"+foodName+"','"+price+"','"+imagePath+"','"+synopsis+"')";
		DbUntils.execute(sql);
		
	}
	/**
	 * �h����Ʒ
	 * @param deskId
	 * @throws Exception
	 */
	public void deleteFood(String foodId) throws Exception{
		String sql="delete from FOOD where foodid="+foodId;
		DbUntils.execute(sql);
		
	}
	/**
	 * �޸Ĳ�Ʒ
	 * @param typeId
	 * @param typeName
	 * @throws Exception
	 */
	public void updateFood(String foodId,String typeId,String foodName,String price,String synopsis,String imagePath) throws Exception{
	
		String sql="update FOOD set foodname='"+foodName+"',typeid='"+typeId+"',price='"+price+"',synopsis='"+synopsis+"',imagePath='"+imagePath+"' where foodId="+foodId;
		int temp=DbUntils.execute(sql);
		//System.out.println(temp+"---"+sql);
	}
	
}

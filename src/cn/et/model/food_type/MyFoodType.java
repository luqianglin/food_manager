package cn.et.model.food_type;

import java.util.List;
import java.util.Map;

import cn.et.model.DbUntils;
import cn.et.model.PageTools;
/**
 * ��ϵ��
 * @author Administrator
 *
 */
public class MyFoodType {
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
		String sql="select count(rowid) as cr from foodtype where TYPENAME like '%"+name+"%'";
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
		List<Map> result=DbUntils.query("select * from (select t.*,rownum rn from foodtype t where t.TYPENAME like '%"+name+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex());
		pt.setData(result);
		
		return pt;
	}
	/**
	 * ��Ӳ�ϵ
	 * @param deskName
	 * @throws Exception
	 */
	public void saveFoodType(String FTName) throws Exception{
		String sql="insert into FOODTYPE values((select nvl(max(TYPEID),0)+1 from foodtype),'"+FTName+"')";
		DbUntils.execute(sql);
		
	}
	/**
	 * ���Ҳ�ϵ��������
	 * @return
	 * @throws Exception
	 */
	public List<Map> getAllFoodType() throws Exception{
		String sql="select * from FOODTYPE";
		return DbUntils.query(sql);
	}
	/**
	 * �h����ϵ
	 * @param deskId
	 * @throws Exception
	 */
	public void deleteFoodType(String typeId) throws Exception{
		
		String sql="delete from FOODTYPE where typeid="+typeId;
		DbUntils.execute(sql);
		
	}
	/**
	 * �޸Ĳ�ϵ
	 * @param typeId
	 * @param uName
	 * @throws Exception
	 */
	public void updateFoodType(String typeId,String typeName) throws Exception{
		String sql="update FOODTYPE set typename='"+typeName+"' where typeid="+typeId;
		DbUntils.execute(sql);
	}
}

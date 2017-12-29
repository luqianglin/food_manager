package cn.et.model.food_type;

import java.util.List;
import java.util.Map;

import cn.et.model.DbUntils;
import cn.et.model.PageTools;
/**
 * 菜系类
 * @author Administrator
 *
 */
public class MyFoodType {
	/**
	 * 获取总条数
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
	 * 封装了get方法
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
	 * 添加菜系
	 * @param deskName
	 * @throws Exception
	 */
	public void saveFoodType(String FTName) throws Exception{
		String sql="insert into FOODTYPE values((select nvl(max(TYPEID),0)+1 from foodtype),'"+FTName+"')";
		DbUntils.execute(sql);
		
	}
	/**
	 * 查找菜系所有数据
	 * @return
	 * @throws Exception
	 */
	public List<Map> getAllFoodType() throws Exception{
		String sql="select * from FOODTYPE";
		return DbUntils.query(sql);
	}
	/**
	 * 刪除菜系
	 * @param deskId
	 * @throws Exception
	 */
	public void deleteFoodType(String typeId) throws Exception{
		
		String sql="delete from FOODTYPE where typeid="+typeId;
		DbUntils.execute(sql);
		
	}
	/**
	 * 修改菜系
	 * @param typeId
	 * @param uName
	 * @throws Exception
	 */
	public void updateFoodType(String typeId,String typeName) throws Exception{
		String sql="update FOODTYPE set typename='"+typeName+"' where typeid="+typeId;
		DbUntils.execute(sql);
	}
}

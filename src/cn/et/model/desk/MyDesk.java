package cn.et.model.desk;

import java.util.List;
import java.util.Map;

import cn.et.model.DbUntils;
import cn.et.model.PageTools;
/**
 * ������
 * @author Administrator
 *
 */
public class MyDesk {
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
		String sql="select count(rowid) as cr from DESK where DNAME like '%"+name+"%'";
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
		List<Map> result=DbUntils.query("select * from (select t.*,rownum rn from DESK t where t.DNAME like '%"+name+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex());
		pt.setData(result);
		
		return pt;
	}
	/**
	 * ��Ӳ���
	 * @param deskName
	 * @throws Exception
	 */
	public void saveDesk(String deskName) throws Exception{
		String sql="insert into desk values((select nvl(max(deskid),0)+1 from desk),'"+deskName+"',0,'')";
		//System.out.println(sql);
		DbUntils.execute(sql); 
		
	}
	/**
	 * �h������
	 * @param deskId
	 * @throws Exception
	 */
	public void deleteDesk(String deskId) throws Exception{
		String sql="delete from desk where deskid="+deskId;
		DbUntils.execute(sql);
		
	}
	/**
	 *����
	 * @param deskId
	 * @throws Exception
	 */
	public void updateDesk(String deskId) throws Exception{
		String sql="update desk set dstate=0 where deskid="+deskId;
		DbUntils.execute(sql);
	}
	
}

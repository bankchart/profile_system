public class Career extends GeneralModel {

	private int careerID;
	private String careerName;

	public Career(){
		this.tableName = "career_tb";
	}	
	public void setCareerID(int id){
		this.careerID = id;
	}
	public int getCareeID(){
		return this.careerID;
	}
	public void setCareerName(String name){
		this.careerName = name;
	}
	public String getCareerName(){
		return this.careerName;
	}
	public String getTableName(){
		return this.tableName;
	}
	public String[] listAttribute(){
		return new String[]{
			"career_id", "career_name" 
		};
	}
	public boolean sqlExecute(String action, String pointerName, String pointerValue, String update, String field){
		DBConnection conn = new DBConnection();
		String sql = null;
		boolean result = false;
		if("query".equals(action)){
			
			sql = "SELECT * FROM " + this.tableName + 
				" WHERE " + pointerName + "=" + pointerValue;
			result = conn.executeData(sql);

		}else if("insert".equals(action)){

			sql = "INSERT INTO " + this.tableName + " VALUES(" + 
				null + ", " + 
				"'" + this.careerName + "')";
			result = conn.executeData(sql);

		}else if("update".equals(action)){

			sql = "UPDATE " + this.tableName + 
			      	" SET " + field + "=" + update + 
			      	" WHERE " + pointerName + "=" + pointerValue;	
			result = conn.executeData(sql);

		}else if("delete".equals(action)){

			sql = "DELETE FROM " + this.tableName + 
				"WHERE " + pointerName + "=" + pointerValue;
			result = conn.executeData(sql);

		}else{
			result = false;
		}
		conn.closeDB();
		return result;
	}
}

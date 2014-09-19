public class GeneralModel {
	protected String tableName;
	/*
	public boolean save(){
		String sql = null;
		if(null == this.pkPointer){ // insert
			String[] tmp = listValues();
			sql = "INSERT INTO " + tableName + " VALUES ("; 
				for(int i=1;i<tmp.length - 1;i++){
								
				}	
			sql += tmp[tmp.length - 1];
			sql += ")";
		}else{ // update
			sql = "";
		}
		return false;
	}
	public boolean delete(){
		if(null == pkPointer){
			log.info("empty pkPointer in " + tableName + " table.");	
			return false;
		}else{
			try{
				String[] tmp = listAttributes();
				DBConnection conn = new DBConnection();
				String sql = "DELETE FROM " + tableName + " WHERE " + tmp[0] + "=" + pkPointer;
				if(conn.executeData(sql)){
					log.info("delete comleted.");
					return true;
				}else{
					log.info("delete failure.");
					return false;				
				}
			}catch(Exception ex){
				log.info("Exception [" + tableName + " model -> delete method]");
				return false;
			}
		}	
	}
	abstract String[] listAttributes();
	abstract String[] listValues();
	*/
}

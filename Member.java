import org.apache.log4j.Logger;
import java.sql.ResultSet;
public class Member extends GeneralModel{
	private Logger log = Logger.getLogger(Member.class.getName());	

	private Integer userID;
	private String userName;
	private String password;
	private String fullName;
	private String birthDate;
	private float height;
	private float weight;
	private String bloodType;
	private String hobby;
	private String phone;
	private String email;
	private String detailEducation;
	private String detailFavorite;
	private String detailLittleSkill;
	private String picturePath;
	private int admin;
	private int careerID;

	public Member(){
		this.tableName = "member_tb";
		this.userID = null;
	}
	public int getUseID(){
		return this.userID;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	public void setFullName(String fullName){
		this.fullName = fullName;
	}
	public String getFullName(){
		return this.fullName;
	}
	public void setBirthDate(String birthDate){
		this.birthDate = birthDate;
	}
	public String getBirthDate(){
		return this.birthDate;
	}
	public void setHeight(float height){
		this.height = height;
	}
	public float getHeight(){
		return height;
	}
	public void setWeight(float weight){
		this.weight = weight;
	}
	public float getWeight(){
		return this.weight;
	}
	public void setBloodType(String bloodType){
		this.bloodType = bloodType;
	}
	public String getBloodType(){
		return bloodType;
	}
	public void setHobby(String hobby){
		this.hobby = hobby;		
	}
	public String getHobby(){
		return this.hobby;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setDetailEducation(String detailEducation){
		this.detailEducation = detailEducation;
	}
	public String getDetailEducation(){
		return this.detailEducation;
	}
	public void setDetailFavorite(String detailFavorite){
		this.detailFavorite = detailFavorite;
	}
	public String getDetailFavorite(){
		return this.detailFavorite;
	}
	public void setDetailLittleSkill(String detailLittleSkill){
		this.detailLittleSkill = detailLittleSkill;
	}
	public String getDetailLittleSkill(){
		return this.detailLittleSkill;
	}
	public void setPicturePath(String picturePath){
		this.picturePath = picturePath;
	}
	public String getPicturePath(){
		return this.picturePath;
	}
	public void setAdmin(int admin){
		this.admin = admin;
	}
	public int getAdmin(){
		return this.admin;
	}
	public void setCareerID(int careerID){
		this.careerID = careerID;
	}
	public boolean sqlExecute(String action, String pointerName, String pointerValue, String update, String field){
		DBConnection conn = new DBConnection();
		String sql = null;
		boolean result = false;
		if("query".equals(action)){
			ResultSet rs = null;
                        sql = "SELECT * FROM " + this.tableName + 
                                " WHERE " + pointerName + "=" + pointerValue;
                        rs = conn.manageData(sql);
			int countRows = 0;
			try{
				while(rs.next())
					countRows++;	
				if(countRows > 0){
					rs.beforeFirst();
					while(rs.next()){
						this.userID = Integer.parseInt(rs.getString("userid"));
	                                	this.userName = rs.getString("username");
	                                	this.password = rs.getString("password");
        	                       		this.fullName = rs.getString("fullname");
                	               		this.birthDate = rs.getString("birthdate");
                        	                this.height = Float.parseFloat(rs.getString("height")) ;
                               	       		this.weight = Float.parseFloat(rs.getString("weight"));
                             			this.bloodType = rs.getString("blood_type");
                                		this.hobby = rs.getString("hobby");
                                		this.phone = rs.getString("phone");
                               			this.email = rs.getString("email");
                               			this.detailEducation = rs.getString("detail_edu");
                                		this.detailFavorite = rs.getString("detail_fav");
                                		this.detailLittleSkill = rs.getString("detail_lsk");
                                		this.picturePath = rs.getString("picture_path"); 
                                      		this.admin = Integer.parseInt(rs.getString("admin"));
                                      		this.careerID = Integer.parseInt(rs.getString("career_id"));
					}
				}
			}catch(Exception ex){
				log.info("Exception in sqlExecute action: query ===>>> " + ex.getMessage());
			}	
		}else if("insert".equals(action)){
			String usernameTmp = "'" + this.userName.trim() + "'";
			String passwordTmp = "'" + this.password.trim() + "'";
			String fullnameTmp = "'" + this.fullName.trim() + "'";
			String birthDateTmp = this.birthDate;
			String heightTmp = "" + this.height;
			String weightTmp = "" + this.weight;
			String bloodTypeTmp = this.bloodType == null ? "null" : "'" + this.bloodType + "'";
			String hobbyTmp = this.hobby == null ? "null" : "'" + this.hobby.trim() + "'";
			String phoneTmp = this.phone == null ? "null" : "'" + this.phone.trim() + "'";
			String emailTmp = this.email == null ? "null" : "'" + this.email.trim() + "'";
			String detailEduTmp = this.detailEducation;
			String detailFavTmp = this.detailFavorite;
			String detailLskTmp = this.detailLittleSkill;
			String picturePathTmp = this.picturePath;
			String adminTmp = "" + this.admin;
			String careerIDTmp = "" + this.careerID;

			sql = "INSERT INTO " + this.tableName + " VALUES(" + 
				      null + ", " + 
				      usernameTmp + "," + 
				      passwordTmp + ", " +
				      fullnameTmp + ", " +
				      birthDateTmp + ", " + 
				      heightTmp + ", " + 
				      weightTmp + ", " + 
				      bloodTypeTmp + ", " + 
				      hobbyTmp + ", " + 
				      phoneTmp + ", " + 
				      emailTmp + ", "+
				      detailEduTmp + ", " + 
				      detailFavTmp + ", " + 
				      detailLskTmp + ", " + 
				      picturePathTmp + ", " + 
				      adminTmp + ", " + 
				      this.careerID + ")";
			result = conn.executeData(sql);
			log.info(sql);
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

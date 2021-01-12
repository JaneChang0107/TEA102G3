package com.member.model;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;

public class MemberVO implements java.io.Serializable{

		@Override
	public String toString() {
		return "[m_id=" + m_id + ", m_email=" + m_email + ", m_password=" + m_password + ", m_name=" + m_name
				+ ", m_gender=" + m_gender + ", m_phone=" + m_phone + ", m_address=" + m_address + ", m_birth="
				+ m_birth + ", m_headpic=" + Arrays.toString(m_headpic) + ", m_status=" + m_status + ", m_identity="
				+ m_identity + ", m_id_pic=" + Arrays.toString(m_id_pic) + ", m_account=" + m_account
				+ ", m_accountName=" + m_accountName + ", b_code=" + b_code + ", m_bank_pic="
				+ Arrays.toString(m_bank_pic) + ", m_moneyTranDate=" + m_moneyTranDate + ", m_storename=" + m_storename
				+ ", m_info=" + m_info + ", m_cover=" + Arrays.toString(m_cover) + ", m_hi=" + m_hi + ", m_offlineHi="
				+ m_offlineHi + ", m_coin=" + m_coin + ", o_id=" + o_id + ", p_id=" + p_id + ", od_count=" + od_count
				+ ", o_status=" + o_status + ", p_name=" + p_name + ", o_date=" + o_date + "]";
	}
		private String m_id;
		private String m_email;
		private String m_password;
		private String m_name;
		private String m_gender;
		private String m_phone;
		private String m_address;
		private java.sql.Date m_birth;
		private byte[] m_headpic;
		private Integer m_status;
		private String m_identity;
		private byte[] m_id_pic;
		private String m_account;
		private String m_accountName;
		private String b_code;
		private byte[] m_bank_pic;
		private java.sql.Timestamp m_moneyTranDate;
		private String m_storename;
		private String m_info;
		private byte[] m_cover;
		private String m_hi;
		private String m_offlineHi;
		private Integer m_coin;
		private String o_id;
		private String p_id;
		private String od_count;
		private String o_status;
		private String p_name;
		private Timestamp o_date;
		
		public Timestamp getO_date() {
			return o_date;
		}
		public void setO_date(Timestamp o_date) {
			this.o_date = o_date;
		}
		public String getO_id() {
			return o_id;
		}
		public void setO_id(String o_id) {
			this.o_id = o_id;
		}
		public String getP_id() {
			return p_id;
		}
		public void setP_id(String p_id) {
			this.p_id = p_id;
		}
		public String getOd_count() {
			return od_count;
		}
		public void setOd_count(String od_count) {
			this.od_count = od_count;
		}
		public String getO_status() {
			return o_status;
		}
		public void setO_status(String o_status) {
			this.o_status = o_status;
		}
		public String getP_name() {
			return p_name;
		}
		public void setP_name(String p_name) {
			this.p_name = p_name;
		}
		public String getM_id() {
			return m_id;
		}
		public void setM_id(String m_id) {
			this.m_id = m_id;
		}
		public String getM_email() {
			return m_email;
		}
		public void setM_email(String m_email) {
			this.m_email = m_email;
		}
		public String getM_password() {
			return m_password;
		}
		public void setM_password(String m_password) {
			this.m_password = m_password;
		}
		public String getM_name() {
			return m_name;
		}
		public void setM_name(String m_name) {
			this.m_name = m_name;
		}
		public String getM_gender() {
			return m_gender;
		}
		public void setM_gender(String m_gender) {
			this.m_gender = m_gender;
		}
		public String getM_phone() {
			return m_phone;
		}
		public void setM_phone(String m_phone) {
			this.m_phone = m_phone;
		}
		public String getM_address() {
			return m_address;
		}
		public void setM_address(String m_address) {
			this.m_address = m_address;
		}
		public java.sql.Date getM_birth() {
			return m_birth;
		}
		public void setM_birth(java.sql.Date m_birth) {
			this.m_birth = m_birth;
		}
		public byte[] getM_headpic() {
			return m_headpic;
		}
		public void setM_headpic(byte[] m_headpic) {
			this.m_headpic = m_headpic;
		}
		public Integer getM_status() {
			return m_status;
		}
		public void setM_status(Integer m_status) {
			this.m_status = m_status;
		}
		public String getM_identity() {
			return m_identity;
		}
		public void setM_identity(String m_identity) {
			this.m_identity = m_identity;
		}
		public byte[] getM_id_pic() {
			return m_id_pic;
		}
		public void setM_id_pic(byte[] m_id_pic) {
			this.m_id_pic = m_id_pic;
		}
		public String getM_account() {
			return m_account;
		}
		public void setM_account(String m_account) {
			this.m_account = m_account;
		}
		public String getM_accountName() {
			return m_accountName;
		}
		public void setM_accountName(String m_accountName) {
			this.m_accountName = m_accountName;
		}
		public String getB_code() {
			return b_code;
		}
		public void setB_code(String b_code) {
			this.b_code = b_code;
		}
		public byte[] getM_bank_pic() {
			return m_bank_pic;
		}
		public void setM_bank_pic(byte[] m_bank_pic) {
			this.m_bank_pic = m_bank_pic;
		} 
		public java.sql.Timestamp getM_moneyTranDate() {
			return m_moneyTranDate;
		}
		public void setM_moneyTranDate(java.sql.Timestamp m_moneyTranDate) {
			this.m_moneyTranDate = m_moneyTranDate;
		}
		public String getM_storename() {
			return m_storename;
		}
		public void setM_storename(String m_storename) {
			this.m_storename = m_storename;
		}
		public String getM_info() {
			return m_info;
		}
		public void setM_info(String m_info) {
			this.m_info = m_info;
		}
		public byte[] getM_cover() {
			return m_cover;
		}
		public void setM_cover(byte[] m_cover) {
			this.m_cover = m_cover;
		}
		public String getM_hi() {
			return m_hi;
		}
		public void setM_hi(String m_hi) {
			this.m_hi = m_hi;
		}
		public String getM_offlineHi() {
			return m_offlineHi;
		}
		public void setM_offlineHi(String m_offlineHi) {
			this.m_offlineHi = m_offlineHi;
		}
		public Integer getM_coin() {
			return m_coin;
		}
		public void setM_coin(Integer m_coin) {
			this.m_coin = m_coin;
		}
		
		//回傳圖片以base64解碼字串
		
		public String getM_headpic2() {
			if(m_headpic!=null) {
				m_headpic=Base64.getEncoder().encode(m_headpic);
			    String str ="data:image/jpg;base64,"+new String(m_headpic);
			    return str;
			} else {
				return "../../images/LOGO找不到圖片.png";
			}
		}
		
		public String getM_id_pic2() {
			if(m_id_pic!=null) {
				m_id_pic=Base64.getEncoder().encode(m_id_pic);
			    String str ="data:image/jpg;base64,"+new String(m_id_pic);
			    return str;
			} else {
				return "../../images/LOGO找不到圖片.png";
			}
		}
		
		public String getM_bank_pic2() {
			if(m_bank_pic!=null) {
				m_bank_pic=Base64.getEncoder().encode(m_bank_pic);
			    String str ="data:image/jpg;base64,"+new String(m_bank_pic);
			    return str;
			} else {
				return "../../images/LOGO找不到圖片.png";
			}
		}
		
		public String getM_cover2() {
			if(m_cover!=null) {
				m_cover=Base64.getEncoder().encode(m_cover);
			    String str ="data:image/jpg;base64,"+new String(m_cover);
			    return str;
			} else {
				return "../../images/LOGO找不到圖片.png";
			}
		}
		public String getM_statusByString() {
			String statusName = "";
			if(m_status ==0) {
				statusName ="未開通";
			}else if(m_status ==1) {
				statusName ="買家";
			}else if (m_status == 2) {
				statusName ="賣家未驗證";
			}else if(m_status ==3) {
				statusName="賣家";
			}
			return statusName;
		}
		
		
		
		
		
	}


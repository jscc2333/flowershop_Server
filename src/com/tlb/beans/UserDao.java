package com.tlb.beans;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import com.tlb.global.*;
import com.tlb.utils.DBUtils;
import com.tlb.entity.User;

public class UserDao {
	
 	private static InitialContext context = null;
	private DataSource dataSource = null;

//	ע���û�
	public int signUp(User user) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst =null;
		int result = 0;
		int status_no =  0;
		try{
			pstmt = conn.prepareStatement("SELECT *FROM user WHERE username=?");
			pstmt.setString(1, user.getUsername());
			rst = pstmt.executeQuery();
			if(rst.next()){
//				��ǰ�û����ڣ����ش���no
				status_no = Global.STATUS_ERR;
				return status_no;
			}else{
				try{
					pstmt = conn.prepareStatement("INSERT INTO user(username,password) VALUES (?,?)");
					pstmt.setString(1, user.getUsername());
					pstmt.setString(2, user.getPassword());
//					�����û���������
					result = pstmt.executeUpdate();
					if(result>0){
						status_no = Global.STATUS_OK;
						return status_no;
					}
				}catch(Exception e){
					System.out.println(e);
					return 0;
				}finally{
					try{
						conn.close();
					}catch(Exception e){
						System.out.print(e);
					}
				}
			}
		}catch(Exception e){
			System.out.println(e);
			return 0;
		}finally{
			try{
				conn.close();
			}catch(Exception e){
				System.out.print(e);
			}
		}
		return 0;
	}
	
	public int signIn(User user){
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst =null;
		int status_no = 0;
		try{
			pstmt = conn.prepareStatement("SELECT *FROM user WHERE username=?");
			pstmt.setString(1, user.getUsername());
			rst = pstmt.executeQuery();
			if(rst.next()){
//				�ж��������������ݿ������Ƿ����
				String inPw = user.getPassword();
				String dbPw = rst.getString("password");
				 if(inPw.equals(dbPw)){
					 //����򷵻�����״̬��
					 status_no = Global.STATUS_OK;
					 return status_no;
				}else{
					//������򷵻��������״̬��
					status_no = Global.PASSWORD_ERR;
					return status_no;
				}
			}else{
//				�Ҳ����û��򷵻��û���������״̬��
				status_no = Global.USERNAME_ERR;
				return status_no;
			}

		}catch(Exception e){
			return 0;
		}finally{
			try{
				conn.close();
			}catch(Exception e){
				System.out.print(e);
			}
		}
	}
}

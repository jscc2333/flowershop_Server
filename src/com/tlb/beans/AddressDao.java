package com.tlb.beans;

import javax.naming.InitialContext;
import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;

import com.tlb.entity.Address;
import com.tlb.global.Global;
import com.tlb.utils.DBUtils;

public class AddressDao {
	private static InitialContext context = null;
	private DataSource dataSource = null;

	public int configAddress(String username, Address address, int operationType) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		int status_no = 0;
		try {
			int addressID = address.getAddressID();
			String consignee = address.getConsignee();
			String addressname = address.getAddressname();
			String phonenumber = address.getPhonenumber();
			if (operationType == Global.ADDRESS_ADD) {
				pstmt = conn.prepareStatement(
						"INSERT address(addressID,username,consignee,addressname,phonenumber) " + "VALUES(?,?,?,?,?)");
				pstmt.setInt(1, addressID);
				pstmt.setString(2, username);
				pstmt.setString(3, consignee);
				pstmt.setString(4, addressname);
				pstmt.setString(5, phonenumber);
			} else if (operationType == Global.ADDRESS_UPDATE) {
				pstmt = conn.prepareStatement(
						"UPDATE address SET consignee=?,addressname=?,phonenumber=? WHERE addressID=? AND username=?");
				pstmt.setString(1, consignee);
				pstmt.setString(2, addressname);
				pstmt.setString(3, phonenumber);
				pstmt.setInt(4, addressID);
				pstmt.setString(5, username);
			} else {
			}
			result = pstmt.executeUpdate();
			if (result > 0) {
				status_no = Global.STATUS_OK;
				return status_no;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
		return status_no;
	}

	public ArrayList<Address> getAddress(String username) {
		ArrayList<Address> addressList = new ArrayList<Address>(0);
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = conn.prepareStatement("SELECT *FROM address WHERE username=?");
			pstmt.setString(1, username);
			rst = pstmt.executeQuery();
			while (rst.next()) {
				int addressID = rst.getInt("addressID");
				String addressname = rst.getString("addressname");
				String consignee = rst.getString("consignee");
				String phonenumber = rst.getString("phonenumber");
				Address address = new Address(addressID, addressname, consignee, phonenumber);
				addressList.add(addressList.size(), address);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return addressList;
	}
}

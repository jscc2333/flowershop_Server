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
			Boolean defaultAddress = address.getDefaultAddress();
			if (operationType == Global.ADDRESS_ADD) {
				try {
					PreparedStatement pstmtTemp = conn.prepareStatement("SELECT addressID FROM address");
					ResultSet rstTemp = pstmtTemp.executeQuery();
					rstTemp.last();
					addressID = rstTemp.getRow() + 1;
				} catch (Exception e) {
					System.out.println(e);
				}
				if (defaultAddress) {
					try {
						PreparedStatement pstmtTemp = conn
								.prepareStatement("UPDATE address SET defaultAddress = false");
						pstmtTemp.executeUpdate();
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				pstmt = conn.prepareStatement(
						"INSERT address(addressID,username,consignee,addressname,phonenumber,defaultAddress) "
								+ "VALUES(?,?,?,?,?,?)");
				pstmt.setInt(1, addressID);
				pstmt.setString(2, username);
				pstmt.setString(3, consignee);
				pstmt.setString(4, addressname);
				pstmt.setString(5, phonenumber);
				pstmt.setBoolean(6, defaultAddress);
			} else if (operationType == Global.ADDRESS_UPDATE) {
				try {
					PreparedStatement pstmtTemp = conn.prepareStatement("SELECT addressID FROM address");
					ResultSet rstTemp = pstmtTemp.executeQuery();
				} catch (Exception e) {
					System.out.println(e);
				}
				if (defaultAddress) {
					try {
						PreparedStatement pstmtTemp = conn
								.prepareStatement("UPDATE address SET defaultAddress = false");
						pstmtTemp.executeUpdate();
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				pstmt = conn.prepareStatement(
						"UPDATE address SET consignee=?,addressname=?,phonenumber=?,defaultAddress=? WHERE addressID=? AND username=?");
				pstmt.setString(1, consignee);
				pstmt.setString(2, addressname);
				pstmt.setString(3, phonenumber);
				pstmt.setBoolean(4, defaultAddress);
				pstmt.setInt(5, addressID);
				pstmt.setString(6, username);
				result = pstmt.executeUpdate();
				if (result > 0) {
					status_no = Global.STATUS_OK;
					return status_no;
				}
			} else if (operationType == Global.ADDRESS_DELETE) {
				try {
					pstmt = conn.prepareStatement("DELETE FROM address WHERE addressID = ?");
					pstmt.setInt(1, addressID);
					pstmt.executeUpdate();
					result = pstmt.executeUpdate();
					status_no = Global.STATUS_OK;
				} catch (Exception e) {
					System.out.println(e);
				}
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
				Boolean defaultAddress = rst.getBoolean("defaultAddress");
				Address address = new Address(addressID, addressname, consignee, phonenumber, defaultAddress);
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

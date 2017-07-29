package com.tlb.beans;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.*;
import java.sql.*;

import com.tlb.entity.Tip;
import com.tlb.global.Global;
import com.tlb.utils.DBUtils;

public class TipDao {

	private static InitialContext context = null;
	private DataSource dataSource = null;

	public ArrayList<Tip> getTips() {
		ArrayList<Tip> tips = new ArrayList<Tip>(0);
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = conn.prepareStatement("SELECT *FROM livingtip");
			rst = pstmt.executeQuery();
			if (!rst.next()) {
				return null;
			}
			while (rst.next()) {
				int tipID = rst.getInt("tipID");
				String title = rst.getString("title");
				String text = rst.getString("text");
				String img = rst.getString("img");
				Tip tip = new Tip(tipID, title, text, img);
				tips.add(tips.size(), tip);
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
		return tips;
	}

	public int insertTip(Tip tip) {
		int status_no = 0;
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			int tipID = 0;
			try {
				PreparedStatement pstmtTemp = conn.prepareStatement("SELECT * FROM livingtip");
				ResultSet rst = pstmtTemp.executeQuery();
				if (rst.next()) {
					rst.last();
					tipID = rst.getRow() + 1;
				} else {
					tipID = 1;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			pstmt = conn.prepareStatement("INSERT INTO livingtip(title,text,img,tipID) VALUES (?,?,?,?)");
			pstmt.setString(1, tip.getTitle());
			pstmt.setString(2, tip.getText());
			pstmt.setString(3, tip.getImgsrc());
			pstmt.setInt(4, tipID);
			result = pstmt.executeUpdate();
			if(result > 0){
				status_no = Global.STATUS_OK;
			}
		} catch (Exception e) {

		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return status_no;
	}

	public int deleteTip(Tip tip) {
		int status_no = 0;
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement("DELETE FROM livingtip WHERE tipID = ?");
			pstmt.setInt(1, tip.getTipID());
			result = pstmt.executeUpdate();
			if (result > 0) {
				status_no = Global.STATUS_OK;
			}

		} catch (Exception e) {

		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return status_no;
	}
}

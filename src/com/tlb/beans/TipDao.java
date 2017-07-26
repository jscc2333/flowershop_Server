package com.tlb.beans;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.*;
import java.sql.*;

import com.tlb.entity.Tip;
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
}

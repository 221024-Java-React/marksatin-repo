package com.proj1.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.proj1.exceptions.UserRoleDoesNotExistException;
import com.proj1.models.ExpenseType;
import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;
import com.proj1.models.UserRole;

public class UtilMethods {

	public static Ticket createTicketFromDb(ResultSet rs) {
		
		Ticket t = null;
		
		try {
				ExpenseType tType;
				TicketStatus tStatus;
				
				if (rs.getInt(5) == 1) {
					tType = ExpenseType.TRAVEL;
				} else if (rs.getInt(5) == 2) {
					tType = ExpenseType.LODGING;
				} else if (rs.getInt(5) == 3) {
					tType = ExpenseType.FOOD;
				} else {
					tType = ExpenseType.OTHER;
				}
				
				if (rs.getInt(6) == 1) {
					tStatus = TicketStatus.PENDING;
				} else if (rs.getInt(6) == 2) {
					tStatus = TicketStatus.APPROVED;
				} else {
					tStatus = TicketStatus.DENIED;;
				}
				
				t = new Ticket(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getString(4), tType, tStatus, rs.getInt(7));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	public static int convertTypeToInt(ExpenseType type) {
		
		ExpenseType[] etArray = ExpenseType.values();
		int colNum = 0;
		
		for (int i = 0; i < etArray.length; i++) {
			if (type.equals(etArray[i])) {
				colNum = i + 1;
				break;
			}
		}
		
		return colNum;
	}
	
	public static int convertStatusToInt(TicketStatus status) {
		
		TicketStatus[] tsArray = TicketStatus.values();
		int colNum = 0;
		
		for (int i = 0; i < tsArray.length; i++) {
			if (status.equals(tsArray[i])) {
				colNum = i + 1;
				break;
			}
		}
		
		return colNum;
	}
	
	public static int convertRoleToInt(UserRole role) {
		
		UserRole[] urArray = UserRole.values();
		int colNum = 0;
		
		for (int i = 0; i < urArray.length; i++) {
			if (role.equals(urArray[i])) {
				colNum = i + 1;
				break;
			}
		}
		
		return colNum;
	}
	
	public static UserRole convertIntToRole(ResultSet rs, int colNum) {
		
		UserRole[] urArray = UserRole.values();
		UserRole role = null;
		
		try {
			for (int i = 0; i < urArray.length; i++) {
				if (rs.getInt(colNum) == i + 1) {
					role = urArray[i];
					break;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return role;
	}
	
	public static ExpenseType convertStringToType(String stringType) {
		
		ExpenseType[] etArray = ExpenseType.values();
		ExpenseType et = null;
		
		for (int i = 0; i < etArray.length; i++) {
			if (stringType.equals(etArray[i].toString())) {
				et = etArray[i];
				break;
			}
		}
		
		return et;
	}
	
	public static TicketStatus convertStringToStatus(String stringStatus) {
		
		TicketStatus[] tsArray = TicketStatus.values();
		TicketStatus ts = null;
		
		for (int i = 0; i < tsArray.length; i++) {
			if (stringStatus.equals(tsArray[i].toString())) {
				ts = tsArray[i];
				break;
			}
		}
		
		return ts;
	}
}

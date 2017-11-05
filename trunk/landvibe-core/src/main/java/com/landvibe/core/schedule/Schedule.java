package com.landvibe.core.schedule;

import java.util.List;

import com.landvibe.common.model.BaseModel;
import com.landvibe.core.userfavorite.UserFavorite;


public class Schedule extends BaseModel {
	private static final long serialVersionUID = -175057078389873319L;

	private long schedule_no;
	private long company_no;
	private String date;
	private boolean slot_09;
	private boolean slot_10;
	private boolean slot_11;
	private boolean slot_12;
	private boolean slot_13;
	private boolean slot_14;
	private boolean slot_15;
	private boolean slot_16;
	private boolean slot_17;
	private boolean slot_18;
	private boolean slot_19;
	
	public Schedule(){}
	
	
	public Schedule(long schedule_no) {
		super();
		this.schedule_no = schedule_no;
	}
	public long getSchedule_no() {
		return schedule_no;
	}
	public void setSchedule_no(long schedule_no) {
		this.schedule_no = schedule_no;
	}
	public long getCompany_no() {
		return company_no;
	}
	public void setCompany_no(long company_no) {
		this.company_no = company_no;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isSlot_09() {
		return slot_09;
	}
	public void setSlot_09(boolean slot_09) {
		this.slot_09 = slot_09;
	}
	public boolean isSlot_10() {
		return slot_10;
	}
	public void setSlot_10(boolean slot_10) {
		this.slot_10 = slot_10;
	}
	public boolean isSlot_11() {
		return slot_11;
	}
	public void setSlot_11(boolean slot_11) {
		this.slot_11 = slot_11;
	}
	public boolean isSlot_12() {
		return slot_12;
	}
	public void setSlot_12(boolean slot_12) {
		this.slot_12 = slot_12;
	}
	public boolean isSlot_13() {
		return slot_13;
	}
	public void setSlot_13(boolean slot_13) {
		this.slot_13 = slot_13;
	}
	public boolean isSlot_14() {
		return slot_14;
	}
	public void setSlot_14(boolean slot_14) {
		this.slot_14 = slot_14;
	}
	public boolean isSlot_15() {
		return slot_15;
	}
	public void setSlot_15(boolean slot_15) {
		this.slot_15 = slot_15;
	}
	public boolean isSlot_16() {
		return slot_16;
	}
	public void setSlot_16(boolean slot_16) {
		this.slot_16 = slot_16;
	}
	public boolean isSlot_17() {
		return slot_17;
	}
	public void setSlot_17(boolean slot_17) {
		this.slot_17 = slot_17;
	}
	public boolean isSlot_18() {
		return slot_18;
	}
	public void setSlot_18(boolean slot_18) {
		this.slot_18 = slot_18;
	}
	public boolean isSlot_19() {
		return slot_19;
	}
	public void setSlot_19(boolean slot_19) {
		this.slot_19 = slot_19;
	}
}

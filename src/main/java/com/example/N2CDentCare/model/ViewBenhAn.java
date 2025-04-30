package com.example.N2CDentCare.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "view_benh_an")
public class ViewBenhAn {
	@Id
	@Column(name = "Id")
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "SDT")
	private String sdt;
	
	@Column(name = "BenhNhan")
	private String BenhNhan;
	
	@Column(name = "BacSi")
	private String BacSi;
	
	@Column(name = "NgayKham")
	private String NgayKham;
	
	@Column(name = "ChuanDoan")
	private String ChuanDoan;

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getBenhNhan() {
		return BenhNhan;
	}

	public void setBenhNhan(String benhNhan) {
		BenhNhan = benhNhan;
	}

	public String getBacSi() {
		return BacSi;
	}

	public void setBacSi(String bacSi) {
		BacSi = bacSi;
	}

	public String getNgayKham() {
		return NgayKham;
	}
	
	
	public String getNgayKhamAsView() {
		String day = NgayKham.substring(0,2);
		String month = NgayKham.substring(2,4);
		String year = NgayKham.substring(4);
		
		String kq =  day + "/" + month + "/" + year;
		
		System.out.println(kq);
		return kq;

	}

	public void setNgayKham(String ngayKham) {
		NgayKham = ngayKham;
	}

	public String getChuanDoan() {
		return ChuanDoan;
	}

	public void setChuanDoan(String chuanDoan) {
		ChuanDoan = chuanDoan;
	}

	public ViewBenhAn(String sdt, String benhNhan, String bacSi, String ngayKham, String chuanDoan) {
		super();
		this.sdt = sdt;
		BenhNhan = benhNhan;
		BacSi = bacSi;
		NgayKham = ngayKham;
		ChuanDoan = chuanDoan;
	}

	public ViewBenhAn() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ViewBenhAn [sdt=" + sdt + ", BenhNhan=" + BenhNhan + ", BacSi=" + BacSi + ", NgayKham=" + NgayKham
				+ ", ChuanDoan=" + ChuanDoan + "]";
	}
	
	public static List<String> getTableColumnTitle(){
		List<String> columnBenhAn = new ArrayList<>();
		columnBenhAn.add("Bệnh nhân");
		columnBenhAn.add("Bác sĩ");
		columnBenhAn.add("Ngày khám");
		columnBenhAn.add("Chuẩn đoán");
		
		return columnBenhAn;
	}

}

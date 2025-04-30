package com.example.N2CDentCare.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "benhnhan")
public class BenhNhan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaBn", columnDefinition = "AUTO_INCREMENT")
	private int maBn;
	
	@Column(name = "HoTen")
	private String HoTen;
	
	@Column(name = "GioiTinh")
	private boolean GioiTinh;
	
	@Column(name = "sdt")
	private String sdt;
	
	@Column(name = "DiaChi")
	private String DiaChi;
	
	@jakarta.persistence.Transient
	private String sdtCu;
	
	public String getSdtCu() {
		return sdtCu;
	}

	public void setSdtCu(String sdtCu) {
		this.sdtCu = sdtCu;
	}

	public int getMaBn() {
		return maBn;
	}

	public void setMaBn(int maBn) {
		this.maBn = maBn;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public boolean getGioiTinh() {
		return GioiTinh;
	}

	public String getGioiTinhAsView() {
		if (GioiTinh)
			return "Nữ";
		return "Nam";
	}

	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public BenhNhan(String diaChi, boolean gioiTinh,String hoTen,  String sdt) {
		super();
		HoTen = hoTen;
		GioiTinh = gioiTinh;
		this.sdt = sdt;
		DiaChi = diaChi;
	}

	public BenhNhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BenhNhan [MaBn=" + maBn + ",HoTen=" + HoTen + ", GioiTinh=" + getGioiTinhAsView() + ", sdt=" + sdt + ", DiaChi=" + DiaChi + "]";
	}
	
	public static List<String> getTableColumnTitle(){
		List<String> kq = new ArrayList<>();
		kq.add("Họ và tên");
		kq.add("Giới tính");
		kq.add("Số điện thoại");

		return kq;
	}
}

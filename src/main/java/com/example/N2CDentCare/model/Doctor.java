package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "view_bacsi")
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaBs")
	private String MaBs;
	
	@Column(name = "HoTen")
	private String HoTen;
	
	@Column(name = "GioiTinh")
	private String GioiTinh;
	
	@Column(name = "NgaySinh")
	private String NgaySinh;
	
	@Column(name = "sdt")
	private String sdt;
	
	@Column(name = "DiaChi")
	private String DiaChi;
	
	@jakarta.persistence.Transient
	private String MoTa;
	
	@Column(name = "HocVan")
	private String HocVan;
	
	@Column(name = "Img")
	private String Img;

	public String getMaBs() {
		return MaBs;
	}

	public void setMaBs(String maBs) {
		MaBs = maBs;
	}

	public String getHoTen() {
		return HoTen;
	}

	public String getMoTa() {
		return MoTa;
	}

	public String getHocVan() {
		String kq = HocVan.toUpperCase();
		return kq;
	}

	public void setHocVan(String hocVan) {
		HocVan = hocVan;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
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


	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

	public Doctor(String maBs, String hoTen, String gioiTinh, String ngaySinh, String sdt, String diaChi, String hocVan, String img) {
		super();
		MaBs = maBs;
		HoTen = hoTen;
		GioiTinh = gioiTinh;
		NgaySinh = ngaySinh;
		this.sdt = sdt;
		DiaChi = diaChi;
		HocVan = hocVan;
		Img = img;
	}

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Doctor [MaBs=" + MaBs + ", HoTen=" + HoTen + ", GioiTinh=" + GioiTinh + ", NgaySinh=" + NgaySinh
				+ ", sdt=" + sdt + ", DiaChi=" + DiaChi + ", Img=" + Img + ", Img=" + HocVan +"]";
	}


	
}

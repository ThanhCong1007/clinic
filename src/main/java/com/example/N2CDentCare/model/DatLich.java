package com.example.N2CDentCare.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="thongtindatlich")
public class DatLich {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MaDl")
	private int  MaDl;
	
	@Column(name="MaBn")
	private String MaBn;

	@Column(name="NgayKham")
	private String NgayKham;
	
	@Column(name="GioKham")
	private String GioKham;
	
	@Column(name="GhiChu")
	private String GhiChu;

	public DatLich(int maDl, String maBn, String ngayKham, String gioKham, String ghiChu) {
		super();
		MaDl = maDl;
		MaBn = maBn;
		NgayKham = ngayKham;
		GioKham = gioKham;
		GhiChu = ghiChu;
	}

	public DatLich() {
		super();
	}

	public int getMaDl() {
		return MaDl;
	}

	public void setMaDl(int maDl) {
		MaDl = maDl;
	}

	public String getMaBn() {
		return MaBn;
	}

	public void setMaBn(String maBn) {
		MaBn = maBn;
	}

	public String getNgayKham() {
		return NgayKham;
	}

	public void setNgayKham(String ngayKham) {
		NgayKham = ngayKham;
	}

	public String getGioKham() {
		return GioKham;
	}

	public void setGioKham(String gioKham) {
		GioKham = gioKham;
	}

	public String getGhiChu() {
		return GhiChu;
	}

	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "DatLich [MaDl=" + MaDl + ", MaBn=" + MaBn + ", NgayKham=" + NgayKham + ", GioKham=" + GioKham
				+ ", GhiChu=" + GhiChu + "]";
	}
	
}

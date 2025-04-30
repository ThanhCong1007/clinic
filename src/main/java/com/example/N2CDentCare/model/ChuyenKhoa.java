package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "chuyenkhoa")
public class ChuyenKhoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaKhoa")
	private String MaKhoa;
	
	@Column(name = "TenKhoa")
	private String TenKhoa;

	public String getMaKhoa() {
		return MaKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		MaKhoa = maKhoa;
	}

	public String getTenKhoa() {
		return TenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		TenKhoa = tenKhoa;
	}

	@Override
	public String toString() {
		return "ChuyenKhoa [MaKhoa=" + MaKhoa + ", TenKhoa=" + TenKhoa + "]";
	}

	public ChuyenKhoa(String maKhoa, String tenKhoa) {
		super();
		MaKhoa = maKhoa;
		TenKhoa = tenKhoa;
	}

	public ChuyenKhoa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

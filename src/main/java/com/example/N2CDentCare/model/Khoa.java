package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "khoa")
public class Khoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;

	@Column(name = "MaBs")
	private String MaBs;
	
	@Column(name = "MaChuyenKhoa")
	private String MaChuyenKhoa;

	public Khoa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Khoa(String maBs, String maChuyenKhoa) {
		super();
		MaBs = maBs;
		MaChuyenKhoa = maChuyenKhoa;
	}

	@Override
	public String toString() {
		return "Khoa [MaBs=" + MaBs + ", MaChuyenKhoa=" + MaChuyenKhoa + "]";
	}

	public String getMaBs() {
		return MaBs;
	}

	public void setMaBs(String maBs) {
		MaBs = maBs;
	}

	public String getMaChuyenKhoa() {
		return MaChuyenKhoa;
	}

	public void setMaChuyenKhoa(String maChuyenKhoa) {
		MaChuyenKhoa = maChuyenKhoa;
	}
	
}

package com.example.N2CDentCare.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="dichvu")
public class Dichvu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaDv")
	private String MaDv;
	
	@Column(name = "Img")
	private String Img;

	public String getMaDv() {
		return MaDv;
	}

	public void setMaDv(String maDv) {
		MaDv = maDv;
	}

	@Column(name = "TenDv")
	private String TenDv;
	
	@Column(name = "DonGia")
	private String DonGia;
	
	@Column(name = "MoTa")
	private String MoTa;
	
	public String getTenDv() {
		return TenDv;
	}

	public void setTenDv(String tenDv) {
		TenDv = tenDv;
	}

	public String getDonGia() {		
		return DonGia;
	}

	public void setDonGia(String donGia) {
		DonGia = donGia;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}
	
	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

	public Dichvu() {
		super();
	}
	
	public Dichvu(String maDv, String tenDv, String donGia, String moTa, String img) {
		super();
		MaDv = maDv;
		TenDv = tenDv;
		DonGia = donGia;
		MoTa = moTa;
		Img = img;
	}

	@Override
	public String toString() {
		return "Dichvu [MaDv=" + MaDv + ", TenDv=" + TenDv + ", DonGia=" + DonGia + ", MoTa=" + MoTa + "Img=" + Img + "]";
	}
	
	

}

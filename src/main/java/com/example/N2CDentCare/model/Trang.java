package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "noidungtrang")
public class Trang {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	
	@Column(name = "Trang")
	private String Trang;
	
	@Column(name = "TieuDe")
	private String TieuDe;
	
	@Column(name = "MoTa")
	private String MoTa;

	@Column(name = "Section")
	private String Section;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTrang() {
		return Trang;
	}

	public void setTrang(String trang) {
		Trang = trang;
	}

	public String getTieuDe() {
		return TieuDe;
	}

	public void setTieuDe(String tieuDe) {
		TieuDe = tieuDe;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}

	public String getSection() {
		return Section;
	}

	public void setSection(String section) {
		Section = section;
	}

	@Override
	public String toString() {
		return "Trang [Id=" + Id + ", Trang=" + Trang + ", TieuDe=" + TieuDe + ", MoTa=" + MoTa + ", Section=" + Section
				+ "]";
	}

	public Trang(int id, String trang, String tieuDe, String moTa, String section) {
		super();
		Id = id;
		Trang = trang;
		TieuDe = tieuDe;
		MoTa = moTa;
		Section = section;
	}

	public Trang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

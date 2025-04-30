package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name="giolamviec")
public class GioLamViec {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaGio")
	private String MaGio;
	
	@Column(name = "KhungGio")
	private String KhungGio;

	public String getMaGio() {
		return MaGio;
	}

	public void setMaGio(String maGio) {
		MaGio = maGio;
	}

	public String getKhungGio() {
		String str1 = KhungGio.substring(0, 2);
		String str2 = KhungGio.substring(2, 4);
		String str3 = KhungGio.substring(4, 6);
		String str4 = KhungGio.substring(6);
		
		return str1 + ":" + str2 + " - " + str3 + ":" + str4;
	}

	public void setKhungGio(String khungGio) {
		KhungGio = khungGio;
	}

	public GioLamViec(String maGio, String khungGio) {
		super();
		MaGio = maGio;
		KhungGio = khungGio;
	}

	public GioLamViec() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GioLamViec [MaGio=" + MaGio + ", KhungGio=" + KhungGio + "]";
	}
	
	
}

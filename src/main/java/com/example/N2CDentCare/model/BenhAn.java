package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "benhan")
public class BenhAn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaBa", columnDefinition = "AUTO_INCREMENT")
	private int maBa;
	
	@Column(name = "sdtBenhNhan")
	private String sdt;
	
	@Column(name = "MaBs")
	private String MaBs;
	
	@Column(name = "NgayKham")
	private String NgayKham;
	
	@Column(name = "ChuanDoan")
	private String ChuanDoan;
	
	@jakarta.persistence.Transient
	private String sdtCu;
	
	public String getSdtCu() {
		return sdtCu;
	}

	public void setSdtCu(String sdtCu) {
		this.sdtCu = sdtCu;
	}

	public int getMaBa() {
		return maBa;
	}

	public void setMaBa(int maBa) {
		this.maBa = maBa;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getMaBs() {
		return MaBs;
	}

	public void setMaBs(String maBs) {
		MaBs = maBs;
	}

	public String getNgayKham() {
		return NgayKham;
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

	public BenhAn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BenhAn(String chuanDoan, String maBs, String ngayKham, String sdt) {
		super();
		this.sdt = sdt;
		MaBs = maBs;
		NgayKham = ngayKham;
		ChuanDoan = chuanDoan;
	}

	@Override
	public String toString() {
		return "BenhAn [MaBa=" + this.maBa + ", Sdt=" + sdt + ", MaBs=" + MaBs + ", NgayKham=" + NgayKham + ", ChuanDoan="
				+ ChuanDoan + "]";
	}

	
	
}

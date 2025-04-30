package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "banggiarangsu")
public class BangGiaRangSu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	
	@Column(name = "Type")
	private String Type;
	
	@Column(name = "TenRang")
	private String TenRang;

	@Column(name = "ChatLieu")
	private String ChatLieu;
	
	@Column(name = "BaoHanh")
	private String BaoHanh;
	
	@Column(name = "Img")
	private String Img;
	
	@Column(name = "Gia")
	private String Gia;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getTenRang() {
		return TenRang;
	}

	public void setTenRang(String tenRang) {
		TenRang = tenRang;
	}

	public String getChatLieu() {
		return ChatLieu;
	}

	public void setChatLieu(String chatLieu) {
		ChatLieu = chatLieu;
	}

	public String getBaoHanh() {
		return "(" + BaoHanh + ")";
	}

	public void setBaoHanh(String baoHanh) {
		BaoHanh = baoHanh;
	}

	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

	public String getGia() {
		return Gia;
	}

	public void setGia(String gia) {
		Gia = gia;
	}

	@Override
	public String toString() {
		return "BangGiaRangSu [Id=" + Id + ",Type=" + Type + ", TenRang=" + TenRang + ", ChatLieu=" + ChatLieu
				+ ", BaoHanh=" + BaoHanh + ", Img=" + Img + ", Gia=" + Gia + "]";
	}

	public BangGiaRangSu(int id,String type ,String tenRang, String chatLieu, String baoHanh, String img, String gia) {
		super();
		Id = id;
		Type = type;
		TenRang = tenRang;
		ChatLieu = chatLieu;
		BaoHanh = baoHanh;
		Img = img;
		Gia = gia;
	}

	public BangGiaRangSu() {
		super();
		// TODO Auto-generated constructor stub
	}	
}

if(window.location.pathname == "/tim-benh-nhan"){
	window.history.replaceState(null, document.title, "/nhan-vien/quan-ly");
}

function insertBenhNhan(){
	document.getElementById("benh-nhan-form").style.display = "block";
	document.getElementById("input-benh-nhan-sdt").value = null;
	document.getElementById("input-benh-nhan-ten").value = null;
	document.getElementById("input-benh-nhan-sdt-cu").value = null;
	document.getElementById("input-benh-nhan-id").value = "-1";

}

function updateBenhNhan(id){
	document.getElementById("benh-nhan-form").style.display = "block";
	document.getElementById("btn-benh-nhan").textContent = "Sửa hồ sơ";

	getBenhNhanInfo(id);
}

function getBenhNhanInfo(id){
	let position = id;
	let ten = document.getElementById("ten-benh-nhan-" + position).textContent;
//	let gioiTinh = document.getElementById("gioi-tinh-benh-nhan-" + position).textContent;
	let sdt = document.getElementById("sdt-benh-nhan-" + position).textContent;
	document.getElementById("input-benh-nhan-ten").value = ten;
//	document.getElementById("input-benh-nhan-gioi-tinh").textContent = gioiTinh;
	document.getElementById("input-benh-nhan-sdt").value = sdt;
	document.getElementById("input-benh-nhan-sdt-cu").value = sdt;
	document.getElementById("input-benh-nhan-id").value = position;
}

function insertBenhAn(){
	document.getElementById("benh-an-form").style.display = "block";
	document.getElementById("input-benh-an-id").value = "-1";
	document.getElementById("input-benh-an-sdt").value = null;
	document.getElementById("input-benh-an-sdt-cu").value = null;
	document.getElementById("input-benh-an-chuan-doan").value = null;
	document.getElementById("input-benh-an-ngay-value").value = null;
	
	document.getElementById("benh-an-input-date-row").style.display = "none";
}

function updateBenhAn(id){
	document.getElementById("benh-an-form").style.display = "block";
	document.getElementById("btn-benh-an").textContent = "Sửa bệnh án";
	document.getElementById("benh-an-input-date-row").style.display = "block";

	getBenhAnInfo(id);
}

function getBenhAnInfo(id){
	console.log(id);
	
	let sdt = id.substring(0,10);
	console.log(sdt);

	let position = id.substring(11);
	console.log(position);

	let ngayKham = document.getElementById("benh-an-ngay-" + position).textContent;
	let chuanDoan = document.getElementById("benh-an-chuan-doan-" + position).textContent;
	
	document.getElementById("input-benh-an-id").value = position;
	document.getElementById("input-benh-an-sdt").value = sdt;
	document.getElementById("input-benh-an-sdt-cu").value = sdt;
	document.getElementById("input-benh-an-chuan-doan").value = chuanDoan;
	document.getElementById("input-benh-an-ngay-value").value = ngayKham;

}

function showDatePicker(){
	document.getElementById("input-benh-an-ngay").style.zIndex = "0";
	document.getElementById("input-benh-an-ngay-value").style.zIndex = "-1";
	document.getElementById("input-benh-an-ngay").style.position = "relative";
	document.getElementById("input-benh-an-ngay-value").style.position = "absolute";
}

function pickDate(){
	let date = new Date(document.getElementById("input-benh-an-ngay").value);

	let day = date.getDate();
	let month = date.getMonth();
	month++;
	let year = date.getFullYear();
	
	if (day < 10){
		day = "0" + day;
	}	
	if (month < 10){
		month = "0" + month;
	}
	
	document.getElementById("input-benh-an-ngay-value").value = day + '/' + month + '/' + year;
	document.getElementById("input-benh-an-ngay").style.zIndex = "-1";
	document.getElementById("input-benh-an-ngay-value").style.zIndex = "0";
	document.getElementById("input-benh-an-ngay").style.position = "absolute";
	document.getElementById("input-benh-an-ngay-value").style.position = "relative";
}
/**
 * 
 */
function showNotification(type, message) {
    const notification = document.getElementById('notification');
    const notificationMessage = document.getElementById('notification-message');

    // Kiểm tra nếu các thành phần tồn tại
    if (!notification || !notificationMessage) {
        console.error("Không tìm thấy phần tử thông báo trong DOM.");
        return;
    }

    // Gán nội dung thông báo
    notificationMessage.textContent = message;

    // Xóa các class alert cũ và thêm class mới
    notification.className = 'alert position-fixed top-0 end-0 m-3'; // Reset class
    notification.classList.add(type === 'success' ? 'alert-success' : 'alert-danger');
    notification.classList.remove('d-none'); // Hiển thị thông báo

    // Tự động ẩn sau 3 giây
    setTimeout(() => {
        notification.classList.add('d-none');
    }, 3000);
}
document.addEventListener("DOMContentLoaded", function () {
    // Lấy thông tin từ server thông qua các thẻ đã được gán giá trị từ model
    const notification = document.querySelector("meta[name='notification']").content;
    const message = document.querySelector("meta[name='message']").content;

    // Kiểm tra trạng thái và hiển thị thông báo
    if (notification === "success") {
        toastr.success(message, "Thành công");
    } else if (notification === "error") {
        toastr.error(message, "Lỗi");
    }
});

// Cấu hình Toastr
toastr.options = {
    "closeButton": true,
    "progressBar": true,
    "timeOut": "5000",
    "positionClass": "toast-top-right",
};
-- các chức năng chính là Người dùng sẽ đặt lịch khám.
-- Admin sẽ quản lý người dùng, quản lý dịch vụ, quản lý bác sĩ, quản lý lịch sử khám bệnh của từng bác sĩ, thống kê thu nhập của hệ thống.
-- Bác sĩ sẽ thăm khám bệnh nhân, nhập bệnh án khi thăm khám, chỉ định thuốc khi khám ( tôi muốn gọi APi thuốc để có thể làm chuẩn quốc tế khi chọn thuốc), tự động lập hóa đơn khi thăm khám xong. 
-- 1. Quản lý bệnh nhân

-- Lịch sử điều trị dài hạn: Tạo tính năng theo dõi quá trình điều trị của bệnh nhân qua nhiều lần khám, giúp bác sĩ có cái nhìn tổng quan về tiến trình điều trị.
-- Hồ sơ răng miệng: Bổ sung sơ đồ răng kỹ thuật số để ghi lại tình trạng từng răng, lưu trữ hình ảnh X-quang và hình ảnh trước/sau điều trị.
-- Nhắc nhở tái khám: Hệ thống tự động gửi thông báo nhắc nhở khi đến thời điểm tái khám định kỳ.

-- 2. Quản lý lịch hẹn

-- Đặt lịch trực tuyến thông minh: Hiển thị thời gian trống của bác sĩ phù hợp với dịch vụ mà khách hàng đang tìm kiếm.
-- Xác nhận lịch hẹn qua SMS/Email: Tự động gửi tin nhắn nhắc nhở trước ngày hẹn 24h.
-- Quản lý hàng chờ: Khi có bệnh nhân hủy lịch, tự động thông báo cho người trong danh sách chờ.

-- 3. Bổ sung cho bác sĩ

-- Kế hoạch điều trị: Cho phép bác sĩ lập kế hoạch điều trị nhiều buổi với chi phí dự kiến, tiến trình điều trị.
-- Báo cáo hiệu suất: Thống kê số lượng bệnh nhân đã khám, tỷ lệ điều trị thành công, đánh giá từ bệnh nhân.
-- Công cụ chẩn đoán hỗ trợ: Tích hợp hệ thống gợi ý chẩn đoán dựa trên triệu chứng và lịch sử bệnh án.

-- 4. Tài chính và thanh toán

-- Gói điều trị và giảm giá: Quản lý các gói dịch vụ, chương trình khuyến mãi cho bệnh nhân thường xuyên.
-- Thanh toán trực tuyến: Tích hợp cổng thanh toán để bệnh nhân có thể thanh toán trước hoặc đặt cọc.
-- Quản lý bảo hiểm: Tích hợp với bảo hiểm y tế để tự động tính phần chi trả của bảo hiểm.
-- Tạo báo cáo tài chính: Báo cáo doanh thu theo ngày, tuần, tháng, quý và theo bác sĩ hoặc dịch vụ.

-- 5. Marketing và chăm sóc khách hàng

-- Hệ thống khách hàng thân thiết: Tích điểm cho bệnh nhân khi sử dụng dịch vụ, đổi điểm lấy ưu đãi.
-- Khảo sát sự hài lòng: Tự động gửi khảo sát sau khi hoàn thành điều trị.
-- Blog nha khoa: Phần blog chia sẻ kiến thức chăm sóc răng miệng, giới thiệu dịch vụ mới.

-- 6. Tích hợp và tiện ích
-- Chatbot tư vấn: Hỗ trợ trả lời các câu hỏi thường gặp, đánh giá sơ bộ tình trạng trước khi đặt lịch.


-- Ứng dụng di động: Phát triển app cho bệnh nhân dễ dàng đặt lịch, xem hồ sơ, thanh toán.
-- Tích hợp Google Calendar: Đồng bộ lịch hẹn với lịch cá nhân của bác sĩ và bệnh nhân.
-- Bản đồ và chỉ đường: Tích hợp Google Maps để bệnh nhân dễ dàng tìm đường đến phòng khám.

-- 8. Báo cáo và phân tích

-- Dashboard cho quản lý: Hiển thị các chỉ số KPI quan trọng như tỷ lệ lấp đầy lịch, doanh thu, số bệnh nhân mới.
-- Phân tích chuyển đổi: Theo dõi tỷ lệ khách hàng từ tư vấn đến điều trị thành công.

-- 9. Tuân thủ và bảo mật

-- Quản lý tuân thủ: Đảm bảo hệ thống tuân theo các quy định về dữ liệu y tế, GDPR.
-- Sao lưu dữ liệu tự động: Lịch trình sao lưu tự động và phục hồi dữ liệu.

-- Những tính năng này có thể được triển khai theo từng giai đoạn, ưu tiên những chức năng quan trọng trước. Với việc thiết kế cơ sở dữ liệu đã có, bạn có thể mở rộng thêm một số bảng hoặc trường để hỗ trợ các chức năng mới này.

-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS nhakhoa;
USE nhakhoa;

-- Bảng vai trò người dùng
CREATE TABLE roles (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng người dùng (users)
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    role_id INT NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    date_of_birth DATE,
    gender ENUM('Nam', 'Nữ', 'Khác'),
    address TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng bệnh nhân (patients)
CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    full_name VARCHAR(100) NOT NULL,
    date_of_birth DATE,
    gender ENUM('Nam', 'Nữ', 'Khác'),
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    address TEXT,
    emergency_contact VARCHAR(100),
    emergency_phone VARCHAR(20),
    medical_history TEXT,
    allergies TEXT,
    points INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng bác sĩ (doctors)
CREATE TABLE doctors (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    specialty VARCHAR(100),
    qualification TEXT,
    experience_years INT,
    license_number VARCHAR(50),
    bio TEXT,
    profile_image VARCHAR(255),
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng dịch vụ (services)
CREATE TABLE services (
    service_id INT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(12, 2) NOT NULL,
    duration INT NOT NULL, -- Thời gian dự kiến (phút)
    category VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng gói dịch vụ (service_packages)
CREATE TABLE service_packages (
    package_id INT AUTO_INCREMENT PRIMARY KEY,
    package_name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(12, 2) NOT NULL,
    discount_percentage DECIMAL(5, 2) DEFAULT 0,
    validity_days INT, -- Số ngày có hiệu lực
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng chi tiết gói dịch vụ (package_services)
CREATE TABLE package_services (
    package_service_id INT AUTO_INCREMENT PRIMARY KEY,
    package_id INT NOT NULL,
    service_id INT NOT NULL,
    quantity INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (package_id) REFERENCES service_packages(package_id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng lịch làm việc bác sĩ (doctor_schedules)
CREATE TABLE doctor_schedules (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT NOT NULL,
    day_of_week ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'),
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng ngày nghỉ của bác sĩ (doctor_days_off)
CREATE TABLE doctor_days_off (
    day_off_id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT NOT NULL,
    date_off DATE NOT NULL,
    reason TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng trạng thái lịch hẹn (appointment_statuses)
CREATE TABLE appointment_statuses (
    status_id INT AUTO_INCREMENT PRIMARY KEY,
    status_name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    color_code VARCHAR(10) -- Mã màu để hiển thị trên giao diện
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng lịch hẹn (appointments)
CREATE TABLE appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    service_id INT,
    package_id INT,
    appointment_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    status_id INT NOT NULL,
    notes TEXT,
    reminder_sent BOOLEAN DEFAULT FALSE,
    created_by INT, -- User ID của người tạo lịch
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE RESTRICT,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE SET NULL,
    FOREIGN KEY (package_id) REFERENCES service_packages(package_id) ON DELETE SET NULL,
    FOREIGN KEY (status_id) REFERENCES appointment_statuses(status_id) ON DELETE RESTRICT,
    FOREIGN KEY (created_by) REFERENCES users(user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng danh sách chờ (waitlist)
CREATE TABLE waitlist (
    waitlist_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    service_id INT,
    preferred_date DATE,
    preferred_time TIME,
    doctor_id INT,
    notes TEXT,
    is_notified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE SET NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng thông tin răng (teeth_chart)
CREATE TABLE teeth_chart (
    chart_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng chi tiết răng (teeth_details)
CREATE TABLE teeth_details (
    teeth_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    chart_id INT NOT NULL,
    tooth_number INT NOT NULL, -- Số răng theo hệ thống FDI (1-32)
    tooth_condition VARCHAR(50), -- Trạng thái răng
    notes TEXT,
    updated_by INT, -- User ID của bác sĩ cập nhật
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (chart_id) REFERENCES teeth_chart(chart_id) ON DELETE CASCADE,
    FOREIGN KEY (updated_by) REFERENCES users(user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng bệnh án (medical_records)
CREATE TABLE medical_records (
    record_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT NOT NULL,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    chief_complaint TEXT, -- Lý do khám
    diagnosis TEXT,
    treatment_notes TEXT,
    recommendations TEXT,
    follow_up_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng kế hoạch điều trị (treatment_plans)
CREATE TABLE treatment_plans (
    plan_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    plan_name VARCHAR(100) NOT NULL,
    description TEXT,
    start_date DATE,
    estimated_end_date DATE,
    status ENUM('Đã lên kế hoạch', 'Đang thực hiện', 'Hoàn thành', 'Hủy bỏ') DEFAULT 'Đã lên kế hoạch',
    total_cost DECIMAL(12, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng chi tiết kế hoạch điều trị (treatment_plan_details)
CREATE TABLE treatment_plan_details (
    plan_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    plan_id INT NOT NULL,
    service_id INT NOT NULL,
    tooth_number VARCHAR(50), -- Có thể là nhiều răng (e.g., "18,17,16")
    notes TEXT,
    sequence_order INT, -- Thứ tự điều trị
    status ENUM('Chưa thực hiện', 'Đã thực hiện', 'Hủy bỏ') DEFAULT 'Chưa thực hiện',
    appointment_id INT, -- Lịch hẹn thực hiện
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (plan_id) REFERENCES treatment_plans(plan_id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE RESTRICT,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng thuốc (medications) - Có thể đồng bộ với API thuốc
CREATE TABLE medications (
    medication_id INT AUTO_INCREMENT PRIMARY KEY,
    medication_code VARCHAR(50), -- Mã thuốc theo tiêu chuẩn quốc tế
    medication_name VARCHAR(100) NOT NULL,
    generic_name VARCHAR(100),
    form VARCHAR(50), -- Dạng thuốc (viên, ống, ...)
    strength VARCHAR(50), -- Hàm lượng
    manufacturer VARCHAR(100),
    description TEXT,
    usage_instructions TEXT,
    contraindications TEXT,
    side_effects TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng kê thuốc (prescriptions)
CREATE TABLE prescriptions (
    prescription_id INT AUTO_INCREMENT PRIMARY KEY,
    record_id INT NOT NULL,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    prescription_date DATE NOT NULL,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (record_id) REFERENCES medical_records(record_id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng chi tiết kê thuốc (prescription_details)
CREATE TABLE prescription_details (
    prescription_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    prescription_id INT NOT NULL,
    medication_id INT NOT NULL,
    dosage VARCHAR(50) NOT NULL,
    frequency VARCHAR(50) NOT NULL,
    duration VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    instructions TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id) ON DELETE CASCADE,
    FOREIGN KEY (medication_id) REFERENCES medications(medication_id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng phương thức thanh toán (payment_methods)
CREATE TABLE payment_methods (
    method_id INT AUTO_INCREMENT PRIMARY KEY,
    method_name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng hóa đơn (invoices)
CREATE TABLE invoices (
    invoice_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    appointment_id INT,
    doctor_id INT,
    total_amount DECIMAL(12, 2) NOT NULL,
    discount_amount DECIMAL(12, 2) DEFAULT 0,
    tax_amount DECIMAL(12, 2) DEFAULT 0,
    final_amount DECIMAL(12, 2) NOT NULL,
    invoice_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    due_date DATE,
    status ENUM('Chưa thanh toán', 'Đã thanh toán một phần', 'Đã thanh toán', 'Hủy bỏ') DEFAULT 'Chưa thanh toán',
    notes TEXT,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id) ON DELETE SET NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE SET NULL,
    FOREIGN KEY (created_by) REFERENCES users(user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng chi tiết hóa đơn (invoice_items)
CREATE TABLE invoice_items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    invoice_id INT NOT NULL,
    service_id INT,
    package_id INT,
    description VARCHAR(255) NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    unit_price DECIMAL(12, 2) NOT NULL,
    discount_amount DECIMAL(12, 2) DEFAULT 0,
    subtotal DECIMAL(12, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (invoice_id) REFERENCES invoices(invoice_id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE SET NULL,
    FOREIGN KEY (package_id) REFERENCES service_packages(package_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng thanh toán (payments)
CREATE TABLE payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    invoice_id INT NOT NULL,
    method_id INT NOT NULL,
    amount DECIMAL(12, 2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    transaction_reference VARCHAR(100),
    payment_status ENUM('Thành công', 'Đang xử lý', 'Thất bại') DEFAULT 'Thành công',
    notes TEXT,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (invoice_id) REFERENCES invoices(invoice_id) ON DELETE CASCADE,
    FOREIGN KEY (method_id) REFERENCES payment_methods(method_id) ON DELETE RESTRICT,
    FOREIGN KEY (created_by) REFERENCES users(user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng thông tin bảo hiểm (insurance_info)
CREATE TABLE insurance_info (
    insurance_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    insurance_provider VARCHAR(100) NOT NULL,
    policy_number VARCHAR(50) NOT NULL,
    group_number VARCHAR(50),
    coverage_percentage DECIMAL(5, 2),
    expiry_date DATE,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng yêu cầu bảo hiểm (insurance_claims)
CREATE TABLE insurance_claims (
    claim_id INT AUTO_INCREMENT PRIMARY KEY,
    invoice_id INT NOT NULL,
    insurance_id INT NOT NULL,
    claim_amount DECIMAL(12, 2) NOT NULL,
    submitted_date DATE NOT NULL,
    claim_status ENUM('Đã gửi', 'Đang xử lý', 'Đã thanh toán', 'Từ chối', 'Cần bổ sung') DEFAULT 'Đã gửi',
    response_date DATE,
    paid_amount DECIMAL(12, 2),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (invoice_id) REFERENCES invoices(invoice_id) ON DELETE CASCADE,
    FOREIGN KEY (insurance_id) REFERENCES insurance_info(insurance_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng hình ảnh y tế (medical_images)
CREATE TABLE medical_images (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    record_id INT,
    image_type ENUM('X-quang', 'Ảnh trước điều trị', 'Ảnh sau điều trị', 'Khác'),
    image_path VARCHAR(255) NOT NULL,
    description TEXT,
    taken_date DATE NOT NULL,
    uploaded_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (record_id) REFERENCES medical_records(record_id) ON DELETE SET NULL,
    FOREIGN KEY (uploaded_by) REFERENCES users(user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng đánh giá của bệnh nhân (patient_reviews)
CREATE TABLE patient_reviews (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT,
    appointment_id INT,
    rating TINYINT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    review_text TEXT,
    is_anonymous BOOLEAN DEFAULT FALSE,
    is_approved BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE SET NULL,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng thông báo (notifications)
CREATE TABLE notifications (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    notification_type VARCHAR(50),
    reference_id INT, -- ID tham chiếu (có thể là appointment_id, invoice_id, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng bài viết blog (blog_posts)
CREATE TABLE blog_posts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    excerpt TEXT,
    featured_image VARCHAR(255),
    author_id INT NOT NULL,
    status ENUM('Nháp', 'Công khai', 'Ẩn') DEFAULT 'Nháp',
    published_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng danh mục blog (blog_categories)
CREATE TABLE blog_categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng phân loại bài viết blog (post_categories)
CREATE TABLE post_categories (
    post_category_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    category_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES blog_posts(post_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES blog_categories(category_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng bình luận blog (blog_comments)
CREATE TABLE blog_comments (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    user_id INT,
    parent_comment_id INT,
    author_name VARCHAR(100),
    author_email VARCHAR(100),
    content TEXT NOT NULL,
    is_approved BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES blog_posts(post_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL,
    FOREIGN KEY (parent_comment_id) REFERENCES blog_comments(comment_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng tin nhắn từ chatbot (chatbot_messages)
CREATE TABLE chatbot_messages (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    session_id VARCHAR(100) NOT NULL,
    user_input TEXT,
    bot_response TEXT,
    user_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng lịch sử thao tác hệ thống (system_logs)
CREATE TABLE system_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    action VARCHAR(255) NOT NULL,
    entity_type VARCHAR(50), -- Loại đối tượng (user, patient, appointment, ...)
    entity_id INT, -- ID của đối tượng
    description TEXT,
    ip_address VARCHAR(50),
    user_agent TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Thêm dữ liệu mẫu cho bảng vai trò
INSERT INTO roles (role_name, description) VALUES 
('Admin', 'Quản trị viên hệ thống'),
('Doctor', 'Bác sĩ nha khoa'),
('Receptionist', 'Nhân viên lễ tân'),
('Patient', 'Bệnh nhân');

-- Thêm dữ liệu mẫu cho bảng trạng thái lịch hẹn
INSERT INTO appointment_statuses (status_name, description, color_code) VALUES 
('Đã đặt', 'Lịch hẹn đã được đặt thành công', '#3498db'),
('Đã xác nhận', 'Lịch hẹn đã được xác nhận', '#2ecc71'),
('Đang thực hiện', 'Bệnh nhân đang được khám', '#f39c12'),
('Hoàn thành', 'Lịch hẹn đã hoàn thành', '#27ae60'),
('Đã hủy', 'Lịch hẹn đã bị hủy', '#e74c3c'),
('Vắng mặt', 'Bệnh nhân không đến khám theo lịch hẹn', '#95a5a6');

-- Thêm dữ liệu mẫu cho bảng phương thức thanh toán
INSERT INTO payment_methods (method_name, description) VALUES 
('Tiền mặt', 'Thanh toán bằng tiền mặt tại quầy'),
('Thẻ tín dụng/ghi nợ', 'Thanh toán bằng thẻ tín dụng hoặc thẻ ghi nợ'),
('Chuyển khoản ngân hàng', 'Thanh toán bằng chuyển khoản ngân hàng'),
('Ví điện tử', 'Thanh toán qua ví điện tử (VNPay, MoMo, ZaloPay, ...)');

-- Tạo khóa ngoại và ràng buộc bổ sung
ALTER TABLE appointments ADD CONSTRAINT check_appointment_times CHECK (end_time > start_time);
ALTER TABLE doctor_schedules ADD CONSTRAINT check_schedule_times CHECK (end_time > start_time);
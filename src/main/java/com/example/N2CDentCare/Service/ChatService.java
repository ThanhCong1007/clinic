package com.example.N2CDentCare.Service;

import com.example.N2CDentCare.model.Appointment;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ChatService {
    @Autowired
    private AppointmentService appointmentService;

    @Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${clinic.opening.hours:08:00-18:00}")
    private String clinicHours;

    @Value("${clinic.weekend.hours:09:00-16:00}")
    private String weekendHours;

    private final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    // Danh sách các dịch vụ phổ biến của phòng khám
    private final Map<String, String> dentalServices = Map.of(
            "trám răng", "Dịch vụ trám răng thẩm mỹ tại N2CDentCare sử dụng vật liệu cao cấp, đảm bảo độ bền và tính thẩm mỹ cao.",
            "nhổ răng", "Dịch vụ nhổ răng tại N2CDentCare được thực hiện với gây tê hiện đại, giảm đau tối đa.",
            "tẩy trắng", "Dịch vụ tẩy trắng răng tại N2CDentCare giúp răng trắng sáng tự nhiên sau 1 lần điều trị.",
            "cấy ghép implant", "Dịch vụ cấy ghép Implant tại N2CDentCare sử dụng công nghệ tiên tiến với tỷ lệ thành công cao.",
            "niềng răng", "Dịch vụ niềng răng tại N2CDentCare có nhiều lựa chọn từ mắc cài kim loại đến niềng trong suốt.",
            "chữa tủy", "Dịch vụ điều trị tủy tại N2CDentCare sử dụng công nghệ hiện đại, giảm thiểu đau đớn tối đa."
    );

    // Danh sách các câu hỏi thường gặp
    private final Map<String, String> faqs = Map.of(
            "giá", "Bảng giá dịch vụ của N2CDentCare thay đổi tùy theo từng trường hợp cụ thể. Vui lòng đặt lịch khám để được báo giá chính xác.",
            "bảo hiểm", "N2CDentCare có liên kết với nhiều bảo hiểm nha khoa. Vui lòng mang theo thẻ bảo hiểm khi đến khám.",
            "địa chỉ", "N2CDentCare tọa lạc tại 123 Đường Nguyễn Văn A, Quận B, TP.HCM. Phòng khám mở cửa từ 8h-18h các ngày trong tuần và 9h-16h vào cuối tuần.",
            "đau", "Tại N2CDentCare, chúng tôi sử dụng các phương pháp giảm đau tiên tiến nhất. Hầu hết bệnh nhân đều cảm thấy thoải mái trong quá trình điều trị."
    );
        private final Map<String, String> painReliefSolutions = Map.of(
                "đau răng", "Để giảm đau răng tạm thời: 1) Súc miệng bằng nước muối ấm (1/2 muỗng cà phê muối hòa vào 1 cốc nước ấm). 2) Dùng gạc sạch đắp viên đá nhỏ lên vùng má bị đau (10-15 phút). 3) Dùng thuốc giảm đau không kê đơn như Paracetamol theo hướng dẫn. Quan trọng: Đây chỉ là giải pháp tạm thời, bạn nên đặt lịch sớm để điều trị tận gốc.",

                "nhức răng", "Giải pháp tạm thời cho răng nhức: 1) Thoa tinh dầu đinh hương lên vùng đau. 2) Tránh thức ăn quá nóng, lạnh hoặc ngọt. 3) Có thể dùng thuốc giảm đau không kê đơn như Ibuprofen theo chỉ dẫn. Vui lòng đặt lịch khám để bác sĩ có thể điều trị nguyên nhân gây đau.",

                "sâu răng", "Để giảm khó chịu do sâu răng tạm thời: 1) Tránh thức ăn cứng, ngọt, nóng hoặc lạnh. 2) Súc miệng bằng nước muối ấm. 3) Trong một số trường hợp, bạn có thể sử dụng kem đánh răng đặc biệt cho răng nhạy cảm. Bạn nên đặt lịch khám càng sớm càng tốt vì sâu răng cần được điều trị chuyên nghiệp.",

                "viêm lợi", "Giải pháp tạm thời cho viêm lợi: 1) Súc miệng bằng nước muối ấm 3-4 lần/ngày. 2) Đánh răng nhẹ nhàng bằng bàn chải lông mềm. 3) Sử dụng nước súc miệng kháng khuẩn không cồn. Hãy đặt lịch khám để bác sĩ có thể làm sạch cao răng và điều trị viêm lợi hiệu quả.",

                "nhạy cảm", "Đối với răng nhạy cảm: 1) Sử dụng kem đánh răng chuyên dụng cho răng nhạy cảm. 2) Tránh thức ăn quá nóng, lạnh, chua hoặc ngọt. 3) Đánh răng nhẹ nhàng bằng bàn chải lông mềm. Vui lòng đặt lịch khám để bác sĩ xác định nguyên nhân chính xác và đưa ra phương pháp điều trị phù hợp.",

                "chảy máu", "Khi lợi chảy máu: 1) Súc miệng bằng nước muối ấm. 2) Đặt gạc sạch lên vùng chảy máu và cắn nhẹ khoảng 10-15 phút. 3) Tránh đánh răng mạnh hoặc dùng tăm xỉa răng. Việc lợi chảy máu thường là dấu hiệu của viêm lợi hoặc bệnh nha chu, hãy đặt lịch khám càng sớm càng tốt.",

                "mọc răng khôn", "Đối với đau do răng khôn: 1) Súc miệng bằng nước muối ấm. 2) Dùng đá lạnh đắp ngoài má khoảng 15-20 phút. 3) Dùng thuốc giảm đau không kê đơn như Paracetamol theo chỉ dẫn. Nếu đau nhiều hoặc kèm sưng, hãy đặt lịch khám ngay để bác sĩ kiểm tra và tư vấn có nên nhổ răng khôn không."
        );
        // Regex patterns
        private final Pattern datePattern = Pattern.compile("(\\d{1,2})[/-](\\d{1,2})(?:[/-](\\d{2,4}))?");
        private final Pattern timePattern = Pattern.compile("(\\d{1,2})(?:[:h])(\\d{2})?");

        public String chatWithAI(String userMessage, HttpSession session) {
            // Đảm bảo session mới không còn lưu trạng thái appointment cũ
            if (session.isNew()) {
                session.removeAttribute("appointmentState");
                session.removeAttribute("appointmentDate");
                session.removeAttribute("appointmentTime");
                session.removeAttribute("appointmentService");
                session.removeAttribute("appointmentPhone");
                session.removeAttribute("appointmentDebug");
                session.removeAttribute("hasGreeted");
            }

            // Kiểm tra xem có thể xử lý nhanh không trước khi gọi AI
            String quickResponse = handleQuickResponses(userMessage, session);
            if (quickResponse != null) {
                return quickResponse;
            }

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("Content-Type", "application/json");

            // Lấy lịch sử hội thoại từ session
            List<Map<String, String>> messages = (List<Map<String, String>>) session.getAttribute("chatHistory");
            if (messages == null) {
                messages = new ArrayList<>();

                // Thêm tin nhắn hệ thống
                Map<String, String> systemMessage = new HashMap<>();
                systemMessage.put("role", "system");
                systemMessage.put("content", "Bạn là nhân viên tư vấn nha khoa của phòng khám N2CDentCare. "
                        + "Trả lời ngắn gọn bằng tiếng Việt, tập trung vào vấn đề. "
                        + "Nên trả lời trong 2-3 câu ngắn gọn. "
                        + "Bạn chỉ đưa ra lời khuyên theo liệu trình của phòng khám. "
                        + "Nếu bệnh nhân bị sâu răng, hãy khuyến nghị quy trình trám răng của phòng khám. "
                        + "Nếu bệnh nhân bị đau răng do viêm lợi, hãy hướng dẫn cách xử lý và nhắc đặt lịch hẹn. "
                        + "Không đưa ra lời khuyên chung chung, chỉ tư vấn theo dịch vụ của phòng khám."
                        + "Nếu khách hàng có triệu chứng cụ thể, hãy hướng dẫn theo phác đồ điều trị. "
                        + "Không chẩn đoán, không kê đơn, luôn khuyến khích khách hàng đến phòng khám để được kiểm tra chính xác."
                        + "Nếu không chắc chắn về câu trả lời, hãy yêu cầu khách đặt lịch khám."
                        + "Phòng khám mở cửa từ 8h-18h các ngày trong tuần và 9h-16h vào cuối tuần."
                        + "Phòng khám cung cấp các dịch vụ: trám răng, nhổ răng, tẩy trắng, cấy ghép implant, niềng răng, chữa tủy, khám tổng quát, cạo vôi."
                        + "Luôn trả lời bằng tiếng Việt dù người dùng nhắn gì.");
                messages.add(systemMessage);

                session.setAttribute("chatHistory", messages);
            }

            // Kiểm tra nếu đây là tin nhắn đầu tiên -> Chào hỏi
            Boolean hasGreeted = (Boolean) session.getAttribute("hasGreeted");
            if (hasGreeted == null || !hasGreeted) {
                session.setAttribute("hasGreeted", true);
            }

            // Thêm tin nhắn của người dùng vào lịch sử
            Map<String, String> userMessageMap = new HashMap<>();
            userMessageMap.put("role", "user");
            userMessageMap.put("content", userMessage);
            messages.add(userMessageMap);

            // Tạo request body
            Map<String, Object> body = new HashMap<>();
            body.put("model", "deepseek/deepseek-r1-distill-qwen-32b:free");
            body.put("messages", messages);

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            try {
                ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, Map.class);
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");

                if (choices != null && !choices.isEmpty()) {
                    String aiResponse = (String) ((Map<String, Object>) choices.get(0).get("message")).get("content");

                    // Kiểm tra nếu bot đã nhận diện đặt lịch nhưng chưa hoàn thiện
                    if (aiResponse.toLowerCase().contains("đặt lịch") || aiResponse.toLowerCase().contains("hẹn khám")) {
                        aiResponse = enhanceAppointmentResponse(aiResponse, userMessage, session);
                    }

                    // Lưu tin nhắn AI vào lịch sử hội thoại
                    Map<String, String> aiMessageMap = new HashMap<>();
                    aiMessageMap.put("role", "assistant");
                    aiMessageMap.put("content", aiResponse);
                    messages.add(aiMessageMap);

                    session.setAttribute("chatHistory", messages);
                    return aiResponse;
                } else {
                    return "Xin lỗi, tôi không thể phản hồi lúc này.";
                }
            } catch (Exception e) {
                return "Lỗi khi gọi AI: " + e.getMessage();
            }
        }

        // Xử lý các phản hồi nhanh không cần gọi AI
        private String handleQuickResponses(String userMessage, HttpSession session) {
            String lowerMessage = userMessage.toLowerCase();
    // Thêm debug log
    String debug = (String) session.getAttribute("appointmentDebug");
    if (debug == null) {
        debug = "";
    }
    debug += " | handleQuickResponses called with message: " + lowerMessage;
    session.setAttribute("appointmentDebug", debug);

            // Kiểm tra xem có phải là yêu cầu giải pháp giảm đau tạm thời không
            if (lowerMessage.contains("đau") || lowerMessage.contains("nhức") ||
                    lowerMessage.contains("sâu răng") || lowerMessage.contains("viêm lợi") ||
                    lowerMessage.contains("chảy máu") || lowerMessage.contains("răng khôn") ||
                    lowerMessage.contains("nhạy cảm") || lowerMessage.contains("khó chịu") ||
                    lowerMessage.contains("buốt")) {

                String painReliefResponse = handlePainReliefRequest(userMessage, session);
                if (painReliefResponse != null) {
            debug += " | Pain relief response returned";
            session.setAttribute("appointmentDebug", debug);
            return painReliefResponse;
        }
            }

            // Kiểm tra trạng thái đặt lịch hẹn
                String appointmentState = (String) session.getAttribute("appointmentState");
    debug += " | Current appointmentState: " + appointmentState;
    session.setAttribute("appointmentDebug", debug);

            // Kiểm tra tin nhắn đầu tiên, nếu là tin nhắn đầu tiên và appointmentState không null
             Boolean hasGreeted = (Boolean) session.getAttribute("hasGreeted");
    if ((hasGreeted == null || !hasGreeted) && appointmentState != null) {
        debug += " | Reset appointmentState on first message";
        session.setAttribute("appointmentDebug", debug);
        session.removeAttribute("appointmentState");
        appointmentState = null;
    }

            // Kiểm tra hủy quy trình đặt lịch nếu người dùng muốn hỏi thông tin khác
            if (appointmentState != null &&
                    (lowerMessage.contains("tư vấn") ||
                            lowerMessage.contains("hỏi về") ||
                            lowerMessage.contains("bệnh") ||
                            lowerMessage.contains("đau răng") ||lowerMessage.contains("không ") ||
                            lowerMessage.contains("nhức răng") ||
                            lowerMessage.contains("sâu răng") ||
                            lowerMessage.contains("lợi") ||
                            lowerMessage.contains("hỏi thêm"))) {
                // Lưu trạng thái đặt lịch hiện tại để có thể tiếp tục sau
                session.setAttribute("pausedAppointmentState", appointmentState);
                session.setAttribute("appointmentState", null);
                return "Bạn cần hỏi gì về tình trạng răng?";
            }

            if (appointmentState != null) {
                // Kiểm tra xem người dùng muốn tiếp tục đặt lịch không
                if (lowerMessage.contains("tiếp tục đặt lịch") ||
                        lowerMessage.contains("quay lại đặt lịch") ||
                        lowerMessage.contains("tiếp tục lịch") ||
                        lowerMessage.contains("đặt lịch tiếp")) {

                    // Nếu đã tạm dừng trước đó, thì lấy lại trạng thái
                    String pausedState = (String) session.getAttribute("pausedAppointmentState");
                    if (pausedState != null) {
                        session.setAttribute("appointmentState", pausedState);
                        session.removeAttribute("pausedAppointmentState");

                        // Hiển thị thông báo tương ứng với trạng thái đặt lịch
                        switch (pausedState) {
                            case "askDate":
                                return "Chúng ta tiếp tục đặt lịch. Vui lòng cho tôi biết ngày bạn muốn đặt lịch hẹn? (Ví dụ: 15/04 hoặc 15/04/2025)";
                            case "askTime":
                                return "Chúng ta tiếp tục đặt lịch. Vui lòng chọn giờ bạn muốn đặt lịch?";
                            case "askService":
                                return "Chúng ta tiếp tục đặt lịch. Bạn cần đặt lịch cho dịch vụ nào?";
                            case "askPhone":
                                return "Chúng ta tiếp tục đặt lịch. Vui lòng cung cấp số điện thoại của bạn.";
                            case "confirm":
                                return "Chúng ta tiếp tục đặt lịch. Vui lòng xác nhận lịch hẹn.";
                            default:
                                return startAppointmentFlow(session);
                        }
                    }
                }

                return handleAppointmentFlow(lowerMessage, appointmentState, session);
            }

            // Kiểm tra xem người dùng muốn tiếp tục đặt lịch đã tạm dừng không
            String pausedState = (String) session.getAttribute("pausedAppointmentState");
            if (pausedState != null &&
                    (lowerMessage.contains("tiếp tục đặt lịch") ||
                            lowerMessage.contains("quay lại đặt lịch") ||
                            lowerMessage.contains("tiếp tục lịch") ||
                            lowerMessage.contains("đặt lịch tiếp"))) {

                session.setAttribute("appointmentState", pausedState);
                session.removeAttribute("pausedAppointmentState");

                // Hiển thị thông báo tương ứng với trạng thái đặt lịch
                switch (pausedState) {
                    case "askDate":
                        return "Chúng ta tiếp tục đặt lịch. Vui lòng cho tôi biết ngày bạn muốn đặt lịch hẹn? (Ví dụ: 15/04 hoặc 15/04/2025)";
                    case "askTime":
                        return "Chúng ta tiếp tục đặt lịch. Vui lòng chọn giờ bạn muốn đặt lịch?";
                    case "askService":
                        return "Chúng ta tiếp tục đặt lịch. Bạn cần đặt lịch cho dịch vụ nào?";
                    case "askPhone":
                        return "Chúng ta tiếp tục đặt lịch. Vui lòng cung cấp số điện thoại của bạn.";
                    case "confirm":
                        return "Chúng ta tiếp tục đặt lịch. Vui lòng xác nhận lịch hẹn.";
                    default:
                        return startAppointmentFlow(session);
                }
            }

            // Kiểm tra các từ khóa dịch vụ
            for (Map.Entry<String, String> service : dentalServices.entrySet()) {
                if (lowerMessage.contains(service.getKey())) {
                    String response = service.getValue() + " Bạn có muốn đặt lịch tư vấn chi tiết không?";
                    suggestAppointment(session);
                    return response;
                }
            }

            // Kiểm tra các từ khóa FAQ
            for (Map.Entry<String, String> faq : faqs.entrySet()) {
                if (lowerMessage.contains(faq.getKey())) {
                    return faq.getValue();
                }
            }

            // Kiểm tra xem có phải là yêu cầu đặt lịch không
            if (lowerMessage.contains("đặt lịch") || lowerMessage.contains("hẹn khám") ||
                    lowerMessage.contains("khám răng") || lowerMessage.contains("lịch hẹn")) {
                // Thêm bước xác nhận trước khi bắt đầu đặt lịch
                if (lowerMessage.contains("muốn đặt lịch") ||
                        lowerMessage.contains("đặt lịch") ||lowerMessage.contains(" hẹn") ||
                        lowerMessage.contains("đặt hẹn")) {
                    return startAppointmentFlow(session);
                } else {
                    // Nếu chỉ nhắc đến đặt lịch, hỏi xác nhận
                    session.setAttribute("suggestingAppointment", true);
                    return "Bạn muốn đặt lịch hẹn khám răng hoặc cần tư vấn về tình trạng răng trước? Nếu cần đặt lịch ngay, vui lòng 'đặt lịch'. Nếu cần tư vấn trước, vui lòng mô tả tình trạng răng của bạn.";
                }
            }

            // Kiểm tra nếu đã gợi ý đặt lịch trước đó
            Boolean suggestingAppointment = (Boolean) session.getAttribute("suggestingAppointment");
    if (suggestingAppointment != null && suggestingAppointment) {
        debug += " | User was suggested an appointment";
        
        if (lowerMessage.contains("có") || lowerMessage.contains("đồng ý") || 
            lowerMessage.contains("ok") || lowerMessage.contains("đặt lịch") ||
            lowerMessage.contains("muốn đặt")) {
            
            debug += " | User agreed to appointment";
            session.setAttribute("appointmentDebug", debug);
            session.removeAttribute("suggestingAppointment");
            return startAppointmentFlow(session);
        } else if (lowerMessage.contains("không") || lowerMessage.contains("chưa") || 
                   lowerMessage.contains("tư vấn")) {
            
            debug += " | User declined appointment";
            session.setAttribute("appointmentDebug", debug);
            session.removeAttribute("suggestingAppointment");
            return "Vui lòng mô tả tình trạng răng của bạn để tôi có thể tư vấn phù hợp nhất.";
        }
    }

            // Xử lý cảm ơn
            if (lowerMessage.contains("cảm ơn") || lowerMessage.contains("thank")) {
                return "Rất vui được hỗ trợ bạn! Nếu còn thắc mắc gì khác về dịch vụ nha khoa của N2CDentCare, đừng ngần ngại hỏi tôi nhé.";
            }

            return null; // Không có phản hồi nhanh -> chuyển đến xử lý AI
        }

        // Thêm phương thức xử lý yêu cầu giải pháp giảm đau tạm thời
private String handlePainReliefRequest(String userMessage, HttpSession session) {
    String lowerMessage = userMessage.toLowerCase();

    // Tìm kiếm loại đau trong tin nhắn của người dùng
    for (Map.Entry<String, String> painType : painReliefSolutions.entrySet()) {
        if (lowerMessage.contains(painType.getKey())) {
            // Ghi lại log để debug
            String debug = (String) session.getAttribute("appointmentDebug");
            if (debug == null) {
                debug = "";
            }
            debug += " | Pain relief suggestion triggered: " + painType.getKey();
            session.setAttribute("appointmentDebug", debug);
            
            // Sử dụng suggestingAppointment thay vì suggestAppointment để nhất quán với phần còn lại của code
            session.setAttribute("suggestingAppointment", true);
            
            return painType.getValue() + "\n\nBạn có muốn đặt lịch khám ngay không?";
        }
    }

    // Nếu không tìm thấy loại đau cụ thể nhưng người dùng đề cập đến "đau"
    if (lowerMessage.contains("đau") || lowerMessage.contains("nhức") ||
            lowerMessage.contains("khó chịu") || lowerMessage.contains("buốt")) {
        
        // Ghi lại log để debug
        String debug = (String) session.getAttribute("appointmentDebug");
        if (debug == null) {
            debug = "";
        }
        debug += " | Generic pain relief suggestion triggered";
        session.setAttribute("appointmentDebug", debug);
        
        // Sử dụng suggestingAppointment thay vì suggestAppointment
        session.setAttribute("suggestingAppointment", true);
        
        return "Tôi rất tiếc khi nghe bạn đang gặp vấn đề về răng. Để giảm đau tạm thời, bạn có thể:\n\n" +
                "1) Súc miệng với nước muối ấm (1/2 muỗng cà phê muối trong 1 cốc nước ấm)\n" +
                "2) Dùng đá lạnh đắp ngoài má (dùng khăn bọc, đắp 10-15 phút)\n" +
                "3) Dùng thuốc giảm đau không kê đơn như Paracetamol theo chỉ dẫn\n\n" +
                "Những biện pháp này chỉ giúp giảm đau tạm thời. Để điều trị tận gốc, bạn nên đặt lịch khám càng sớm càng tốt. Bạn có muốn đặt lịch ngay không?";
    }

    return null; // Không phải yêu cầu về giảm đau
}
        // Bắt đầu quy trình đặt lịch
        private String startAppointmentFlow(HttpSession session) {
            // Đặt trạng thái và trả về thông báo yêu cầu ngày
            session.setAttribute("appointmentState", "askDate");
            // Thêm ghi nhận để debug
            session.setAttribute("appointmentDebug", "startAppointmentFlow was called");
            return "Vui lòng cho tôi biết ngày bạn muốn đặt lịch hẹn? (Ví dụ: 15/04 hoặc 15/04/2025)";
        }

        // Xử lý quy trình đặt lịch theo trạng thái
        private String handleAppointmentFlow(String userMessage, String state, HttpSession session) {
            // Thêm log để debug
            String debug = (String) session.getAttribute("appointmentDebug");
            if (debug == null) {
                debug = "";
            }
            debug += " | Processing state: " + state;
            session.setAttribute("appointmentDebug", debug);

            switch (state) {
                case "askDate":
                    Matcher dateMatcher = datePattern.matcher(userMessage);
                    if (dateMatcher.find()) {
                        int day = Integer.parseInt(dateMatcher.group(1));
                        int month = Integer.parseInt(dateMatcher.group(2));
                        int year = LocalDate.now().getYear();
                        if (dateMatcher.group(3) != null) {
                            year = Integer.parseInt(dateMatcher.group(3));
                            if (year < 100) year += 2000; // Convert 2-digit year
                        }

                        try {
                            LocalDate appointmentDate = LocalDate.of(year, month, day);
                            LocalDate today = LocalDate.now();

                            // Kiểm tra xem ngày đặt lịch có nằm trong quá khứ không
                            if (appointmentDate.isBefore(today)) {
                                return "Không thể đặt lịch hẹn trong quá khứ. Vui lòng chọn ngày từ hôm nay (" +
                                        today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ") trở đi.";
                            }

                            // Kiểm tra xem ngày đặt lịch có cách quá xa không (tùy chọn)
                            if (appointmentDate.isAfter(today.plusMonths(3))) {
                                return "Phòng khám chỉ hỗ trợ đặt lịch trong vòng 3 tháng tới. Vui lòng chọn ngày gần hơn.";
                            }

                            session.setAttribute("appointmentDate", appointmentDate);
                            session.setAttribute("appointmentState", "askTime");

                            // Kiểm tra ngày cuối tuần để thông báo giờ mở cửa phù hợp
                            String hours = appointmentDate.getDayOfWeek().getValue() <= 5 ? clinicHours : weekendHours;
                            return "Vui lòng chọn giờ bạn muốn đặt lịch? Phòng khám mở cửa " + hours + " vào " +
                                    (appointmentDate.getDayOfWeek().getValue() <= 5 ? "ngày này" : "cuối tuần") + ".";
                        } catch (Exception e) {
                            return "Ngày không hợp lệ. Vui lòng nhập lại theo định dạng DD/MM hoặc DD/MM/YYYY.";
                        }
                    } else {
                        // Nếu người dùng đang ở trạng thái askDate nhưng input không phải là ngày
                        // Kiểm tra xem tin nhắn có phải là chỉ thị đặt lịch không, nếu có thì có thể
                        // họ chỉ muốn bắt đầu quy trình đặt lịch, không phải nhập ngày
                        if (userMessage.toLowerCase().contains("đặt lịch") ||
                                userMessage.toLowerCase().contains("hẹn khám") ||
                                userMessage.toLowerCase().contains("khám răng") ||
                                userMessage.toLowerCase().contains("lịch hẹn")) {
                            // Trạng thái vẫn là askDate, chỉ gửi lại hướng dẫn
                            return "Vui lòng cho tôi biết ngày bạn muốn đặt lịch hẹn? (Ví dụ: 15/04 hoặc 15/04/2025)";
                        }

                        // Nếu đây là tin nhắn đầu tiên trong cuộc trò chuyện và trạng thái là askDate
                        // (có thể do session cũ), reset lại quy trình hoàn toàn
                        Boolean hasGreeted = (Boolean) session.getAttribute("hasGreeted");
                        if (hasGreeted == null || !hasGreeted) {
                            session.removeAttribute("appointmentState");
                            session.setAttribute("hasGreeted", true);
                            return "Xin chào! Tôi là trợ lý nha khoa của phòng khám N2CDentCare. Tôi có thể tư vấn về các dịch vụ nha khoa và giúp bạn đặt lịch hẹn. Bạn cần hỗ trợ vấn đề gì?";
                        }

                        return "Tôi không hiểu ngày bạn đã nhập. Vui lòng nhập theo định dạng DD/MM hoặc DD/MM/YYYY (ví dụ: 15/04 hoặc 15/04/2025).";
                    }

                case "askTime":
                    Matcher timeMatcher = timePattern.matcher(userMessage);
                    if (timeMatcher.find()) {
                        int hour = Integer.parseInt(timeMatcher.group(1));
                        int minute = timeMatcher.group(2) != null ? Integer.parseInt(timeMatcher.group(2)) : 0;

                        LocalTime appointmentTime = LocalTime.of(hour, minute);
                        LocalDate appointmentDate = (LocalDate) session.getAttribute("appointmentDate");

                        // Kiểm tra nếu lịch hẹn là hôm nay và thời gian đã qua
                        if (appointmentDate.equals(LocalDate.now()) && appointmentTime.isBefore(LocalTime.now())) {
                            return "Không thể đặt lịch hẹn vào thời gian đã qua trong ngày hôm nay. Vui lòng chọn thời gian khác.";
                        }

                        // Kiểm tra giờ mở cửa
                        String[] hoursRange = appointmentDate.getDayOfWeek().getValue() <= 5 ?
                                clinicHours.split("-") : weekendHours.split("-");
                        LocalTime openTime = LocalTime.parse(hoursRange[0], DateTimeFormatter.ofPattern("HH:mm"));
                        LocalTime closeTime = LocalTime.parse(hoursRange[1], DateTimeFormatter.ofPattern("HH:mm"));

                        if (appointmentTime.isBefore(openTime) || appointmentTime.isAfter(closeTime)) {
                            return "Giờ hẹn nằm ngoài giờ làm việc của phòng khám. Vui lòng chọn giờ khác trong khoảng " +
                                    (appointmentDate.getDayOfWeek().getValue() <= 5 ? clinicHours : weekendHours) + ".";
                        }

                        session.setAttribute("appointmentTime", appointmentTime);
                        session.setAttribute("appointmentState", "askService");
                        return "Bạn cần đặt lịch cho dịch vụ nào? (Ví dụ: khám tổng quát, trám răng, nhổ răng, niềng răng,tái khám, cạo vôi r...)";
                    } else {
                        return "Tôi không hiểu giờ bạn đã nhập. Vui lòng nhập theo định dạng HH:MM (ví dụ: 9:30 hoặc 14:00).";
                    }

                case "askService":
                    for (String service : dentalServices.keySet()) {
                        if (userMessage.toLowerCase().contains(service)) {
                            session.setAttribute("appointmentService", service);
                            session.setAttribute("appointmentState", "askPhone");

                            return "Vui lòng cung cấp số điện thoại của bạn để chúng tôi liên hệ xác nhận lịch hẹn.";
                        }
                    }
                    return "Dịch vụ không hợp lệ. Vui lòng chọn một trong các dịch vụ: trám răng, nhổ răng, tẩy trắng, cấy ghép implant, niềng răng, chữa tủy.";
                case "askSymptoms":
                    // Lưu triệu chứng người dùng mô tả
                    session.setAttribute("appointmentSymptoms", userMessage);
                    session.setAttribute("appointmentState", "askPhone");

                    return "Cảm ơn thông tin của bạn. Vui lòng cung cấp số điện thoại để chúng tôi liên hệ xác nhận lịch hẹn.";
                case "askPhone":
                    if (userMessage.matches("\\d{10,11}")) {
                        session.setAttribute("appointmentPhone", userMessage);
                        session.setAttribute("appointmentState", "confirm");

                        return "Bạn có muốn xác nhận lịch hẹn sau đây không?\n"
                                + "Ngày: " + session.getAttribute("appointmentDate") + "\n"
                                + " lúc: " + session.getAttribute("appointmentTime") +" giờ." + "\n"
                                + "sử dụng dịch vụ: " + session.getAttribute("appointmentService") + "\n"
                                + "với SĐT: " + userMessage + "\n"
                                + "Vui lòng trả lời 'Xác nhận' để hoàn tất đặt lịch.";
                    } else {
                        return "Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại gồm 10 hoặc 11 chữ số.";
                    }

                case "confirm":
                    if (userMessage.equalsIgnoreCase("xác nhận")) {
                        // Tạo đối tượng lịch hẹn từ session
                        Appointment appointment = new Appointment();
                        appointment.setPatientName("Khách hàng"); // Nếu có tên khách hàng, cập nhật sau
                        appointment.setPhoneNumber((String) session.getAttribute("appointmentPhone"));
                        appointment.setService((String) session.getAttribute("appointmentService"));
                        appointment.setAppointmentDate((LocalDate) session.getAttribute("appointmentDate"));
                        appointment.setAppointmentTime((LocalTime) session.getAttribute("appointmentTime"));
                        appointment.setStatus("PENDING"); // Trạng thái mặc định

                        // Lưu lịch hẹn vào cơ sở dữ liệu
                        appointmentService.saveAppointment(appointment);

                        // Xóa trạng thái cuộc trò chuyện
                        session.removeAttribute("appointmentState");
                        session.removeAttribute("appointmentDate");
                        session.removeAttribute("appointmentTime");
                        session.removeAttribute("appointmentService");
                        session.removeAttribute("appointmentPhone");

                        return " Lịch hẹn của bạn đã được xác nhận! Cảm ơn bạn đã tin tưởng dịch vụ của N2CDentCare. Sẽ có nhân viên liên hệ với bạn 1 cách sớm nhất!";
                    } else {
                        return "Vui lòng trả lời 'Xác nhận' để hoàn tất đặt lịch.";
                    }

                default:
                    return "Có lỗi xảy ra trong quy trình đặt lịch. Vui lòng thử lại.";

            }
        }

        // Gợi ý đặt lịch
private void suggestAppointment(HttpSession session) {
    session.setAttribute("suggestingAppointment", true);
    
    // Thêm debug log
    String debug = (String) session.getAttribute("appointmentDebug");
    if (debug == null) {
        debug = "";
    }
    debug += " | suggestAppointment was called";
    session.setAttribute("appointmentDebug", debug);
}

        // Nâng cao phản hồi về đặt lịch
        private String enhanceAppointmentResponse(String aiResponse, String userMessage, HttpSession session) {
                String lowerMessage = userMessage.toLowerCase();

            // Nếu AI đã gợi ý đặt lịch nhưng chưa bắt đầu quy trình
    String debug = (String) session.getAttribute("appointmentDebug");
    if (debug == null) {
        debug = "";
    }
    debug += " | enhanceAppointmentResponse called";
    session.setAttribute("appointmentDebug", debug);
        // Kiểm tra xem người dùng có đồng ý đặt lịch sau khi nhận tư vấn không
    Boolean suggestingAppointment = (Boolean) session.getAttribute("suggestingAppointment");
    if (suggestingAppointment != null && suggestingAppointment &&
            (lowerMessage.contains("có") || lowerMessage.contains("đồng ý") || 
             lowerMessage.contains("ok") || lowerMessage.contains("đặt lịch") ||
             lowerMessage.contains("muốn đặt"))) {
        
        session.removeAttribute("suggestingAppointment");
        debug += " | User agreed to appointment after suggestion";
        session.setAttribute("appointmentDebug", debug);
        return startAppointmentFlow(session);
    }

            // Nếu người dùng đồng ý đặt lịch
            if (userMessage.toLowerCase().matches(".*(có|đồng ý|ok|chắc chắn|đặt ngay).*") &&
                    session.getAttribute("appointmentState") == null) {

                Boolean suggestAppointment = (Boolean) session.getAttribute("suggestAppointment");
                if (suggestAppointment != null && suggestAppointment) {
                    session.removeAttribute("suggestAppointment");
                    return startAppointmentFlow(session);
                }
            }
            String state = (String) session.getAttribute("appointmentState");
            if ("CONFIRMED".equals(state)) {
                String name = (String) session.getAttribute("appointmentName");
                String phone = (String) session.getAttribute("appointmentPhone");
                String service = (String) session.getAttribute("appointmentService");

                Object dateObj = session.getAttribute("appointmentDate");
                Object timeObj = session.getAttribute("appointmentTime");
                LocalDate date = (dateObj instanceof LocalDate) ? (LocalDate) dateObj : null;
                LocalTime time = (timeObj instanceof LocalTime) ? (LocalTime) timeObj : null;

                String symptoms = (String) session.getAttribute("appointmentSymptoms");

                if (date == null || time == null) {
                    return "Ngày hoặc giờ không hợp lệ, vui lòng thử lại!";
                }

                if (!appointmentService.isTimeSlotAvailable(date, time)) {
                    return "Lịch hẹn vào thời gian này đã đầy. Vui lòng chọn thời gian khác!";
                }

                // Tạo đối tượng Appointment mới
                Appointment appointment = new Appointment();
                appointment.setPatientName(name);
                appointment.setPhoneNumber(phone);
                appointment.setService(service);
                appointment.setAppointmentDate(date);
                appointment.setAppointmentTime(time);
                appointment.setSymptoms(symptoms);
                appointment.setStatus("PENDING");

                // Lưu vào database
                appointmentService.saveAppointment(appointment);

                // Xoá toàn bộ session
                session.invalidate();

                return "Đặt lịch thành công! Chúng tôi sẽ liên hệ với bạn qua số điện thoại " + phone +
                        " để xác nhận lịch hẹn vào ngày " + date + " lúc " + time +
                        " cho dịch vụ " + service + ".";
            }

            return aiResponse;
        }
}
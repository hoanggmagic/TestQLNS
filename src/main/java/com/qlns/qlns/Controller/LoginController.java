package com.qlns.qlns.Controller;

import com.qlns.qlns.Mode.NhanVien;
import com.qlns.qlns.Serevice.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    // 👉 Hiển thị trang đăng nhập
    @GetMapping
    public String loginPage() {
        return "login";
    }

    // 👉 Xử lý đăng nhập
    @PostMapping
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {

        NhanVien nhanVien = loginService.authenticate(username, password);

        if (nhanVien == null) {
            model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu.");
            return "login";
        }

        // ✅ Lưu thông tin nhân viên vào session
        session.setAttribute("loggedInUser", nhanVien);

        // ✅ Điều hướng theo vai trò
        String role = nhanVien.getChucVu().getName().toLowerCase();

        if ("admin".equals(role)) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/dashboard";
        }
    }

    // 👉 Xử lý đăng xuất
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // xoá session
        return "redirect:/login";
    }
}

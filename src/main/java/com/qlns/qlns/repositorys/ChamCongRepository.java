package com.qlns.qlns.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlns.qlns.entitys.*;

public interface ChamCongRepository extends JpaRepository<ChamCong, Long> {
    List<ChamCong> findByNhanVien(NhanVien nhanVien);

}

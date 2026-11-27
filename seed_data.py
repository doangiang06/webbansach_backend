import datetime

EXPORT_SQL_ONLY = True
TOTAL_BOOKS = 30

DB_CONFIG = {
    "host": "127.0.0.1",
    "port": 3306,
    "user": "root",
    "password": "",
    "database": "web_ban_sach",
    "charset": "utf8mb4",
}


def sql_val(v):
    if v is None:
        return "NULL"
    if isinstance(v, bool):
        return "1" if v else "0"
    if isinstance(v, (int, float)):
        return str(v)
    if isinstance(v, (datetime.date, datetime.datetime)):
        return f"'{v.isoformat()}'"
    escaped = str(v).replace("\\", "\\\\").replace("'", "''")
    return f"'{escaped}'"


def build_statements(total_books=30):
    stmts = [
        "SET NAMES utf8mb4;",
        "SET FOREIGN_KEY_CHECKS = 0;",
        "",
    ]

    # 1. quyen
    quyens = [
        (1, "ADMIN"),
        (2, "USER"),
    ]
    for ma_quyen, ten_quyen in quyens:
        stmts.append(
            f"INSERT INTO quyen (ma_quyen, ten_quyen) VALUES ({sql_val(ma_quyen)}, {sql_val(ten_quyen)});"
        )

    # 2. nguoi_dung
    nguoi_dungs = [
        (
            1,
            "Đoàn Trường",
            "Giang",
            "giang06",
            "$2a$10$7EqJtq98hPqEX7fNZaFWoOhi5z4Q0G0Y8l6.0n6Z9j5f8Z5x5y5y",
            "M",
            "giang@example.com",
            "0347326770",
            "Tân Triều, Thanh Trì, Hà Nội",
            "Tân Triều, Thanh Trì, Hà Nội",
        ),
        (
            2,
            "Nguyễn Văn",
            "An",
            "nguyenvanan",
            "$2a$10$7EqJtq98hPqEX7fNZaFWoOhi5z4Q0G0Y8l6.0n6Z9j5f8Z5x5y5y",
            "M",
            "an.nguyen@example.com",
            "0912345678",
            "Hà Đông, Hà Nội",
            "Hà Đông, Hà Nội",
        ),
    ]
    for row in nguoi_dungs:
        stmts.append(
            "INSERT INTO nguoi_dung "
            "(ma_nguoi_dung, ho_dem, ten, ten_dang_nhap, mat_khau, gioi_tinh, email, so_dien_thoai, dia_chi_mua_hang, dia_chi_giao_hang) "
            f"VALUES ({', '.join(sql_val(x) for x in row)});"
        )

    # 3. nguoidung_quyen
    for ma_nd, ma_q in [(1, 1), (1, 2), (2, 2)]:
        stmts.append(
            f"INSERT INTO nguoidung_quyen (ma_nguoi_dung, ma_quyen) VALUES ({sql_val(ma_nd)}, {sql_val(ma_q)});"
        )

    # 4. the_loai (chỉnh tên cột nếu TheLoai.java dùng tên cột khác)
    the_loais = [
        (1, "Công nghệ thông tin"),
        (2, "Kinh tế & Khởi nghiệp"),
        (3, "Kỹ năng sống"),
        (4, "Khoa học & Đời sống"),
    ]
    for ma_tl, ten_tl in the_loais:
        stmts.append(
            f"INSERT INTO the_loai (ma_the_loai, ten_the_loai) VALUES ({sql_val(ma_tl)}, {sql_val(ten_tl)});"
        )

    # 5. hinh_thuc_giao_hang & hinh_thuc_thanh_toan
    stmts.append(
        "INSERT INTO hinh_thuc_giao_hang (ma_hinh_thuc_giao_hang, ten_hinh_thuc_giao_hang, mo_ta, chi_phi_giao_hang) "
        "VALUES (1, 'Giao hàng tiêu chuẩn', 'Giao hàng trong 2-4 ngày làm việc', 30000.0), "
        "(2, 'Giao hàng hỏa tốc', 'Giao nhanh trong nội thành', 50000.0);"
    )
    stmts.append(
        "INSERT INTO hinh_thuc_thanh_toan (ma_hinh_thuc_thanh_toan, ten_hinh_thuc_thanh_toan, mo_ta, chi_phi_thanh_toan) "
        "VALUES (1, 'Thanh toán khi nhận hàng (COD)', 'Thanh toán tiền mặt khi nhận sách', 0.0), "
        "(2, 'Chuyển khoản ngân hàng', 'Thanh toán qua chuyển khoản/QR', 0.0);"
    )

    # 6. Sinh 30 bản ghi sach + hinh_anh + sach_the_loai + su_danh_gia + sach_yeu_thich
    authors = [
        "Craig Walls",
        "Robert C. Martin",
        "Alex Xu",
        "Martin Kleppmann",
        "Joshua Bloch",
        "Đoàn Trường Giang",
    ]
    topics = [
        "Spring Boot & Microservices",
        "Clean Architecture",
        "Hệ thống phân tán",
        "Cơ sở dữ liệu & Indexing",
        "Lập trình Java & Backend",
        "Tư duy giải quyết vấn đề",
    ]

    tong_tien_sp_don_1 = 0.0
    chi_tiet_rows = []

    for i in range(1, total_books + 1):
        topic = topics[(i - 1) % len(topics)]
        author = authors[(i - 1) % len(authors)]
        ma_tl = ((i - 1) % len(the_loais)) + 1

        ten_sach = f"{topic} - Tập {i:02d}"
        ten_tac_gia = author
        isbn = f"978-604-{1000 + i:04d}"
        mo_ta = f"Tài liệu thực hành số {i} về {topic.lower()}, phù hợp sinh viên CNTT và lập trình viên."
        gia_niem_yet = float(150000 + ((i - 1) % 10) * 20000)
        gia_ban = float(gia_niem_yet - 25000)
        so_luong = 10 + (i % 25)
        trung_binh_xep_hang = round(3.8 + ((i % 13) * 0.1), 1)
        if trung_binh_xep_hang > 5.0:
            trung_binh_xep_hang = 5.0

        stmts.append(
            "INSERT INTO sach "
            "(ma_sach, ten_sach, ten_tac_gia, isbn, mo_ta, gia_niem_yet, gia_ban, so_luong, trung_binh_xep_hang) "
            f"VALUES ({sql_val(i)}, {sql_val(ten_sach)}, {sql_val(ten_tac_gia)}, {sql_val(isbn)}, "
            f"{sql_val(mo_ta)}, {sql_val(gia_niem_yet)}, {sql_val(gia_ban)}, {sql_val(so_luong)}, {sql_val(trung_binh_xep_hang)});"
        )

        # Liên kết thể loại
        stmts.append(
            f"INSERT INTO sach_the_loai (ma_sach, ma_the_loai) VALUES ({sql_val(i)}, {sql_val(ma_tl)});"
        )
        if i % 3 == 0:
            stmts.append(
                f"INSERT INTO sach_the_loai (ma_sach, ma_the_loai) VALUES ({sql_val(i)}, 1);"
            )

        # Hình ảnh cho từng sách
        ten_anh = f"sach-{i:02d}.jpg"
        duong_dan = f"/images/books/sach-{i:02d}.jpg"
        stmts.append(
            "INSERT INTO hinh_anh (ma_hinh_anh, ten_hinh_anh, la_icon, duong_dan, du_lieu_anh, ma_sach) "
            f"VALUES ({sql_val(i)}, {sql_val(ten_anh)}, 1, {sql_val(duong_dan)}, NULL, {sql_val(i)});"
        )

        # Đánh giá mẫu cho từng sách
        ma_nd_danh_gia = 1 if i % 2 == 1 else 2
        diem = float(4.0 + (i % 2))
        nhan_xet = f"Nhận xét mẫu #{i}: nội dung rõ ràng, dễ áp dụng."
        stmts.append(
            "INSERT INTO su_danh_gia (ma_danh_gia, diem_xep_hang, nhan_xet, ma_sach, ma_nguoi_dung) "
            f"VALUES ({sql_val(i)}, {sql_val(diem)}, {sql_val(nhan_xet)}, {sql_val(i)}, {sql_val(ma_nd_danh_gia)});"
        )

        # Một số sách yêu thích của người dùng 1
        if i <= 6:
            stmts.append(
                f"INSERT INTO sach_yeu_thich (ma_sach_yeu_thich, ma_nguoi_dung, ma_sach) VALUES ({sql_val(i)}, 1, {sql_val(i)});"
            )

        # Gộp 3 cuốn đầu vào đơn hàng mẫu #1
        if i <= 3:
            so_luong_mua = 1
            tong_tien_sp_don_1 += gia_ban * so_luong_mua
            chi_tiet_rows.append((i, so_luong_mua, gia_ban, i, 1))

    # 7. don_hang & chi_tiet_don_hang
    phi_gh = 30000.0
    phi_tt = 0.0
    tong_don_1 = tong_tien_sp_don_1 + phi_gh + phi_tt

    stmts.append(
        "INSERT INTO don_hang "
        "(ma_don_hang, ngay_tao, dia_chi_mua_hang, dia_chi_nhan_hang, tong_tien_san_pham, chi_phi_giao_hang, chi_phi_thanh_toan, tong_tien, ma_nguoi_dung, ma_hinh_thuc_thanh_toan, ma_hinh_thuc_giao_hang) "
        f"VALUES (1, {sql_val(datetime.date(2026, 11, 27))}, 'Tân Triều, Thanh Trì, Hà Nội', 'Tân Triều, Thanh Trì, Hà Nội', "
        f"{sql_val(tong_tien_sp_don_1)}, {sql_val(phi_gh)}, {sql_val(phi_tt)}, {sql_val(tong_don_1)}, 1, 1, 1);"
    )

    for ct_id, sl, gb, ma_s, ma_dh in chi_tiet_rows:
        stmts.append(
            "INSERT INTO chi_tiet_don_hang (ma_chi_tiet_don_hang, so_luong, gia_ban, ma_sach, ma_don_hang) "
            f"VALUES ({sql_val(ct_id)}, {sql_val(sl)}, {sql_val(gb)}, {sql_val(ma_s)}, {sql_val(ma_dh)});"
        )

    stmts.extend(["", "SET FOREIGN_KEY_CHECKS = 1;"])
    return stmts


def main():
    stmts = build_statements(TOTAL_BOOKS)
    output_file = "seed_web_ban_sach.sql"
    with open(output_file, "w", encoding="utf-8") as f:
        f.write("\n".join(stmts) + "\n")
    print(f"Đã tạo {output_file} với {TOTAL_BOOKS} bản ghi sách.")

    if not EXPORT_SQL_ONLY:
        import mysql.connector

        conn = mysql.connector.connect(**DB_CONFIG)
        cursor = conn.cursor()
        for s in stmts:
            s_strip = s.strip()
            if s_strip and not s_strip.startswith("--"):
                cursor.execute(s_strip)
        conn.commit()
        cursor.close()
        conn.close()
        print("Đã insert thẳng vào MySQL thành công.")


if __name__ == "__main__":
    main()
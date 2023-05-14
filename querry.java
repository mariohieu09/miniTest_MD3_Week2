drop procedure Themvattu;
        use minitest_week2;
        # delimiter //
        # # create procedure Themvattu(
        # in ma_vt int,
        # in ten varchar(20),
        # in dv varchar(20),
        # in gia_VT double
        # )
        # begin
        #     insert into vattu (mvt, ten_VT, dvt, gia) values (ma_vt, ten, dv, gia_VT);
        # end //
        call Themvattu(5, 'bò húc', 'thùng', 15);
        call ThemTonKho(5, 19, 40, 21);
        insert into ncc values (1, 1, 'Handicap','Long Bien', '851930'),
        (2, 2, 'Hanbeco', 'Thuy Si', '321611'),
        (3, 3, 'Han Sara', 'Nhat Ban', '1239419');
        insert into don_dat_hang values (2, 2, '2023-6-11', 2),
        (3,3, '2023-10-12', 3);
        insert into phieunhap values (1, 1, '2024-1-23', 1),
        (2, 2, '2024-2-25', 2),
        (3, 3, '2023-12-24', 3);
        insert into phieuxuat values (1, 1, '2024-1-31', 'ng hai son'),
        (2, 2, '2024-3-1', 'phung kh dung'),
        (3, 3, '2024-1-5', 'Jimmy nguyen');
        insert into chitietdonhang values (1, 1, 1, 5),
        (2,2,2,5),
        (3,3,3,5),
        (4,1,4,5),
        (5,2,3,5),
        (6,3,5,5);
        insert into chitietphieunhap values (1, 1, 1, 20, 30, default),
        (2, 2, 2, 50, 44, default),
        (3, 3, 3, 20, 22, default),
        (4, 3, 4, 45, 94, default),
        (5, 2, 5, 10, 101, default),
        (6, 1, 3, 10, 15, default);
        insert into chitietphieuxuat values (1,1,1, 14, 44, default),
        (2,2,2, 22, 54,default),
        (3,3,3, 10, 29, default),
        (4,3,4,22, 99.2, default),
        (5,2,5, 5, 120, default),
        (6,1,2,20, 54.5, default);
        create view  vw_CTPNHAP as
        select  pn.ma_phieu, vt.mvt, ctpn.sl_nhap, ctpn.don_gia_nhap, sum(ctpn.don_gia_nhap * ctpn.sl_nhap) as thanhtien
        from phieunhap pn join chitietphieunhap ctpn on pn.id_phieu = ctpn.PN_id join vattu vt on ctpn.VT_id = vt.VT_id
        group by pn.ma_phieu, vt.mvt, ctpn.sl_nhap, ctpn.don_gia_nhap
        order by ma_phieu;
        select * from vw_CTPNHAP;
        drop view vw_CTPNHAP;

        create view vw_CTPNHAP_VT as
        select  pn.ma_phieu, vt.mvt, vt.ten_VT, ctpn.sl_nhap, ctpn.don_gia_nhap, sum(ctpn.don_gia_nhap * ctpn.sl_nhap) as thanhtien
        from phieunhap pn join chitietphieunhap ctpn on pn.id_phieu = ctpn.PN_id join vattu vt on ctpn.VT_id = vt.VT_id
        group by pn.ma_phieu, vt.mvt, vt.ten_VT, ctpn.sl_nhap, ctpn.don_gia_nhap
        order by pn.ma_phieu;
        select * from vw_CTPNHAP_VT;
        drop view vw_CTPNHAP_VT;

        create view  vw_CTPNHAP_VT_PN as
        select pn.ma_phieu, pn.ngay_nhap, ctdh.don_hang_id, vt.mvt, vt.ten_VT, ctpn.sl_nhap, ctpn.don_gia_nhap, sum(ctpn.don_gia_nhap * ctpn.sl_nhap) as thanhtien
        from phieunhap pn join chitietphieunhap ctpn on pn.id_phieu = ctpn.PN_id join vattu vt on ctpn.VT_id = vt.VT_id
        join chitietdonhang ctdh on vt.VT_id = ctdh.VT_id
        group by pn.ma_phieu, pn.ngay_nhap, ctdh.don_hang_id, vt.mvt, vt.ten_VT, ctpn.sl_nhap, ctpn.don_gia_nhap
        order by pn.ma_phieu;
        select * from vw_CTPNHAP_VT_PN;
        drop view vw_CTPNHAP_VT_PN;

        create view vw_CTPNHAP_VT_PN_DH as
        select pn.ma_phieu, pn.ngay_nhap, dh.ma_don, cc.ma_NCC, vt.mvt, vt.ten_VT, ctpn.sl_nhap, ctpn.don_gia_nhap, sum(ctpn.don_gia_nhap * ctpn.sl_nhap) as thanhtien
        from phieunhap pn join chitietphieunhap ctpn on pn.id_phieu = ctpn.PN_id
        join don_dat_hang dh on pn.don_hang_id = dh.id join ncc cc on dh.NCC_id = cc.NCC_id
        join vattu vt on ctpn.VT_id = vt.VT_id
        group by pn.ma_phieu, pn.ngay_nhap, dh.ma_don, cc.ma_NCC, vt.mvt, vt.ten_VT, ctpn.sl_nhap, ctpn.don_gia_nhap
        order by pn.ma_phieu;
        select * from vw_CTPNHAP_VT_PN_DH;

        create view vw_CTPNHAP_loc as
        select pn.ma_phieu, vt.mvt, ctpn.sl_nhap, ctpn.don_gia_nhap, sum(ctpn.sl_nhap * ctpn.don_gia_nhap) as thanhtien
        from phieunhap pn join chitietphieunhap ctpn on pn.id_phieu = ctpn.PN_id join vattu vt on ctpn.VT_id = vt.VT_id
        where ctpn.sl_nhap > 10
        group by pn.ma_phieu, vt.mvt, ctpn.sl_nhap, ctpn.don_gia_nhap
        order by pn.ma_phieu;
        select * from vw_CTPNHAP_loc;
        drop view vw_CTPNHAP_loc;

        create view vw_CTPNHAP_VT_loc as
        select pn.ma_phieu, vt.mvt, vt.ten_VT, ctpn.sl_nhap, ctpn.don_gia_nhap, sum(ctpn.sl_nhap * ctpn.don_gia_nhap) as thanhtien
        from phieunhap pn join chitietphieunhap ctpn on pn.id_phieu = ctpn.PN_id
        join vattu vt on ctpn.VT_id = vt.VT_id
        where vt.dvt = 'thùng'
        group by pn.ma_phieu, vt.mvt, vt.ten_VT, ctpn.sl_nhap, ctpn.don_gia_nhap
        order by pn.ma_phieu;
        select * from vw_CTPNHAP_VT_loc;

        create view vw_CTPXUAT as
        select px.ma_phieu, vt.mvt, ctpx.sl_xuat, ctpx.don_gia_xuat, sum(ctpx.sl_xuat * ctpx.don_gia_xuat) as thanhtien
        from phieuxuat px join chitietphieuxuat ctpx on px.id_phieu = ctpx.PX_id
        join vattu vt on ctpx.VT_id = vt.VT_id
        group by px.ma_phieu, vt.mvt, ctpx.sl_xuat, ctpx.don_gia_xuat
        order by px.ma_phieu;
        select * from vw_CTPXUAT;

        create view vw_CTPXUAT_VT as
        select px.ma_phieu, vt.mvt, vt.ten_VT, ctpx.sl_xuat, ctpx.don_gia_xuat
        from phieuxuat px join chitietphieuxuat ctpx on px.id_phieu = ctpx.PX_id
        join vattu vt on ctpx.VT_id = vt.VT_id
        group by px.ma_phieu, vt.mvt, vt.ten_VT, ctpx.sl_xuat, ctpx.don_gia_xuat
        order by px.ma_phieu;
        select * from vw_CTPXUAT_VT;

        create view vw_CTPXUAT_VT_PX as
        select px.ma_phieu, px.tenkhach, vt.mvt, vt.ten_VT, ctpx.sl_xuat, ctpx.don_gia_xuat
        from phieuxuat px join chitietphieuxuat ctpx on px.id_phieu = ctpx.PX_id
        join vattu vt on ctpx.VT_id = vt.VT_id
        group by px.ma_phieu, px.tenkhach, vt.mvt, vt.ten_VT, ctpx.sl_xuat, ctpx.don_gia_xuat
        order by px.ma_phieu;
        select * from vw_CTPXUAT_VT_PX;

        # ###########################################################################################
        delimiter //
        create procedure tongSLCuoi(
        in ten varchar(20)
        )
        begin
        select sum(sl_dau + tong_sl_nhap - tong_sl_xuat) as soluongcuoi, ten_VT
        from tonkho join vattu on tonkho.VT_id = vattu.VT_id
        where ten_VT = ten
        group by ten_VT;
        end //
        delimiter ;
        drop procedure tongSLCuoi;
        call tongSLCuoi('bò húc');

        delimiter //
        create procedure tongTienXuat(
        in id int
        )
        begin
        select sum(don_gia_xuat * sl_xuat) as Tong, VT_id
        from chitietphieuxuat
        where VT_id = id
        group by VT_id;
        end //
        delimiter ;
        drop procedure tongTienXuat;
        call tongTienXuat(1);

        delimiter //
        create procedure tongSLDat(
        in id_don int
        )
        begin
        select sum(sl_dat), don_hang_id
        from chitietdonhang
        where id_don = don_hang_id
        group by don_hang_id;
        end //
        delimiter ;
        call tongSLDat(1);

        delimiter //
        create procedure themDondathang(
        in maDonHang int,
        in ngayDat date,
        in ccid int
        )
        begin
        insert into don_dat_hang(ma_don, ngay_dat, NCC_id) values (maDonHang, ngayDat, ccid);
        end //
        delimiter ;
        call themDondathang(4, '2021-04-8', 3);

        delimiter //
        create procedure themChiTietDonDH(
        in DH_id int,
        in vattu_id int,
        in sl int
        )
        begin
        insert into chitietdonhang (don_hang_id, VT_id, sl_dat) values (DH_id, vattu_id, sl);
        end //
        delimiter ;
        call themChiTietDonDH(4, 2, 14);



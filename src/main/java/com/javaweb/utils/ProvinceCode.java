package com.javaweb.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ProvinceCode {
    AN_GIANG("An Giang"),
    BA_RIA_VUNG_TAU("Ba Ria - Vung Tau"),
    BAC_GIANG("Bac Giang"),
    BAC_KAN("Bac Kan"),
    BAC_LIEU("Bac Lieu"),
    BAC_NINH("Bac Ninh"),
    BEN_TRE("Ben Tre"),
    BINH_DINH("Binh Dinh"),
    BINH_DUONG("Binh Duong"),
    BINH_PHUOC("Binh Phuoc"),
    BINH_THUAN("Binh Thuan"),
    CA_MAU("Ca Mau"),
    CAN_THO("Can Tho"),
    CAO_BANG("Cao Bang"),
    DA_NANG("Da Nang"),
    DAK_LAK("Dak Lak"),
    DAK_NONG("Dak Nong"),
    DIEN_BIEN("Dien Bien"),
    DONG_NAI("Dong Nai"),
    DONG_THAP("Dong Thap"),
    GIA_LAI("Gia Lai"),
    HA_GIANG("Ha Giang"),
    HA_NAM("Ha Nam"),
    HA_NOI("Ha Noi"),
    HA_TINH("Ha Tinh"),
    HAI_DUONG("Hai Duong"),
    HAI_PHONG("Hai Phong"),
    HAU_GIANG("Hau Giang"),
    HOA_BINH("Hoa Binh"),
    HUNG_YEN("Hung Yen"),
    KHANH_HOA("Khanh Hoa"),
    KIEN_GIANG("Kien Giang"),
    KON_TUM("Kon Tum"),
    LAI_CHAU("Lai Chau"),
    LAM_DONG("Lam Dong"),
    LANG_SON("Lang Son"),
    LAO_CAI("Lao Cai"),
    LONG_AN("Long An"),
    NAM_DINH("Nam Dinh"),
    NGHE_AN("Nghe An"),
    NINH_BINH("Ninh Binh"),
    NINH_THUAN("Ninh Thuan"),
    PHU_THO("Phu Tho"),
    PHU_YEN("Phu Yen"),
    QUANG_BINH("Quang Binh"),
    QUANG_NAM("Quang Nam"),
    QUANG_NGAI("Quang Ngai"),
    QUANG_NINH("Quang Ninh"),
    QUANG_TRI("Quang Tri"),
    SOC_TRANG("Soc Trang"),
    SON_LA("Son La"),
    TAY_NINH("Tay Ninh"),
    THAI_BINH("Thai Binh"),
    THAI_NGUYEN("Thai Nguyen"),
    THANH_HOA("Thanh Hoa"),
    THUA_THIEN_HUE("Thua Thien Hue"),
    TIEN_GIANG("Tien Giang"),
    TP_HCM("TP Ho Chi Minh"),
    TRA_VINH("Tra Vinh"),
    TUYEN_QUANG("Tuyen Quang"),
    VINH_LONG("Vinh Long"),
    VINH_PHUC("Vinh Phuc"),
    YEN_BAI("Yen Bai");

    private final String provinceName;

    ProvinceCode(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public static Map<String, String> getProvince() {
        Map<String, String> provinces = new LinkedHashMap<String, String>();
        for (ProvinceCode item : ProvinceCode.values()) {
            provinces.put(item.toString(), item.getProvinceName());
        }
        return provinces;
    }
}


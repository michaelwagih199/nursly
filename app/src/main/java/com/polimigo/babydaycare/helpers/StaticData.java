package com.polimigo.babydaycare.helpers;

import java.util.Arrays;
import java.util.List;

/**
 * @author michael wagih
 */
public class StaticData {
    public static List<String> getEgyptGovernorate(){
        List<String> governorates = Arrays.asList(
                "Select governorates",
                "Matruh",
                "Alexandria",
                "Beheira",
                "Kafr El Sheikh",
                "Dakahlia",
                "Damietta",
                "Port Said",
                "North Sinai",
                "Gharbia",
                "Monufia",
                "Qalyubia",
                "Sharqia",
                "Ismailia",
                "Giza",
                "Faiyum",
                "Cairo",
                "Suez",
                "South Sinai",
                "Beni Suef",
                "Minya",
                "New Valley",
                "Asyut",
                "Red Sea",
                "Sohag",
                "Qena",
                "Luxor",
                "Aswan"
        );
        return governorates;
    }
}

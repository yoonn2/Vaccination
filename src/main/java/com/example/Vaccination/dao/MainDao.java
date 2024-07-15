package com.example.Vaccination.dao;

import com.example.Vaccination.dto.CustomerDto;
import com.example.Vaccination.dto.RecordDto;
import com.example.Vaccination.dto.VaccineDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.List;
@Mapper
public interface MainDao {
    @Select("SELECT * FROM tbl_cust_01")
    public List<CustomerDto> getAllCustomers() throws DataAccessException;
    @Insert("INSERT INTO tbl_order_01 (p_seno, p_no, i_code, p_date) VALUES (#{p_seno}, #{p_no}, #{i_code}, #{p_date})")
    void insertVaccinateion(VaccineDto vaccination);
    @Select("SELECT o.p_seno, o.p_no, c.p_name, o.i_code, i.i_name, o.p_date " +
            "FROM tbl_order_01 o " +
            "JOIN tbl_cust_01 c ON o.p_no = c.p_no " +
            "JOIN tbl_injection_01 i ON o.i_code = i.i_code " +
            "ORDER BY o.p_no")
    List<RecordDto> getAllRecords() throws DataAccessException;
    @Select("SELECT inj.i_code AS a, " +
            "       inj.i_name AS b, " +
            "       COUNT(ord.p_seno) AS c " +
            "FROM tbl_injection_01 inj " +
            "JOIN tbl_order_01 ord ON inj.i_code = ord.i_code " +
            "GROUP BY inj.i_code, inj.i_name")
    List<HashMap<String, Object>> fetchVaccines();
}

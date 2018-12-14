package repository;
import domain.Province;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinceRepository extends Dbtools {

    public List<Province> listProvinces(){
        makeJDBCConnection();

        List<Province> provinces = new ArrayList<>();

        try {
            String query = "SELECT * FROM provinces ORDER BY province_name";

            prepareStat = varConn.prepareStatement(query);
            ResultSet rs = prepareStat.executeQuery();

            while (rs.next()){
                int provinceId = rs.getInt("province_id");
                String provinceName = rs.getString("province_name");

                Province province = new Province(provinceId, provinceName);

                provinces.add(province);
            }

        } catch (SQLException e){
            e.getErrorCode();
        } finally {
            closeConnection(varConn);
        }

        return provinces;
    }
}

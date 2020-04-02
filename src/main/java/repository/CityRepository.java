package repository;

import domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityRepository extends Dbtools {

    public List<City> listCity() {
        makeJDBCConnection();

        List<City> cities = new ArrayList<>();
        try {
            String query = "select a.province_id, a.city_id, a.city_name, b.province_name from cities a\n" +
                    "left join provinces b on (a.province_id = b.province_id)\n" +
                    "order by b.province_name, a.city_name";

            prepareStat = varConn.prepareStatement(query);
            ResultSet rs = prepareStat.executeQuery();

            while (rs.next()) {

                int cityId = rs.getInt("city_id");
                String cityName = rs.getString("city_name");
                int provinceId = rs.getInt("province_id");
                String provinceName = rs.getString("province_name");

                Province province = new Province(provinceId, provinceName);

                City city = new City(province, cityId, cityName);

                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(varConn);
        }

        return cities;
    }
}

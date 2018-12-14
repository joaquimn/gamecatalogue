package view;

import domain.Game;
import domain.Province;
import service.GameService;
import service.PlatformService;
import service.ProvinceService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HeaderServletController extends BaseController {

    ProvinceService provinceService = new ProvinceService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            String action = extractAction(request);

            switch (action) {
                default:
                    listProvinces(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listProvinces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Province> provinces = provinceService.listProvinces();

        request.setAttribute("provinces", provinces);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/header.jsp");
        dispatcher.forward(request, response);
    }
}

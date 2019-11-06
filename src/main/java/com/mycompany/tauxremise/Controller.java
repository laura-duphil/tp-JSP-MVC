/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tauxremise;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedago
 */
@WebServlet(name="Controller",urlPatterns = ("/Controller"))
public class Controller extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        try {
        DAOmdl dao = new DAOmdl(DataSourceFactory.getDataSource());
        List<DiscountEntity> reductions = dao.touteReducs();
        
        request.setAttribute("discount", reductions);
        
        request.getRequestDispatcher("vue.jsp").forward(request, response);
        } catch(Exception ex) {
                Logger.getLogger("Controller").log(Level.SEVERE, "Err de traitement", ex);
        }
    }
}

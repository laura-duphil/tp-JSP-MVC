/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tauxremise;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
        DAOmdl dao = new DAOmdl(DataSourceFactory.getDataSource());
        String monCode;
        String monTaux; 
        monCode = request.getParameter("code");
        monTaux = request.getParameter("taux");
        
        List<DiscountEntity> reductions = dao.touteReducs();
        
        
        //ajouter
       
        String action;
       
        action = request.getParameter("action");
        
        request.setAttribute("selectedCode", reductions);
        request.getRequestDispatcher("views/vue.jsp").forward(request, response);
        
        if("ADD".equals(action)){
            dao.ajouterCode(monCode, Float.parseFloat(monTaux));
            reductions = dao.touteReducs();
        }
        
        if(action.equals("DELETE")){
            dao.delete(monCode, Float.parseFloat(monTaux));
            reductions = dao.touteReducs();
        }
        
        } catch(Exception ex) {
            
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("views/vue.jsp").forward(request, response);
        }
    }
}

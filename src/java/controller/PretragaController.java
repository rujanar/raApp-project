package controller;

import dao.AktivnostDao;
import dao.KorisnikDao;
import dao.ZadatakDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Zadaci;

public class PretragaController extends HttpServlet {
    private ZadatakDao dao;
    private AktivnostDao daoAkt;
    private KorisnikDao daoKor;
    
    public PretragaController() {
        super();
        dao = new ZadatakDao();
        daoAkt = new AktivnostDao();
        daoKor = new KorisnikDao();
    }
    
    private static final long serialVersionUID = 1L;
    private static String LIST = "/pregled.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
         
        // unos za pretragu
        Zadaci zadatak = new Zadaci();
        String period[] = new String[2];
        
        String nalogodavac = request.getParameter("pNalogodavac");
        if(nalogodavac != null && !nalogodavac.isEmpty())        
        {
            zadatak.setZad_korisnik_id(Integer.parseInt(nalogodavac));
        }
        String opis = request.getParameter("pTekst");
        zadatak.setOpis(opis);
        
        String period1 = request.getParameter("pPeriod1");
        String period2 = request.getParameter("pPeriod2");
        period[0] = period1;
        period[1] = period2;
      
        RequestDispatcher view = request.getRequestDispatcher(LIST);
        if ((nalogodavac == null || nalogodavac.isEmpty()) && (opis == null || opis.isEmpty()) && (period1 == null || period1.isEmpty()) && (period2 == null || period2.isEmpty())){
        request.setAttribute("zadaci", dao.getAllZadaci());
        }
        else {
        request.setAttribute("zadaci", dao.pretragaZadaci(zadatak, period));
        }
        request.setAttribute("aktivnosti", daoAkt.getAllAktivnosti());
        request.setAttribute("korisnici", daoKor.getAllKorisnici());
        view.forward(request, response);
    } 
         
}
    
      
    

    

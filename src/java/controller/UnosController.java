package controller;

import dao.AktivnostDao;
import dao.KorisnikDao;
import dao.ZadatakDao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Aktivnosti;
import model.Zadaci;

public class UnosController extends HttpServlet {
    private ZadatakDao dao;
    private AktivnostDao daoAkt;
    private KorisnikDao daoKor;
    
    public UnosController() {
        super();
        dao = new ZadatakDao();
        daoAkt = new AktivnostDao();
        daoKor = new KorisnikDao();
    }
    
    private static final long serialVersionUID = 1L;
    private static String REDIRECT = "/pregled.jsp";
    private static String UNOS = "/unos.jsp";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        // action unos
        if (action.equalsIgnoreCase("unos")){
            forward = UNOS;
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
        }
        // action brisi aktivnost
        else if (action.equalsIgnoreCase("brisi")){
            int zadatak_id = Integer.parseInt(request.getParameter("akt_zadatak_id"));
            Zadaci zadatak = dao.getZadatakById(zadatak_id);
            int aktivnost_id = Integer.parseInt(request.getParameter("aktivnost_id"));
            daoAkt.deleteAktivnost(aktivnost_id);
            forward = "Pregled?action=edit&zadatak_id=" + zadatak_id;
            request.setAttribute("zadatak", zadatak);
            request.setAttribute("aktivnosti", daoAkt.getAllAktivnosti());
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // insert zadatak
        Zadaci zadatak = new Zadaci();
        zadatak.setOpis(request.getParameter("opis"));
        zadatak.setZadatak_rok(request.getParameter("zadatakRok"));
        HttpSession session = request.getSession();
        int kor_id = (int)session.getAttribute("korisnik_id");
        zadatak.setZad_korisnik_id(kor_id);
        String zadatak_id = request.getParameter("zadatakId");
        if(zadatak_id == null || zadatak_id.isEmpty())        
        {
            dao.addZadatak(zadatak);
        }
        else
        {
            zadatak.setZadatak_id(Integer.parseInt(zadatak_id));
            dao.updateZadatak(zadatak);
        }
        
        // insert aktivnosti
        Aktivnosti aktivnost = new Aktivnosti();
        if(zadatak_id == null || zadatak_id.isEmpty()){
            try {
                aktivnost.setAkt_zadatak_id(dao.getLastInsertedId());
            } catch (SQLException ex) {}
        } else {
            aktivnost.setAkt_zadatak_id(Integer.parseInt(zadatak_id));
        }
        for(int i = 1; i<=10; i++){
        String opis = request.getParameter("aktivnostOpis" + i);
        if(opis != null && !opis.isEmpty()){
            aktivnost.setAktivnost_opis(request.getParameter("aktivnostOpis" + i));
            aktivnost.setPotrebno_vreme(request.getParameter("potrebnoVreme" + i));
            aktivnost.setAktivnost_rok(request.getParameter("aktivnostRok" + i));
            aktivnost.setAkt_korisnik_id(Integer.parseInt(request.getParameter("izvrsilac" + i)));
            aktivnost.setStatus(request.getParameter("status" + i));
            String aktivnost_id = request.getParameter("aktivnostId" + i);
            if(aktivnost_id == null || aktivnost_id.isEmpty())
            {
                daoAkt.addAktivnost(aktivnost);
            }
            else
            {
                aktivnost.setAktivnost_id(Integer.parseInt(aktivnost_id));
                daoAkt.updateAktivnost(aktivnost);
            }
        }
        }
        
        RequestDispatcher view = request.getRequestDispatcher(REDIRECT);
        request.setAttribute("zadaci", dao.getAllZadaci());
        request.setAttribute("aktivnosti", daoAkt.getAllAktivnosti());
        request.setAttribute("korisnici", daoKor.getAllKorisnici());
        view.forward(request, response);
    }
}
    

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
import javax.servlet.http.HttpSession;
import model.Zadaci;

public class PregledController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String EDIT = "/unos.jsp";
    private static String LIST = "/pregled.jsp";
    private static String NEZAPOCETO = "/nezapoceto.jsp";
    private static String UTOKU = "/utoku.jsp";
    private static String ZAVRSENO = "/zavrseno.jsp";
    private static String UPDATE_TO_UTOKU = "/nezapoceto.jsp";
    private static String UPDATE_TO_ZAVRSENO = "/utoku.jsp";
    private ZadatakDao dao;
    private AktivnostDao daoAkt;
    private KorisnikDao daoKor;
    private int trenutniKorisnikId;

    public PregledController() {
        super();
        dao = new ZadatakDao();
        daoAkt = new AktivnostDao();
        daoKor = new KorisnikDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        HttpSession session = request.getSession();
        trenutniKorisnikId = (int) session.getAttribute("korisnik_id");
        String action = request.getParameter("action");
        // action delete zadatak
        if (action.equalsIgnoreCase("delete")){
            int zadatak_id = Integer.parseInt(request.getParameter("zadatak_id"));
            dao.deleteZadatak(zadatak_id);
            forward = LIST;
            request.setAttribute("zadaci", dao.getAllZadaci());    
            request.setAttribute("aktivnosti", daoAkt.getAllAktivnosti());
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
        } 
        // action edit zadatak
        else if (action.equalsIgnoreCase("edit")){
            forward = EDIT;
            int zadatak_id = Integer.parseInt(request.getParameter("zadatak_id"));
            Zadaci zadatak = dao.getZadatakById(zadatak_id);
            request.setAttribute("zadatak", zadatak);
            request.setAttribute("aktivnost", daoAkt.getAllAktivnosti());
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
        } 
        // action pregled 
        else if (action.equalsIgnoreCase("pregled")){
            forward = LIST;
            request.setAttribute("zadaci", dao.getAllZadaci());
            request.setAttribute("aktivnosti", daoAkt.getAllAktivnosti());
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
        } 
        // action nezapoceto
        else if (action.equalsIgnoreCase("nezapoceto")){ 
            forward = NEZAPOCETO;
            request.setAttribute("trenutniZadaci", dao.getAllZadaciByTrNezapoceto(trenutniKorisnikId));
            request.setAttribute("aktivnosti", daoAkt.getAllAktivnosti());
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
        } 
        // action utoku 
        else if (action.equalsIgnoreCase("utoku")){ 
            forward = UTOKU;
            request.setAttribute("trenutniZadaci", dao.getAllZadaciByTrUtoku(trenutniKorisnikId));
            request.setAttribute("aktivnosti", daoAkt.getAllAktivnosti());
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
        }
        // action zavrseno 
        else if (action.equalsIgnoreCase("zavrseno")){ 
            forward = ZAVRSENO;
            request.setAttribute("trenutniZadaci", dao.getAllZadaciByTrZavrseno(trenutniKorisnikId));
            request.setAttribute("aktivnosti", daoAkt.getAllAktivnosti());
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
        }
        // action update to u toku
        else if (action.equalsIgnoreCase("statusut")){
            int aktivnost_id = Integer.parseInt(request.getParameter("aktivnost_id"));
            daoAkt.updateStatusUtoku(aktivnost_id);
            forward = UPDATE_TO_UTOKU;
            request.setAttribute("trenutniZadaci", dao.getAllZadaciByTrNezapoceto(trenutniKorisnikId));
            request.setAttribute("aktivnosti", daoAkt.getAllAktivnosti());
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
        }
        //action update to završeno
        else if (action.equalsIgnoreCase("statusz")){
            int aktivnost_id = Integer.parseInt(request.getParameter("aktivnost_id"));
            daoAkt.updateStatusZavrseno(aktivnost_id);
            forward = UPDATE_TO_ZAVRSENO;
            request.setAttribute("trenutniZadaci", dao.getAllZadaciByTrUtoku(trenutniKorisnikId));
            request.setAttribute("aktivnosti", daoAkt.getAllAktivnosti());
            request.setAttribute("korisnici", daoKor.getAllKorisnici());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    
}
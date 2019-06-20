package controller;

import dao.KorisnikDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Korisnici;

public class LoginController extends HttpServlet {
    private KorisnikDao daoKor;
    public Korisnici trenutniKorisnik;
    public int korisnik_id;
    
    public LoginController() {
        super();
        daoKor = new KorisnikDao();
    }
    
    private static final long serialVersionUID = 1L;
    private static String PREGLED = "/pregled.jsp";
    private static String ERROR = "/error.jsp";
    private static String LOGIN = "/login.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("pregled")){
            forward = PREGLED;
        } else if (action.equalsIgnoreCase("error")){
            forward = ERROR;
        } else if (action.equalsIgnoreCase("login")){
            forward = LOGIN;
        } else {
            forward = LOGIN;
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // login check
        Korisnici korisnik = new Korisnici();
        korisnik.setUsername(request.getParameter("username"));
        korisnik.setPassword(request.getParameter("password"));
        daoKor.getKorisnikLogin(korisnik);
        if(korisnik.isValid()){
          HttpSession session = request.getSession(true);
          session.setAttribute("trenutniKorisnik", korisnik);
          session.setAttribute("korisnik_id", korisnik.getKorisnik_id());
          response.sendRedirect("Pregled?action=pregled");
        }
        // logout
        else if (trenutniKorisnik != null){
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        else response.sendRedirect("error.jsp"); 
    }
}
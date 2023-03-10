package it.unisa.control;

import it.unisa.model.UserBean;
import it.unisa.model.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")

/**
 * Servlet implementation class LoginServlet
 */ public class LoginServlet extends HttpServlet {


  /**
   *
   */
  private static final long serialVersionUID = 1L;
  static UserDAO UserDAO = new UserDAO();

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, java.io.IOException {

    try {

      UserBean user = new UserBean();
      user.setUsername(request.getParameter("un"));
      user.setPassword(request.getParameter("pw"));
      user = UserDAO.doRetrieve(user);

      if (user.isValid()) {

        HttpSession session = request.getSession(true);
        session.setAttribute("currentSessionUser", user);
        response.sendRedirect("Homepage.jsp"); //logged-in page
      } else
        response.sendRedirect("loginPage.jsp?login=wrong"); //error page
    } catch (Throwable theException) {
      System.out.println(theException);
    }
  }
}

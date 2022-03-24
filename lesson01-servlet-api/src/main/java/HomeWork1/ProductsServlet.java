package HomeWork1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductsServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        for (int i = 0; i < 10; i++) {
            this.productRepository.insert(new Product("Product " + (i + 1), (Math.random() * 5000 + 1000)));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().println("<h1>Привет от сервлета!!!</h1>");
//        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
//        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
//        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");
//        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");
//        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p>");
//        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p>");

        PrintWriter wr = resp.getWriter();

        wr.println("<table>");
        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th>Title</th>");
        wr.println("<th>Cost</th>");
        wr.println("</tr>");
        if (req.getPathInfo() == null) {
            for (Product product : productRepository.findAll()) {
                wr.println("<tr>");
                wr.println("<td><a href=" + req.getRequestURL() + "/" + product.getId() + ">" + product.getId() + "</a></td>");
                wr.println("<td>" + product.getTitle() + "</td>");
                wr.format("<td>%.2f", product.getCost());
                wr.println("</td>");
                wr.println("</tr>");
            }
        } else {
            String product = req.getPathInfo();
            Product selectedItem = productRepository.findById(Long.parseLong(product.substring(1)));
            wr.println("<tr>");
            wr.println("<td>" + selectedItem.getId() + "</a></td>");
            wr.println("<td>" + selectedItem.getTitle() + "</td>");
            wr.format("<td>%.2f", selectedItem.getCost());
            wr.println("</td>");
            wr.println("</tr>");
        }
    }
}




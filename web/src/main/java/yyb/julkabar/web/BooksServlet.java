package yyb.julkabar.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import yyb.julkabar.core.domain.Book;
import yyb.julkabar.core.domain.PageRequest;
import yyb.julkabar.core.port.CatalogRepositoryPort;

import java.io.IOException;
import java.util.List;

@WebServlet("/books/*")
public class BooksServlet extends HttpServlet {

    private final CatalogRepositoryPort bookRepo;

    public BooksServlet(CatalogRepositoryPort bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PageRequest pageRequest = new PageRequest(0, 20);
        List<Book> books = bookRepo.search(null, pageRequest).getItems();

        req.setAttribute("books", books);
        req.getRequestDispatcher("/WEB-INF/views/books.jsp").forward(req, resp);
    }
}

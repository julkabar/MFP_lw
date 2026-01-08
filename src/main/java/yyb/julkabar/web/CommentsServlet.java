package yyb.julkabar.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import yyb.julkabar.db.CommentDao;

import java.io.IOException;

public class CommentsServlet extends jakarta.servlet.http.HttpServlet {
    private final CommentDao dao = new CommentDao();
    private final com.fasterxml.jackson.databind.ObjectMapper om =
            new com.fasterxml.jackson.databind.ObjectMapper();
    /**
     * GET /comments
     * Очікувано: 200 OK + JSON масив коментарів.
     * Помилка БД: 500 + "DB error".
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json; charset=UTF-8");
        try {
            om.writeValue(resp.getWriter(), dao.list());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "DB error");
        }
    }

/**
 * POST /comments
 * Очікувано: 204 No Content при успіху.
 * Валідація: 400 (порожні/надто довгі поля або некоректний todoId).
 * Помилка БД: 500 + "DB error".
 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");
        String author = req.getParameter("author");
        String text   = req.getParameter("text");

        if (author == null || author.isBlank() || author.length() > 64 ||
                text == null || text.isBlank() || text.length() > 1000) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
            return;
        }

        try {
            dao.add(author.trim(), text.trim());
            log("Add comment: автор - " + author + ", довжина - " + text.length());
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "DB error");
        }
    }
}

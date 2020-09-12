package webapp.servlet;

import com.google.gson.Gson;
import webapp.repository.Table;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Puzzle", urlPatterns = {"/servlet/puzzle"})
public class Puzzle extends HttpServlet {
    private Table table;
    private String username;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.username = request.getParameter("username").split("\n")[0];
        this.table = new Table(this.username);
        HashMap<Integer, String> data = table.findAll();
        data.put(-1, table.getMoves().toString());
        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String index = request.getParameter("position");
        String fullImage = request.getParameter("source");
        String image = fullImage.split("/")[2];
        Integer position = Integer.valueOf(index);
        HashMap<Integer, String> maps  = swap(position, image);
        table.incMoves();
        maps.put(-1, table.getMoves().toString());
        if (maps.size()>0){
            String json = new Gson().toJson(maps);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

//    private boolean checkWin()
//    {
//        HashMap<Integer, String> data = table.findAll();
//        for (Map.Entry<Integer,String> entry : data.entrySet()) {
//            if (!entry.getKey().equals(Integer.parseInt(entry.getValue().split(".")[0]))) return false;
//        }
//
//        return true;
//    }



    private HashMap<Integer, String> swap(Integer position, String image){
        if (image.equals("empty.jpg")) return null;
        Integer replacement = getReplacement(position, image);
        if (replacement == -1) return null;
        HashMap<Integer, String> updated = new HashMap<>();
        updated.put(position, "empty.jpg");
        updated.put(replacement, image);
        table.update(position,"empty.jpg");
        table.update(replacement, image);
        return updated;
    }

    private Integer getReplacement(Integer position, String image) {
        HashMap<Integer, String> data = table.findAll();
        String current = null;
        switch (position) {
            case 1: {
                current = data.get(2);
                if (current.equals("empty.jpg")) return 2;
                current = data.get(4);
                if (current.equals("empty.jpg")) return 4;
                break;
            }
            case 2: {
                current = data.get(1);
                if (current.equals("empty.jpg")) return 1;
                current = data.get(5);
                if (current.equals("empty.jpg")) return 5;
                current = data.get(3);
                if (current.equals("empty.jpg")) return 3;
                break;
            }
            case 3: {
                current = data.get(2);
                if (current.equals("empty.jpg")) return 2;
                current = data.get(6);
                if (current.equals("empty.jpg")) return 6;
                break;
            }
            case 4: {
                current = data.get(1);
                if (current.equals("empty.jpg")) return 1;
                current = data.get(5);
                if (current.equals("empty.jpg")) return 5;
                current = data.get(7);
                if (current.equals("empty.jpg")) return 7;
                break;
            }
            case 5: {
                current = data.get(2);
                if (current.equals("empty.jpg")) return 2;
                current = data.get(4);
                if (current.equals("empty.jpg")) return 4;
                current = data.get(6);
                if (current.equals("empty.jpg")) return 6;
                current = data.get(8);
                if (current.equals("empty.jpg")) return 8;
                break;
            }
            case 6: {
                current = data.get(3);
                if (current.equals("empty.jpg")) return 3;
                current = data.get(5);
                if (current.equals("empty.jpg")) return 5;
                current = data.get(9);
                if (current.equals("empty.jpg")) return 9;
                break;
            }
            case 7: {
                current = data.get(4);
                if (current.equals("empty.jpg")) return 4;
                current = data.get(8);
                if (current.equals("empty.jpg")) return 8;
                break;
            }
            case 8: {
                current = data.get(7);
                if (current.equals("empty.jpg")) return 7;
                current = data.get(5);
                if (current.equals("empty.jpg")) return 5;
                current = data.get(9);
                if (current.equals("empty.jpg")) return 9;
                break;

            }
            case 9:{
                current = data.get(6);
                if (current.equals("empty.jpg")) return 6;
                current = data.get(8);
                if (current.equals("empty.jpg")) return 8;
                break;
            }
        }
        return -1;

    }
}

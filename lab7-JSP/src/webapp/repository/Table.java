package webapp.repository;

import webapp.model.User;
import webapp.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Table {
    private ArrayList<String> images;
    private Connection connection;
    private String username;
    private Integer moves;

    public Table(String username) {
        this.username = username;
        connection = DatabaseConnection.getConnection();
        images = new ArrayList<>(Arrays.asList("1.jpg", "2.jpg", "3.jpg", "4.jpg","5.jpg", "6.jpg", "7.jpg", "8.jpg", "empty.jpg"));
        checkPlayer();
        moves = checkMoves();
    }

    public Integer getMoves() {
        return moves;
    }

    public void incMoves(){
        this.moves+=1;
        try{
            String sql = "update moves set counter = ? where user_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, this.moves);
            statement.setString(2, this.username);
            statement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private Integer checkMoves(){
        Integer moves = null;
        try{
            Statement statement = connection.createStatement();
            String sql = "select * from moves where user_name= '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()){
                moves = initMoves();
            }
            moves =  resultSet.getInt("counter");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return moves;
    }

    private void checkPlayer(){
        try{
            Statement statement = connection.createStatement();
            String sql = "select * from puzzle where user_name= '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()){
                initTable();
                return;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private Integer initMoves(){
        try {
            String sql = "insert into moves(user_name) values (?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.username);
            statement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


    private Integer restartMoves(){
        try {
            String sql = "update moves set counter = ? where user_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 0);
            statement.setString(2, this.username);
            statement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


    private void initTable() {
        Collections.shuffle(images);
        Integer position = 1;
        while (position <= images.size()){
            add(position, images.get(position - 1));
            position+=1;
        }
    }

    private void restartTable() {
        Collections.shuffle(images);
        Integer position = 1;
        while (position <= images.size()){
            update(position, images.get(position - 1));
            position+=1;
        }
    }

    public void add(Integer pos, String image) {
        try {
            String sql = "insert into puzzle(user_name, position, image) values (?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.username);
            statement.setInt(2, pos);
            statement.setString(3, image);
            statement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(Integer pos, String image) {
        try {
            String sql = "update puzzle set image = ? where position = ? and user_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, image);
            statement.setInt(2, pos);
            statement.setString(3, this.username);
            statement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<Integer, String> findAll(){
        HashMap<Integer, String> table = new HashMap<>();
        try{
            Statement statement = connection.createStatement();
            String sql = "select * from puzzle where user_name= '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                table.put(resultSet.getInt("position"), resultSet.getString("image"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return table;
    }

    public void initialise(){
        restartTable();
        this.moves = restartMoves();
    }

}

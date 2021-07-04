package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Digital;


public class DAO extends DBContext {

    //hàm để lấy bài đăng mới nhất từ database
    public Digital getTop() {
        try {
            // cau lenh sql
            String url = "select top 1 * from Digital\n"
                    + "order by timePost desc";
            PreparedStatement stm = connection.prepareStatement(url);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SimpleDateFormat dateF = new SimpleDateFormat("MMM dd yyyy - h:mm");
                SimpleDateFormat dateF2 = new SimpleDateFormat("a");
                String dateFormat = dateF.format(rs.getTimestamp("timePost")) + dateF2.format(rs.getTimestamp("timePost")).toLowerCase();
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        dateFormat,
                        rs.getString("shortDes"));
                return d;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // hàm để lấy 5 bài mới gần đây nhất sau bài đăng mới nhất
    public ArrayList<Digital> getTop5() {
        ArrayList<Digital> list = new ArrayList<>();
        try {
            String url = "select top 5 * from digital\n"
                    + "where timePost not in(\n"
                    + "select max(timepost) from digital\n"
                    + ")\n"
                    + "order by timePost desc";
            PreparedStatement stm = connection.prepareStatement(url);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SimpleDateFormat dateF = new SimpleDateFormat("MMM dd yyyy - h:mm");
                SimpleDateFormat dateF2 = new SimpleDateFormat("a");
                String dateFormat = dateF.format(rs.getTimestamp("timePost")) + dateF2.format(rs.getTimestamp("timePost")).toLowerCase();
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        dateFormat,
                        rs.getString("shortDes"));
                list.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // hàm để lấy bài news theo id
    public Digital getByID(int id) {
        try {
            String url = "select * from digital where ID = ?";
            PreparedStatement stm = connection.prepareStatement(url);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SimpleDateFormat dateF = new SimpleDateFormat("MMM dd yyyy - h:mm");
                SimpleDateFormat dateF2 = new SimpleDateFormat("a");
                String dateFormat = dateF.format(rs.getTimestamp("timePost")) + dateF2.format(rs.getTimestamp("timePost")).toLowerCase();
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        dateFormat,
                        rs.getString("shortDes"));
                return d;
            }
        } catch (SQLException ex) { 
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // hàm để đếm số lượng bài khi mình search
    public int countSearch(String txt) {
        try {
            String url = "select count(*) from digital\n"
                    + "where title like ?";
            PreparedStatement stm = connection.prepareStatement(url);
            stm.setString(1, "%" + txt + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    
    public ArrayList<Digital> searchResult(String txt, int index, int size) {
        ArrayList<Digital> list = new ArrayList<>();
        try {
            String url = "select * from digital \n"
                    + "where title like ? \n"
                    + "ORDER BY id OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(url);
            stm.setString(1, "%" + txt + "%");
            stm.setInt(2, (index - 1) * size);
            stm.setInt(3, size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SimpleDateFormat dateF = new SimpleDateFormat("MMM dd yyyy - h:mm");
                SimpleDateFormat dateF2 = new SimpleDateFormat("a");
                String dateFormat = dateF.format(rs.getTimestamp("timePost")) + dateF2.format(rs.getTimestamp("timePost")).toLowerCase();
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        dateFormat,
                        rs.getString("shortDes"));
                list.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}

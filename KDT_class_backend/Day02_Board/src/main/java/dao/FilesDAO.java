package dao;

import dto.FilesDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FilesDAO {

    //region initial methods
    private static FilesDAO instance;

    private FilesDAO() {
    }

    public synchronized static FilesDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new FilesDAO();
        }
        return instance;
    }

    private Connection getConnection() throws Exception {
        Context cxt = new InitialContext();
        DataSource ds = (DataSource) cxt.lookup("java:comp/env/jdbc/study");
        return ds.getConnection();
    }
    //endregion

    //region insert
    public boolean uploadFile(FilesDTO file) throws Exception {
        String sql = "INSERT INTO files VALUES(files_seq.nextval, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, file.getWriter());
            pstat.setString(2, file.getOriName());
            pstat.setString(3, file.getSysName());
            pstat.setInt(4, file.getParentSeq());

            return pstat.executeUpdate() > 0;
        }
    }
    //endregion

    //region select
    public List<FilesDTO> getFilesList() throws Exception {
        String sql = "SELECT * FROM files ORDER BY seq DESC";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
             ResultSet rs = pstat.executeQuery()) {
            List<FilesDTO> resultList = new ArrayList<>();
            while (rs.next()) {
                int seq = rs.getInt("seq");
                String writer = rs.getString("writer");
                String oriName = rs.getString("oriName");
                String sysName = rs.getString("sysName");
                int parentSeq = rs.getInt("parent_seq");

                resultList.add(new FilesDTO(seq, writer, oriName, sysName, parentSeq));
            }
            return resultList;
        }
    }

    public List<FilesDTO> getFilesListByParentSeq(int target) throws Exception {
        String sql = "SELECT * FROM files WHERE parent_seq=? ORDER BY seq DESC";

        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setInt(1, target);
            try (ResultSet rs = pstat.executeQuery()) {
                List<FilesDTO> resultList = new ArrayList<>();
                while (rs.next()) {
                    int seq = rs.getInt("seq");
                    String writer = rs.getString("writer");
                    String oriName = rs.getString("oriName");
                    String sysName = rs.getString("sysName");

                    resultList.add(new FilesDTO(seq, writer, oriName, sysName, target));
                }
                return resultList;
            }
        }
    }

    //endregion

}

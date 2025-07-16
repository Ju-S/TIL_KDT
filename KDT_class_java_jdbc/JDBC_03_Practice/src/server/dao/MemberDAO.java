package server.dao;

import server.dto.MemberDTO;
import server.security.CustomEncrypt;
import server.config.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
    // 회원가입
    public int registerMember(MemberDTO member) throws Exception {
        String registerSql = "insert into member values( ?, ?, ?)";
        try (Connection con = DataSourceProvider.getInstance().getConnection();
             PreparedStatement stat = con.prepareStatement(registerSql)) {
            stat.setString(1, member.getId());
            stat.setString(2, CustomEncrypt.encrypt(member.getPassword()));
            stat.setString(3, member.getName());
            return stat.executeUpdate();
        }
    }

    // 아이디 존재 확인
    public boolean isIDExist(String id) throws Exception {
        String sql = "select * from member where id = ?";
        try (Connection con = DataSourceProvider.getInstance().getConnection();
             PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, id);
            try(ResultSet rs = stat.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Statement -> PreparedStatement
    // 1. 보안성 확보
    // 2. 쿼리 조립 편의성 확보
    // 3. 일부 전제 조건 하에 성능 개선

    // 로그인(성공 시, name반환)
    public String loginMember(MemberDTO member) throws Exception {
        String sql = "select name from member where id = ? and password = ?";
        try (Connection con = DataSourceProvider.getInstance().getConnection();
             PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, member.getId());
            stat.setString(2, CustomEncrypt.encrypt(member.getPassword()));
            try(ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    return "";
                }
            }
        }
    }
}

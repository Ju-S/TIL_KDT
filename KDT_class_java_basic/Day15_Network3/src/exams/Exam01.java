package exams;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Exam01 {
    public static void main(String[] args) {
        // 암호화(encryption)
        // 양방향 암호화 - 키값을 기반으로 암호화 및 복호화가 가능한 암호화 방식
        // 단방향 암호화 - 데이터를 암호화는 시킬 수 있지만 복호화는 불가한 방식

        // 단방향 암호화의 경우, 가입할때 입력한 비밀번호를 암호화하여 저장하고, 로그인시 입력한 비밀번호를 같은 암호화 방식으로 암호화하여 비교.
        // 과거 MD5 Hashing - 단방향 암호화로 많이 사용됨.
        // 현재 MD5 -> SHA256 or SHA512 사용

        System.out.println(encrypt("Hello"));
    }

    public static String encrypt(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
            byte[] digest = md.digest(bytes);

            StringBuilder builder = new StringBuilder();
            for (byte b : digest) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 암호화 실패", e);
        }
    }
}

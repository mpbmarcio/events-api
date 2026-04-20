package br.com.mpb.events.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCrypt {
    public static void main(String[] args) {
        //Para geração de uma senha criptografada para primeiro uso
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode("123456");
        System.out.println(senhaCriptografada);
    }
}

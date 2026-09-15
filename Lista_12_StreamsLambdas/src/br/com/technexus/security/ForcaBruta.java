package br.com.technexus.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * Desafio bônus (Operação Big Data): quebra por força bruta um link cifrado com
 * "openssl enc -aes-256-cbc -pbkdf2 -iter 1000".
 * Senha: exatamente 6 caracteres (letras minúsculas e/ou números), começa com "lam".
 */
public class ForcaBruta {

    public static void main(String[] args) throws Exception {
        // Substitua pela string EXATA fornecida no enunciado (sem espaços/quebras extras)
        String encryptedB64 = "U2FsdGVkX189CvKETNa+k2wHIMpbCwNk7HKB3nBRzOD9bBaPt2nFMCdElvKoRfTmmqVv41Trh37ORXFWRVNOX3"
                + "vpgPHULkkaoyh9DfmzrGBXkGnu/SJfQkGuU08zbgMQNSwCGTwoIHkUMzRFQELN0Q==";

        encryptedB64 = encryptedB64.replaceAll("\\s", "");

        byte[] fullData = Base64.getDecoder().decode(encryptedB64);
        byte[] salt = Arrays.copyOfRange(fullData, 8, 16);
        byte[] cipherText = Arrays.copyOfRange(fullData, 16, fullData.length);

        String charset = "abcdefghijklmnopqrstuvwxyz0123456789";

        System.out.println("Iniciando ataque de força bruta no link da TechNexus...");
        long startTime = System.currentTimeMillis();

        for (char c1 : charset.toCharArray()) {
            for (char c2 : charset.toCharArray()) {
                for (char c3 : charset.toCharArray()) {
                    String testPass = "lam" + c1 + c2 + c3;
                    try {
                        PBEKeySpec spec = new PBEKeySpec(testPass.toCharArray(), salt, 1000, 48 * 8);
                        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                        byte[] keyAndIv = factory.generateSecret(spec).getEncoded();

                        byte[] key = Arrays.copyOfRange(keyAndIv, 0, 32);
                        byte[] iv = Arrays.copyOfRange(keyAndIv, 32, 48);

                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));

                        byte[] decryptedBytes = cipher.doFinal(cipherText);
                        String result = new String(decryptedBytes, StandardCharsets.UTF_8);

                        if (result.contains("http")) {
                            long endTime = System.currentTimeMillis();
                            System.out.println("\nSUCESSO! A criptografia foi quebrada!");
                            System.out.println("Senha encontrada: " + testPass);
                            System.out.println("Link revelado: " + result.trim());
                            System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
                            return;
                        }
                    } catch (Exception e) {
                        // Senha incorreta -> BadPaddingException. Ignora e segue tentando.
                    }
                }
            }
        }

        System.out.println("\nForça bruta concluída. Senha não encontrada.");
        System.out.println("Dica: confira se o texto cifrado foi copiado exatamente igual ao do PDF (sem espaços/quebras extras).");
    }
}

package br.com.fronteira.main;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * Desafio bônus: quebra por força bruta um link cifrado com
 * "openssl enc -aes-256-cbc -pbkdf2 -iter 1000".
 * Senha: 5 caracteres, começa com "jav", últimos 2 são letras/números.
 */
public class App {

    public static void main(String[] args) throws Exception {
        String encryptedB64 = "U2FsdGVkX1/Jz86x4/Ydu3FZFw5pSo86xHG1MwpCFX/"
                + "Dnn9uCMDd3xLNn61XZouvQy6G2FIhyQXAQwvTWn3/01JGIoIh5RN4NXgs+kdpcf6afHmSMvCZ"
                + "u0EiiiXlVpB2EQGIKDLIAU9c1aQx6bzEgQ==";

        // Limpa possíveis quebras de linha do Base64
        encryptedB64 = encryptedB64.replaceAll("\\s", "");

        // 1. Decodifica o Base64 para Bytes
        byte[] fullData = Base64.getDecoder().decode(encryptedB64);

        // 2. Extrai o Salt (do byte 8 ao 15) e o Texto Cifrado (do 16 em diante)
        //    Os 8 primeiros bytes são o cabeçalho "Salted__" do formato OpenSSL.
        byte[] salt = Arrays.copyOfRange(fullData, 8, 16);
        byte[] cipherText = Arrays.copyOfRange(fullData, 16, fullData.length);

        // O padrão da senha é "javXX" contendo letras e números
        String charset = "abcdefghijklmnopqrstuvwxyz0123456789";

        System.out.println("Iniciando ataque de força bruta no link da NexusTech...");
        long startTime = System.currentTimeMillis();

        // 3. Força Bruta: combinando os dois últimos caracteres
        for (char c1 : charset.toCharArray()) {
            for (char c2 : charset.toCharArray()) {
                String testPass = "jav" + c1 + c2;
                try {
                    // PBKDF2 gera Key e IV juntos: 32 bytes (Key) + 16 bytes (IV) = 48 bytes
                    PBEKeySpec spec = new PBEKeySpec(testPass.toCharArray(), salt, 1000, 48 * 8);
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                    byte[] keyAndIv = factory.generateSecret(spec).getEncoded();

                    byte[] key = Arrays.copyOfRange(keyAndIv, 0, 32);
                    byte[] iv = Arrays.copyOfRange(keyAndIv, 32, 48);

                    // Prepara o motor do AES-256-CBC
                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
                    cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));

                    // Tenta descriptografar. Se a chave estiver errada, o padding falha e lança Exception
                    byte[] decryptedBytes = cipher.doFinal(cipherText);
                    String result = new String(decryptedBytes, StandardCharsets.UTF_8);

                    // 4. Checa se revelou o link com sucesso
                    if (result.contains("http")) {
                        long endTime = System.currentTimeMillis();
                        System.out.println("\nSUCESSO! A criptografia foi quebrada!");
                        System.out.println("Senha encontrada: " + testPass);
                        System.out.println("Link revelado: " + result.trim());
                        System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
                        return; // Interrompe a execução após achar a senha
                    }
                } catch (Exception e) {
                    // Uma senha incorreta vai acionar a BadPaddingException.
                    // Nós simplesmente ignoramos o erro e o loop continua testando a próxima!
                }
            }
        }

        System.out.println("\nForça bruta concluída. Senha não encontrada.");
        System.out.println("Dica: confira se o texto cifrado foi copiado exatamente igual ao do PDF (sem espaços/quebras extras).");
    }
}
